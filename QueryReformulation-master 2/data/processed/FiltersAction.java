/***/
package org.eclipse.ui.views.tasklist;

import org.eclipse.jface.window.Window;
import org.eclipse.ui.PlatformUI;

/**
* This action opens an editor for the resource
* associated with the selected marker, and
* jumps to the marker's location in the editor.
*/
class FiltersAction extends TaskAction {

    /**
* Creates the action.
* @param tasklist the task list
* @param id the id
*/
    public  FiltersAction(TaskList tasklist, String id) {
        super(tasklist, id);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, ITaskListHelpContextIds.FILTERS_ACTION);
    }

    /**
* Performs this action.
*/
    @Override
    public void run() {
        FiltersDialog dialog = new FiltersDialog(getShell());
        TasksFilter filter = getTaskList().getFilter();
        dialog.setFilter(filter);
        int result = dialog.open();
        if (result == Window.OK) {
            getTaskList().filterChanged();
        }
    }
}
