/***/
package org.eclipse.ui.views.bookmarkexplorer;

import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.operations.IUndoableOperation;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.SelectionProviderAction;
import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;

/**
* An abstract class for all bookmark view actions.
*/
abstract class BookmarkAction extends SelectionProviderAction {

    private BookmarkNavigator view;

    /**
* Creates a bookmark action.
*/
    protected  BookmarkAction(BookmarkNavigator view, String label) {
        super(view.getViewer(), label);
        this.view = view;
    }

    /**
* Returns the bookmarks view.
*/
    public BookmarkNavigator getView() {
        return view;
    }

    /**
* Execute the specified undoable operation
*/
    void execute(IUndoableOperation operation, String title, IProgressMonitor monitor, IAdaptable uiInfo) {
        try {
            PlatformUI.getWorkbench().getOperationSupport().getOperationHistory().execute(operation, monitor, uiInfo);
        } catch (ExecutionException e) {
            if (e.getCause() instanceof CoreException) {
                ErrorDialog.openError(view.getShell(), title, null, ((CoreException) e.getCause()).getStatus());
            } else {
                IDEWorkbenchPlugin.log(title, e);
            }
        }
    }
}
