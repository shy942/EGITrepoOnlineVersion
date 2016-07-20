/***/
package org.eclipse.ui.views.bookmarkexplorer;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Table;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.views.bookmarkexplorer.BookmarkMessages;

/**
* Action to select all bookmarks.
*/
class SelectAllAction extends BookmarkAction {

    /**
* Create a new instance of this class.
*
* @param view the view
*/
    public  SelectAllAction(BookmarkNavigator view) {
        super(view, BookmarkMessages.SelectAll_text);
        setToolTipText(BookmarkMessages.SelectAll_toolTip);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IBookmarkHelpContextIds.SELECT_ALL_BOOKMARK_ACTION);
        setEnabled(true);
    }

    @Override
    public void run() {
        Viewer viewer = getView().getViewer();
        Control control = viewer.getControl();
        if (control instanceof Table) {
            ((Table) control).selectAll();
            viewer.setSelection(viewer.getSelection(), false);
        }
    }
}
