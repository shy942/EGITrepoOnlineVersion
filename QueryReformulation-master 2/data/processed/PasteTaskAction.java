/***/
package org.eclipse.ui.views.tasklist;

import java.util.ArrayList;
import java.util.Map;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.undo.CreateMarkersOperation;
import org.eclipse.ui.ide.undo.WorkspaceUndoUtil;
import org.eclipse.ui.internal.views.tasklist.TaskListMessages;
import org.eclipse.ui.part.MarkerTransfer;

/**
* Standard action for pasting tasks from the clipboard.
* <p>
* This class may be instantiated; it is not intended to be subclassed.
* </p>
*
* @since 2.0
*/
class PasteTaskAction extends TaskAction {

    /**
* Creates a new action.
*
* @param tasklist the task list
* @param id the id
*/
    public  PasteTaskAction(TaskList tasklist, String id) {
        super(tasklist, id);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, ITaskListHelpContextIds.PASTE_TASK_ACTION);
    }

    /**
* Implementation of method defined on <code>IAction</code>.
*/
    @Override
    public void run() {
        // Get the markers from the clipboard
        MarkerTransfer transfer = MarkerTransfer.getInstance();
        final IMarker[] markerData = (IMarker[]) getTaskList().getClipboard().getContents(transfer);
        if (markerData == null) {
            return;
        }
        final ArrayList newMarkerAttributes = new ArrayList();
        final ArrayList newMarkerResources = new ArrayList();
        try {
            getTaskList().getWorkspace().run( monitor -> {
                for (int i = 0; i < markerData.length; i++) {
                    if (!markerData[i].getType().equals(IMarker.TASK)) {
                        continue;
                    }
                    newMarkerResources.add(markerData[i].getResource());
                    newMarkerAttributes.add(markerData[i].getAttributes());
                }
            }, null);
        } catch (CoreException e) {
            ErrorDialog.openError(getShell(), TaskListMessages.PasteTask_errorMessage, null, e.getStatus());
            return;
        }
        final Map[] attrs = (Map[]) newMarkerAttributes.toArray(new Map[newMarkerAttributes.size()]);
        final IResource[] resources = (IResource[]) newMarkerResources.toArray(new IResource[newMarkerResources.size()]);
        final CreateMarkersOperation op = new CreateMarkersOperation(IMarker.TASK, attrs, resources, getText());
        execute(op, TaskListMessages.PasteTask_errorMessage, null, WorkspaceUndoUtil.getUIInfoAdapter(getShell()));
        // sent after the operation is executed.
        if (op.getMarkers() != null) {
            getShell().getDisplay().asyncExec(() -> {
                TaskList taskList = getTaskList();
                taskList.setSelection(new StructuredSelection(op.getMarkers()), true);
            });
        }
    }
}
