/***/
package org.eclipse.ui.navigator;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.internal.navigator.NavigatorSafeRunnable;
import org.eclipse.ui.internal.navigator.Policy;
import org.eclipse.ui.internal.navigator.dnd.NavigatorPluginDropAction;
import org.eclipse.ui.part.PluginTransfer;

/**
*
* Provides an implementation of {@link DragSourceAdapter} which uses the
* extensions provided by the associated {@link INavigatorContentService}.
*
* <p>
* Clients should not need to create an instance of this class unless they are
* creating their own custom viewer. Otherwise, {@link CommonViewer} configures
* its drag adapter automatically.
* </p>
*
* @see INavigatorDnDService
* @see CommonDragAdapterAssistant
* @see CommonDropAdapter
* @see CommonDropAdapterAssistant
* @see CommonViewer
* @since 3.2
*
*/
public final class CommonDragAdapter extends DragSourceAdapter {

    private final INavigatorContentService contentService;

    private final ISelectionProvider provider;

    private CommonDragAdapterAssistant setDataAssistant;

    private List<CommonDragAdapterAssistant> assistantsToUse;

    /**
* Create a DragAdapter that drives the configuration of the drag data.
*
* @param aContentService
*            The content service this Drag Adapter is associated with
* @param aProvider
*            The provider that can give the current selection from the
*            appropriate viewer.
*/
    public  CommonDragAdapter(INavigatorContentService aContentService, ISelectionProvider aProvider) {
        super();
        contentService = aContentService;
        provider = aProvider;
        assistantsToUse = new ArrayList<CommonDragAdapterAssistant>();
    }

    /**
*
* @return An array of supported Drag Transfer types. The list contains [
*         {@link LocalSelectionTransfer#getTransfer()},
*         {@link PluginTransfer#getInstance()}] in addition to any
*         supported types contributed by the
*         {@link CommonDragAdapterAssistant assistants}.
* @see CommonDragAdapterAssistant
* @see LocalSelectionTransfer
* @see PluginTransfer
*/
    public Transfer[] getSupportedDragTransfers() {
        CommonDragAdapterAssistant[] assistants = contentService.getDnDService().getCommonDragAssistants();
        Set<Transfer> supportedTypes = new LinkedHashSet<Transfer>();
        supportedTypes.add(PluginTransfer.getInstance());
        supportedTypes.add(LocalSelectionTransfer.getTransfer());
        Transfer[] transferTypes = null;
        for (int i = 0; i < assistants.length; i++) {
            transferTypes = assistants[i].getSupportedTransferTypes();
            for (int j = 0; j < transferTypes.length; j++) {
                if (transferTypes[j] != null) {
                    supportedTypes.add(transferTypes[j]);
                }
            }
        }
        Transfer[] transfers = supportedTypes.toArray(new Transfer[supportedTypes.size()]);
        return transfers;
    }

    @Override
    public void dragStart(final DragSourceEvent event) {
        if (Policy.DEBUG_DND) {
            //$NON-NLS-1$
            System.out.println("CommonDragAdapter.dragStart (begin): " + event);
        }
        SafeRunner.run(new NavigatorSafeRunnable() {

            @Override
            public void run() throws Exception {
                DragSource dragSource = (DragSource) event.widget;
                if (Policy.DEBUG_DND) {
                    //$NON-NLS-1$
                    System.out.println("CommonDragAdapter.dragStart source: " + dragSource);
                }
                Control control = dragSource.getControl();
                if (control == control.getDisplay().getFocusControl()) {
                    ISelection selection = provider.getSelection();
                    assistantsToUse.clear();
                    if (!selection.isEmpty()) {
                        LocalSelectionTransfer.getTransfer().setSelection(selection);
                        boolean doIt = false;
                        INavigatorDnDService dndService = contentService.getDnDService();
                        CommonDragAdapterAssistant[] assistants = dndService.getCommonDragAssistants();
                        if (assistants.length == 0)
                            doIt = true;
                        for (int i = 0; i < assistants.length; i++) {
                            if (Policy.DEBUG_DND) {
                                System.out.println(//$NON-NLS-1$
                                "CommonDragAdapter.dragStart assistant: " + assistants[i]);
                            }
                            event.doit = true;
                            assistants[i].dragStart(event, (IStructuredSelection) selection);
                            doIt |= event.doit;
                            if (event.doit) {
                                if (Policy.DEBUG_DND) {
                                    System.out.println(//$NON-NLS-1$
                                    "CommonDragAdapter.dragStart assistant - event.doit == true");
                                }
                                assistantsToUse.add(assistants[i]);
                            }
                        }
                        event.doit = doIt;
                    } else {
                        event.doit = false;
                    }
                } else {
                    event.doit = false;
                }
            }
        });
        if (Policy.DEBUG_DND) {
            //$NON-NLS-1$
            System.out.println("CommonDragAdapter.dragStart (end): doit=" + event.doit);
        }
    }

    @Override
    public void dragSetData(final DragSourceEvent event) {
        final ISelection selection = LocalSelectionTransfer.getTransfer().getSelection();
        if (Policy.DEBUG_DND) {
            System.out.println(//$NON-NLS-1$ //$NON-NLS-2$
            "CommonDragAdapter.dragSetData: event" + event + " selection=" + selection);
        }
        if (LocalSelectionTransfer.getTransfer().isSupportedType(event.dataType)) {
            event.data = selection;
            if (Policy.DEBUG_DND) {
                System.out.println(//$NON-NLS-1$
                "CommonDragAdapter.dragSetData set LocalSelectionTransfer: " + event.data);
            }
        } else if (PluginTransfer.getInstance().isSupportedType(event.dataType)) {
            event.data = NavigatorPluginDropAction.createTransferData(contentService);
            if (Policy.DEBUG_DND) {
                System.out.println(//$NON-NLS-1$
                "CommonDragAdapter.dragSetData set PluginTransfer: " + event.data);
            }
        } else if (selection instanceof IStructuredSelection) {
            if (Policy.DEBUG_DND) {
                System.out.println(//$NON-NLS-1$
                "CommonDragAdapter.dragSetData looking for assistants");
            }
            for (int i = 0, len = assistantsToUse.size(); i < len; i++) {
                final CommonDragAdapterAssistant assistant = assistantsToUse.get(i);
                if (Policy.DEBUG_DND) {
                    System.out.println(//$NON-NLS-1$
                    "CommonDragAdapter.dragSetData assistant: " + assistant);
                }
                Transfer[] supportedTransferTypes = assistant.getSupportedTransferTypes();
                final boolean[] getOut = new boolean[1];
                for (int j = 0; j < supportedTransferTypes.length; j++) {
                    if (supportedTransferTypes[j].isSupportedType(event.dataType)) {
                        SafeRunner.run(new NavigatorSafeRunnable() {

                            @Override
                            public void run() throws Exception {
                                if (Policy.DEBUG_DND) {
                                    System.out.println(//$NON-NLS-1$
                                    "CommonDragAdapter.dragSetData supported xfer type");
                                }
                                if (assistant.setDragData(event, (IStructuredSelection) selection)) {
                                    if (Policy.DEBUG_DND) {
                                        System.out.println(//$NON-NLS-1$
                                        "CommonDragAdapter.dragSetData set data " + event.data);
                                    }
                                    setDataAssistant = assistant;
                                    getOut[0] = true;
                                }
                            }
                        });
                        if (getOut[0])
                            return;
                    }
                }
            }
            if (Policy.DEBUG_DND) {
                System.out.println(//$NON-NLS-1$
                "CommonDragAdapter.dragSetData FAILED no assistant handled it");
            }
            event.doit = false;
        } else {
            if (Policy.DEBUG_DND) {
                System.out.println(//$NON-NLS-1$
                "CommonDragAdapter.dragSetData FAILED can't identify transfer type");
            }
            event.doit = false;
        }
    }

    @Override
    public void dragFinished(DragSourceEvent event) {
        if (Policy.DEBUG_DND) {
            //$NON-NLS-1$
            System.out.println("CommonDragAdapter.dragFinished(): " + event);
        }
        ISelection selection = LocalSelectionTransfer.getTransfer().getSelection();
        if (event.doit && selection instanceof IStructuredSelection && setDataAssistant != null)
            setDataAssistant.dragFinished(event, (IStructuredSelection) selection);
        setDataAssistant = null;
        LocalSelectionTransfer.getTransfer().setSelection(null);
    // TODO Handle clean up if drop target was outside of workbench
    // if (event.doit != false) {
    //
    // }
    }
}
