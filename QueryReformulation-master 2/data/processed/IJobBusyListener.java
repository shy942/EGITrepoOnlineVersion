/***/
package org.eclipse.ui.internal.progress;

import org.eclipse.core.runtime.jobs.Job;

/**
* The IJobBusyListener is used to listen for running and
* terminating of jobs of a particular family.
*/
interface IJobBusyListener {

    /**
* Increment the busy count for job.
* @param job
*/
    public void incrementBusy(Job job);

    /**
* Decrement the busy count for job.
* @param job
*/
    public void decrementBusy(Job job);
}
