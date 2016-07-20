/***/
package org.eclipse.ui.views.tasklist;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.PlatformUI;

/**
* This action opens the properties dialog for the current task.
*/
class TaskPropertiesAction extends TaskAction {

    /**
* Creates the action.
*
* @param tasklist the task list
* @param id the id
*/
    public  TaskPropertiesAction(TaskList tasklist, String id) {
        super(tasklist, id);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, ITaskListHelpContextIds.TASK_PROPERTIES_ACTION);
    }

    /**
* Performs this action.
*/
    @Override
    public void run() {
        IStructuredSelection sel = (IStructuredSelection) getTaskList().getSelection();
        Object o = sel.getFirstElement();
        if (o instanceof IMarker) {
            TaskPropertiesDialog dialog = new TaskPropertiesDialog(getShell());
            dialog.setMarker((IMarker) o);
            dialog.open();
        }
    }
}
