/***/
package org.eclipse.ui.tests.progress;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.Job;

/**
*
* A job that completes very quicky and returns the specified status
*
* @since 3.6
* @author Prakash G.R. (grprakash@in.ibm.com)
*
*/
public class DummyJob extends Job {

    private final IStatus status;

    public boolean inProgress = false;

    /** if false, infinite until changed or job is cancelled */
    public boolean shouldFinish = true;

    public  DummyJob(String name, IStatus status) {
        super(name);
        this.status = status;
    }

    @Override
    protected IStatus run(IProgressMonitor monitor) {
        // spare us from registering a job change listener
        inProgress = true;
        monitor.beginTask(getName() + " starts now", 10);
        try {
            for (int i = 0; i < 10 || !shouldFinish; i++) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                }
                monitor.worked(1);
                if (monitor.isCanceled()) {
                    break;
                }
            }
            return status;
        } finally {
            monitor.done();
        }
    }
}
