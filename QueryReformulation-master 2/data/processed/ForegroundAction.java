/***/
package org.eclipse.ui.examples.jobs.actions;

import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.*;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

public class ForegroundAction implements IWorkbenchWindowActionDelegate {

    @Override
    public void run(IAction action) {
        try {
            ResourcesPlugin.getWorkspace().run(new IWorkspaceRunnable() {

                @Override
                public void run(IProgressMonitor monitor) {
                //no-op
                }
            }, null);
        } catch (OperationCanceledException e) {
            e.printStackTrace();
        } catch (CoreException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void selectionChanged(IAction action, ISelection selection) {
    //do nothing
    }

    @Override
    public void dispose() {
    //do nothing
    }

    @Override
    public void init(IWorkbenchWindow window) {
    //do nothing
    }
}
