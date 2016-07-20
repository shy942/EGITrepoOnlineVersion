/***/
package org.eclipse.ui.progress;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;

/**
* Interface for runnables that can be run as jobs.
*
* @since 3.3
*/
public interface IJobRunnable {

    /**
* Executes this runnable. Returns the result of the execution.
* <p>
* The provided monitor can be used to report progress and respond to
* cancellation. If the progress monitor has been canceled, the runnable should
* finish its execution at the earliest convenience and return a result
* status of severity <code>IStatus.CANCEL</code>. The singleton cancel
* status <code>Status.CANCEL_STATUS</code> can be used for this purpose.
* <p>
*
* @param monitor
*            the monitor to be used for reporting progress and responding
*            to cancelation. The monitor is never <code>null</code>
* @return resulting status of the run. The result must not be
*         <code>null</code>
*/
    public IStatus run(IProgressMonitor monitor);
}
