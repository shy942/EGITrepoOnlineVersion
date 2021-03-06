/***/
package org.eclipse.ui.views.bookmarkexplorer;

import java.util.Iterator;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.util.OpenStrategy;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.internal.views.bookmarkexplorer.BookmarkMessages;

/**
* Action to open an editor on the selected bookmarks.
*/
class OpenBookmarkAction extends BookmarkAction {

    /**
* Create a new instance of this class.
*
* @param view the view
*/
    public  OpenBookmarkAction(BookmarkNavigator view) {
        super(view, BookmarkMessages.OpenBookmark_text);
        setToolTipText(BookmarkMessages.OpenBookmark_toolTip);
        PlatformUI.getWorkbench().getHelpSystem().setHelp(this, IBookmarkHelpContextIds.OPEN_BOOKMARK_ACTION);
        setEnabled(false);
    }

    @Override
    public void run() {
        IWorkbenchPage page = getView().getSite().getPage();
        for (Iterator i = getStructuredSelection().iterator(); i.hasNext(); ) {
            IMarker marker = (IMarker) i.next();
            try {
                IDE.openEditor(page, marker, OpenStrategy.activateOnOpen());
            } catch (PartInitException e) {
                CoreException nestedException = null;
                IStatus status = e.getStatus();
                if (status != null && status.getException() instanceof CoreException) {
                    nestedException = (CoreException) status.getException();
                }
                if (nestedException != null) {
                    ErrorDialog.openError(getView().getShell(), BookmarkMessages.OpenBookmark_errorTitle, e.getMessage(), nestedException.getStatus());
                } else {
                    MessageDialog.openError(getView().getShell(), BookmarkMessages.OpenBookmark_errorTitle, e.getMessage());
                }
            }
        }
    }

    @Override
    public void selectionChanged(IStructuredSelection sel) {
        setEnabled(!sel.isEmpty());
    }
}
