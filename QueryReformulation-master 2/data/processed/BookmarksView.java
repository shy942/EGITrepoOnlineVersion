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
* The BookmarksView is the ide view for bookmarks.
* @since 3.4
*
*/
public class BookmarksView extends MarkerSupportView {

    /**
* Create a new instance of the receiver.
*/
    public  BookmarksView() {
        super(MarkerSupportRegistry.BOOKMARKS_GENERATOR);
    }

    @Override
    protected IUndoContext getUndoContext() {
        return WorkspaceUndoUtil.getBookmarksUndoContext();
    }

    @Override
    protected String getDeleteOperationName(IMarker[] markers) {
        Assert.isLegal(markers.length > 0);
        return markers.length == 1 ? MarkerMessages.deleteBookmarkMarker_operationName : MarkerMessages.deleteBookmarkMarkers_operationName;
    }
}
