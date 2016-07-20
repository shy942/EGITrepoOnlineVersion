/***/
package org.eclipse.ui.views.tasklist;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.ui.PlatformUI;

/**
* This action selects all tasks currently showing in the task list.
*/
class SelectAllTasksAction extends TaskAction {

    /**
* Creates the action.
*/
    protected  SelectAllTasksAction(TaskList tasklist, String id) {
        super(tasklist, id);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, ITaskListHelpContextIds.SELECT_ALL_TASKS_ACTION);
    }

    /**
* Selects all resources in the view.
*/
    @Override
    public void run() {
        getTaskList().cancelEditing();
        TableViewer viewer = getTaskList().getTableViewer();
        viewer.getTable().selectAll();
        // force viewer selection change
        viewer.setSelection(viewer.getSelection());
    }
}
