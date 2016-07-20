/***/
package org.eclipse.ui.navigator;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.jface.util.LocalSelectionTransfer;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.FileTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Item;
import org.eclipse.ui.internal.navigator.NavigatorSafeRunnable;
import org.eclipse.ui.internal.navigator.Policy;
import org.eclipse.ui.internal.navigator.dnd.NavigatorPluginDropAction;
import org.eclipse.ui.part.PluginDropAdapter;
import org.eclipse.ui.part.PluginTransfer;

/**
* Provides an implementation of {@link PluginDropAdapter} which uses the
* extensions provided by the associated {@link INavigatorContentService}.
*
* <p>
* Clients should not need to create an instance of this class unless they are
* creating their own custom viewer. Otherwise, {@link CommonViewer} configures
* its drop adapter automatically.
* </p>
*
*
* @see INavigatorDnDService
* @see CommonDragAdapter
* @see CommonDragAdapterAssistant
* @see CommonDropAdapterAssistant
* @see CommonViewer
* @since 3.2
*/
public final class CommonDropAdapter extends PluginDropAdapter {

    private static final Transfer[] SUPPORTED_DROP_TRANSFERS = new Transfer[] { LocalSelectionTransfer.getTransfer(), FileTransfer.getInstance(), PluginTransfer.getInstance() };

    private final INavigatorContentService contentService;

    private final INavigatorDnDService dndService;

    /**
* Create a DropAdapter that handles a drop based on the given content
* service and selection provider.
*
* @param aContentService
*            The content service this Drop Adapter is associated with
* @param aStructuredViewer
*            The viewer this DropAdapter is associated with.
*/
    public  CommonDropAdapter(INavigatorContentService aContentService, StructuredViewer aStructuredViewer) {
        super(aStructuredViewer);
        contentService = aContentService;
        dndService = contentService.getDnDService();
        setFeedbackEnabled(false);
    }

    /**
*
* @return An array of Transfers allowed by the CommonDropAdapter. Includes
*         {@link LocalSelectionTransfer#getTransfer()},
*         {@link FileTransfer#getInstance()},
*         {@link PluginTransfer#getInstance()}.
* @see LocalSelectionTransfer
* @see FileTransfer
* @see PluginTransfer
*/
    public Transfer[] getSupportedDropTransfers() {
        return SUPPORTED_DROP_TRANSFERS;
    }

    @Override
    public void dragEnter(DropTargetEvent event) {
        if (event.detail == DND.DROP_NONE)
            return;
        if (Policy.DEBUG_DND) {
            //$NON-NLS-1$
            System.out.println("CommonDropAdapter.dragEnter: " + event);
        }
        for (int i = 0; i < event.dataTypes.length; i++) {
            if (LocalSelectionTransfer.getTransfer().isSupportedType(event.dataTypes[i])) {
                event.currentDataType = event.dataTypes[i];
                if (Policy.DEBUG_DND) {
                    //$NON-NLS-1$
                    System.out.println("CommonDropAdapter.dragEnter: local selection: " + event.currentDataType);
                }
                super.dragEnter(event);
                return;
            }
        }
        for (int i = 0; i < event.dataTypes.length; i++) {
            if (FileTransfer.getInstance().isSupportedType(event.dataTypes[i])) {
                event.currentDataType = event.dataTypes[i];
                event.detail = DND.DROP_COPY;
                if (Policy.DEBUG_DND) {
                    //$NON-NLS-1$
                    System.out.println("CommonDropAdapter.dragEnter: file: " + event.currentDataType);
                }
                super.dragEnter(event);
                return;
            }
        }
        for (int i = 0; i < event.dataTypes.length; i++) {
            if (PluginTransfer.getInstance().isSupportedType(event.dataTypes[i])) {
                event.currentDataType = event.dataTypes[i];
                if (Policy.DEBUG_DND) {
                    //$NON-NLS-1$
                    System.out.println("CommonDropAdapter.dragEnter: plugin: " + event.currentDataType);
                }
                super.dragEnter(event);
                return;
            }
        }
        event.detail = DND.DROP_NONE;
    }

    @Override
    public void dragLeave(DropTargetEvent event) {
        super.dragLeave(event);
        if (LocalSelectionTransfer.getTransfer().isSupportedType(event.currentDataType)) {
            event.data = NavigatorPluginDropAction.createTransferData(contentService);
        }
    }

    @Override
    public boolean performDrop(Object data) {
        final DropTargetEvent event = getCurrentEvent();
        if (Policy.DEBUG_DND) {
            //$NON-NLS-1$
            System.out.println("CommonDropAdapter.drop (begin): " + event);
        }
        final Object target = getCurrentTarget() != null ? getCurrentTarget() : getViewer().getInput();
        // Must validate the drop here because on some platforms (Linux, Mac) the event
        // is not populated with the correct currentDataType until the drop actually
        // happens, and validateDrop sets the currentTransfer based on that.  The
        // call to validateDrop in dragAccept is too early.
        validateDrop(target, getCurrentOperation(), event.currentDataType);
        if (PluginTransfer.getInstance().isSupportedType(event.currentDataType)) {
            super.drop(event);
            return true;
        }
        if (Policy.DEBUG_DND) {
            //$NON-NLS-1$ //$NON-NLS-2$
            System.out.println("CommonDropAdapter.drop target: " + target + " op: " + getCurrentOperation());
        }
        final CommonDropAdapterAssistant[] assistants = dndService.findCommonDropAdapterAssistants(target, getCurrentTransfer());
        final boolean[] retValue = new boolean[1];
        for (int i = 0; i < assistants.length; i++) {
            final CommonDropAdapterAssistant localAssistant = assistants[i];
            SafeRunner.run(new NavigatorSafeRunnable() {

                @Override
                public void run() throws Exception {
                    localAssistant.setCurrentEvent(event);
                    IStatus valid = localAssistant.validateDrop(target, getCurrentOperation(), getCurrentTransfer());
                    if (valid != null && valid.isOK()) {
                        if (Policy.DEBUG_DND) {
                            System.out.println(//$NON-NLS-1$ //$NON-NLS-2$
                            "CommonDropAdapter.drop assistant selected: " + localAssistant + " op: " + event.detail);
                        }
                        localAssistant.handleDrop(CommonDropAdapter.this, event, target);
                        retValue[0] = true;
                    }
                }
            });
            if (retValue[0])
                return true;
        }
        return false;
    }

    @Override
    public boolean validateDrop(final Object aDropTarget, final int theDropOperation, final TransferData theTransferData) {
        if (Policy.DEBUG_DND) {
            //$NON-NLS-1$ //$NON-NLS-2$
            System.out.println("CommonDropAdapter.validateDrop (begin) operation: " + theDropOperation + " target: " + aDropTarget);
        //new Exception().printStackTrace(System.out);
        }
        boolean result = false;
        final IStatus[] valid = new IStatus[1];
        if (super.validateDrop(aDropTarget, theDropOperation, theTransferData)) {
            result = true;
            if (Policy.DEBUG_DND) {
                //$NON-NLS-1$
                System.out.println("CommonDropAdapter.validateDrop valid for plugin transfer");
            }
        } else {
            final Object target = aDropTarget != null ? aDropTarget : getViewer().getInput();
            if (Policy.DEBUG_DND) {
                //$NON-NLS-1$
                System.out.println("CommonDropAdapter.validateDrop target: " + target);
                System.out.println(//$NON-NLS-1$
                "CommonDropAdapter.validateDrop local selection: " + LocalSelectionTransfer.getTransfer().getSelection());
            }
            CommonDropAdapterAssistant[] assistants = dndService.findCommonDropAdapterAssistants(target, theTransferData);
            for (int i = 0; i < assistants.length; i++) {
                if (Policy.DEBUG_DND) {
                    System.out.println(//$NON-NLS-1$
                    "CommonDropAdapter.validateDrop checking assistant: \"" + assistants[i]);
                }
                final CommonDropAdapterAssistant assistantLocal = assistants[i];
                SafeRunner.run(new NavigatorSafeRunnable() {

                    @Override
                    public void run() throws Exception {
                        assistantLocal.setCurrentEvent(getCurrentEvent());
                        valid[0] = assistantLocal.validateDrop(target, theDropOperation, theTransferData);
                    }
                });
                if (valid[0] != null && valid[0].isOK()) {
                    result = true;
                    if (Policy.DEBUG_DND) {
                        //$NON-NLS-1$
                        System.out.println("CommonDropAdapter.validateDrop VALID");
                    }
                    break;
                }
                if (Policy.DEBUG_DND) {
                    System.out.println(//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                    "CommonDropAdapter.validateDrop NOT valid: " + (valid[0] != null ? (valid[0].getSeverity() + ": " + valid[0].getMessage()) : ""));
                }
            }
        }
        if (Policy.DEBUG_DND) {
            System.out.println(//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
            "CommonDropAdapter.validateDrop (returning " + (valid[0] != null ? valid[0].getSeverity() + ": " + valid[0].getMessage() : "" + result) + ")");
        }
        setScrollExpandEnabled(true);
        return result;
    }

    /*
* The visibility of the following methods is raised for downstream clients
* (assistants).
*/
    @Override
    public Rectangle getBounds(Item item) {
        return super.getBounds(item);
    }

    @Override
    public int getCurrentLocation() {
        return super.getCurrentLocation();
    }

    @Override
    public int getCurrentOperation() {
        return super.getCurrentOperation();
    }

    /**
* @see org.eclipse.jface.viewers.ViewerDropAdapter#overrideOperation(int)
* @since 3.4
*/
    @Override
    public void overrideOperation(int operation) {
        if (Policy.DEBUG_DND) {
            //$NON-NLS-1$
            System.out.println("CommonDropAdapter.overrideOperation: " + operation);
        }
        super.overrideOperation(operation);
    }

    @Override
    public Object getCurrentTarget() {
        return super.getCurrentTarget();
    }

    @Override
    public TransferData getCurrentTransfer() {
        return super.getCurrentTransfer();
    }
}
