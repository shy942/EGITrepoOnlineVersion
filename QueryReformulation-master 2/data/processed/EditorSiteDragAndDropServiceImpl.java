/***/
package org.eclipse.ui.internal;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTarget;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.DropTargetListener;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dnd.IDragAndDropService;
import org.eclipse.ui.services.IDisposable;

/**
* Implementation for the <code>IDragAndDropService</code> to be used from
* <code>EditorSite</code>'s.
* </p><p>
* Adds a drop target to the given control that merges the site's
* drop behaviour with that specified by the <code>addMergedDropTarget</code> call.
* </p><p>
* The current implementation is only defined for EditorSite's and merges the
* given drop handling with the existing EditorSashContainer's behaviour.
* </p><p>
* NOTE: There is no cleanup (i.e. 'dispose') handling necessary for merged
* Drop Targets but the hooks are put into place to maintain the consistency
* of the implementation pattern.
* </p>
* @since 3.3
*
*/
public class EditorSiteDragAndDropServiceImpl implements IDragAndDropService, IDisposable {

    // Key used to store/retrieve the MergedDropTarget instance from the real DropTarget
    //$NON-NLS-1$
    private static String MDT_KEY = "MDT";

    /**
* Implementation of a DropTarget wrapper that will either delegate to the
* <code>primaryListener</code> if the event's <code>currentDataType</code>
* can be handled by it; otherwise the event is forwarded on to the
* listener specified by <code>secondaryListener</code>.
* </p><p>
* NOTE: we should perhaps refactor this out into an external class
* </p>
* @since 3.3
*
*/
    private static class MergedDropTarget {

        private DropTarget realDropTarget;

        private Transfer[] secondaryTransfers;

        private DropTargetListener secondaryListener;

        private int secondaryOps;

        private Transfer[] primaryTransfers;

        private DropTargetListener primaryListener;

        private int primaryOps;

        public  MergedDropTarget(Control control, int priOps, Transfer[] priTransfers, DropTargetListener priListener, int secOps, Transfer[] secTransfers, DropTargetListener secListener) {
            realDropTarget = new DropTarget(control, priOps | secOps);
            realDropTarget.setData(MDT_KEY, this);
            // Cache the editor's transfers and listener
            primaryTransfers = priTransfers;
            primaryListener = priListener;
            primaryOps = priOps;
            // Cache the editor area's current transfers & listener
            secondaryTransfers = secTransfers;
            secondaryListener = secListener;
            secondaryOps = secOps;
            // Combine the two sets of transfers into one array
            Transfer[] allTransfers = new Transfer[secondaryTransfers.length + primaryTransfers.length];
            int curTransfer = 0;
            for (int i = 0; i < primaryTransfers.length; i++) {
                allTransfers[curTransfer++] = primaryTransfers[i];
            }
            for (int i = 0; i < secondaryTransfers.length; i++) {
                allTransfers[curTransfer++] = secondaryTransfers[i];
            }
            realDropTarget.setTransfer(allTransfers);
            // Create a listener that will delegate to the appropriate listener
            // NOTE: the -editor- wins (i.e. it can over-ride WB behaviour if it wants
            realDropTarget.addDropListener(new DropTargetListener() {

                @Override
                public void dragEnter(DropTargetEvent event) {
                    getAppropriateListener(event, true).dragEnter(event);
                }

                @Override
                public void dragLeave(DropTargetEvent event) {
                    getAppropriateListener(event, false).dragLeave(event);
                }

                @Override
                public void dragOperationChanged(DropTargetEvent event) {
                    getAppropriateListener(event, true).dragOperationChanged(event);
                }

                @Override
                public void dragOver(DropTargetEvent event) {
                    getAppropriateListener(event, true).dragOver(event);
                }

                @Override
                public void drop(DropTargetEvent event) {
                    getAppropriateListener(event, true).drop(event);
                }

                @Override
                public void dropAccept(DropTargetEvent event) {
                    getAppropriateListener(event, true).dropAccept(event);
                }
            });
        }

        private DropTargetListener getAppropriateListener(DropTargetEvent event, boolean checkOperation) {
            if (isSupportedType(primaryTransfers, event.currentDataType)) {
                if (checkOperation && !isSupportedOperation(primaryOps, event.detail)) {
                    event.detail = DND.DROP_NONE;
                }
                return primaryListener;
            }
            if (checkOperation && !isSupportedOperation(secondaryOps, event.detail)) {
                event.detail = DND.DROP_NONE;
            }
            return secondaryListener;
        }

        private boolean isSupportedType(Transfer[] transfers, TransferData transferType) {
            for (int i = 0; i < transfers.length; i++) {
                if (transfers[i].isSupportedType(transferType))
                    return true;
            }
            return false;
        }

        private boolean isSupportedOperation(int dropOps, int eventDetail) {
            return ((dropOps | DND.DROP_DEFAULT) & eventDetail) != 0;
        }
    }

    // Cache any listeners for cleanup
    List addedListeners = new ArrayList();

    @Override
    public void addMergedDropTarget(Control control, int ops, Transfer[] transfers, DropTargetListener listener) {
        // First we have to remove any existing drop target from the control
        removeMergedDropTarget(control);
        // Capture the editor area's current ops, transfers & listener
        int editorSiteOps = DND.DROP_DEFAULT | DND.DROP_COPY | DND.DROP_LINK;
        WorkbenchWindow ww = (WorkbenchWindow) PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        WorkbenchWindowConfigurer winConfigurer = ww.getWindowConfigurer();
        Transfer[] editorSiteTransfers = winConfigurer.getTransfers();
        DropTargetListener editorSiteListener = winConfigurer.getDropTargetListener();
        // Create a new 'merged' drop Listener using combination of the desired
        // transfers and the ones used by the EditorArea
        MergedDropTarget newTarget = new MergedDropTarget(control, ops, transfers, listener, editorSiteOps, editorSiteTransfers, editorSiteListener);
        addedListeners.add(newTarget);
        newTarget.realDropTarget.addDisposeListener(new DisposeListener() {

            @Override
            public void widgetDisposed(DisposeEvent e) {
                Object mdt = e.widget.getData(MDT_KEY);
                addedListeners.remove(mdt);
            }
        });
    }

    /**
* This method will return the current drop target for the control
* (whether or not it was created using this service.
*
* @param control The control to get the drop target for
* @return The DropTarget for that control (could be null
*/
    private DropTarget getCurrentDropTarget(Control control) {
        if (control == null)
            return null;
        Object curDT = control.getData(DND.DROP_TARGET_KEY);
        return (DropTarget) curDT;
    }

    @Override
    public void removeMergedDropTarget(Control control) {
        DropTarget targetForControl = getCurrentDropTarget(control);
        if (targetForControl != null) {
            targetForControl.dispose();
            addedListeners.remove(targetForControl);
        }
    }

    @Override
    public void dispose() {
        addedListeners.clear();
    }
}