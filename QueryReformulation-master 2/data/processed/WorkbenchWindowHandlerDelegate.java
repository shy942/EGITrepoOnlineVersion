/***/
package org.eclipse.ui.internal.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;

/**
* A handler that can be used to imitate a IWorkbenchWindowActionDelegate.
*
* @since 3.1
*/
public abstract class WorkbenchWindowHandlerDelegate extends ExecutableExtensionHandler implements IWorkbenchWindowHandlerDelegate {

    /**
* By default, this will do nothing. Subclasses may override.
*
* @param window
*            the window that provides the context for this delegate
* @see org.eclipse.ui.IWorkbenchWindowActionDelegate#init(IWorkbenchWindow)
*/
    @Override
    public void init(final IWorkbenchWindow window) {
    // Do nothing by default.
    }

    /**
* This simply calls execute with a <code>null</code> map of parameter
* values. If an <code>ExecutionException</code> occurs, then this should
* be handle somehow. It's not clear what we'll do yet.
*
* @param action
*            The action proxy that handles the presentation portion of the
*            action
* @see org.eclipse.ui.IActionDelegate#run(IAction)
*/
    @Override
    public void run(final IAction action) {
        try {
            execute(new ExecutionEvent());
        } catch (final ExecutionException e) {
        }
    }

    /**
* By default, this will do nothing. Subclasses may override.
*
* @param action
*            The action proxy that handles presentation portion of the
*            action
* @param selection
*            The current selection, or <code>null</code> if there is no
*            selection.
*
* @see org.eclipse.ui.IActionDelegate#selectionChanged(IAction, ISelection)
*/
    @Override
    public void selectionChanged(IAction action, ISelection selection) {
    // Do nothing be default.
    }
}
