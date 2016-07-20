/***/
package org.eclipse.ui.internal.views.markers;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.ide.undo.DeleteMarkersOperation;
import org.eclipse.ui.ide.undo.WorkspaceUndoUtil;
import org.eclipse.ui.views.markers.MarkerItem;
import org.eclipse.ui.views.markers.MarkerViewHandler;
import org.eclipse.ui.views.markers.internal.MarkerMessages;

/**
* DeleteCompletedHandler is the handler for the deletion of completed
* tasks.
* @since 3.4
*
*/
public class DeleteCompletedHandler extends MarkerViewHandler {

    @Override
    public Object execute(ExecutionEvent event) {
        ExtendedMarkersView view = getView(event);
        if (view == null)
            return this;
        final List<IMarker> completed = getCompletedTasks(view);
        // Check if there is anything to do
        if (completed.size() == 0) {
            MessageDialog.openInformation(view.getSite().getShell(), MarkerMessages.deleteCompletedTasks_dialogTitle, MarkerMessages.deleteCompletedTasks_noneCompleted);
            return this;
        }
        String message;
        if (completed.size() == 1) {
            message = MarkerMessages.deleteCompletedTasks_permanentSingular;
        } else {
            message = NLS.bind(MarkerMessages.deleteCompletedTasks_permanentPlural, String.valueOf(completed.size()));
        }
        // Verify.
        if (!MessageDialog.openConfirm(view.getSite().getShell(), MarkerMessages.deleteCompletedTasks_dialogTitle, message)) {
            return view;
        }
        IMarker[] markers = new IMarker[completed.size()];
        completed.toArray(markers);
        IUndoableOperation op = new DeleteMarkersOperation(markers, MarkerMessages.deleteCompletedAction_title);
        execute(op, MarkerMessages.deleteCompletedTasks_errorMessage, null, WorkspaceUndoUtil.getUIInfoAdapter(view.getSite().getShell()));
        return this;
    }

    /**
* Get the list of completed tasks from the view.
*
* @param view
* @return List of {@link IMarker}
*/
    private List<IMarker> getCompletedTasks(ExtendedMarkersView view) {
        List<IMarker> completed = new ArrayList();
        MarkerItem[] items = view.getAllConcreteItems();
        for (int i = 0; i < items.length; i++) {
            MarkerItem markerItem = items[i];
            if (markerItem.getAttributeValue(IMarker.DONE, false) && markerItem.getMarker() != null) {
                completed.add(markerItem.getMarker());
            }
        }
        return completed;
    }
}
