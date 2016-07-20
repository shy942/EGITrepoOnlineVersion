/***/
package org.eclipse.ui.examples.jobs.actions;

import org.eclipse.core.internal.jobs.JobManager;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

/**
* This class is not really sample code.  This action is used to gather debugging
* information about the internal state of the background job scheduling
* mechanism.
*/
public class DebugJobManagerAction implements IWorkbenchWindowActionDelegate {

    public  DebugJobManagerAction() {
        super();
    }

    @Override
    public void dispose() {
    //
    }

    @Override
    public void init(IWorkbenchWindow window) {
    //
    }

    @Override
    public void run(IAction action) {
        //$NON-NLS-1$
        System.out.println("**** BEGIN DUMP JOB MANAGER INFORMATION ****");
        Job[] jobs = Platform.getJobManager().find(null);
        for (int i = 0; i < jobs.length; i++) {
            //$NON-NLS-1$ //$NON-NLS-2$
            System.out.println("" + jobs[i].getClass().getName() + " state: " + JobManager.printState(jobs[i].getState()));
        }
        //$NON-NLS-1$
        System.out.println("**** END DUMP JOB MANAGER INFORMATION ****");
    }

    @Override
    public void selectionChanged(IAction action, ISelection selection) {
    //
    }
}
