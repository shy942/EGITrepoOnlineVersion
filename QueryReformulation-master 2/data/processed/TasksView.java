/***/
package org.eclipse.ui.internal.views.markers;

import org.eclipse.core.commands.operations.IUndoContext;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.Assert;
import org.eclipse.ui.ide.undo.WorkspaceUndoUtil;
import org.eclipse.ui.views.markers.MarkerSupportView;
import org.eclipse.ui.views.markers.internal.MarkerMessages;
import org.eclipse.ui.views.markers.internal.MarkerSupportRegistry;

/**
* TasksView is the ide view for showing tasks.
* @since 3.4
*
*/
public class TasksView extends MarkerSupportView {

    /**
* Create a new instance of the receiver.
*/
    public  TasksView() {
        super(MarkerSupportRegistry.TASKS_GENERATOR);
    }

    @Override
    protected IUndoContext getUndoContext() {
        return WorkspaceUndoUtil.getTasksUndoContext();
    }

    @Override
    protected String getDeleteOperationName(IMarker[] markers) {
        Assert.isLegal(markers.length > 0);
        return markers.length == 1 ? MarkerMessages.deleteTaskMarker_operationName : MarkerMessages.deleteTaskMarkers_operationName;
    }
}