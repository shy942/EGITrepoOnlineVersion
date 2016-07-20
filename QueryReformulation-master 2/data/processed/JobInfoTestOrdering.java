/***/
package org.eclipse.ui.tests.progress;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.ui.internal.progress.JobInfo;
import org.junit.Before;
import org.junit.Test;

public class JobInfoTestOrdering {

    private List jobinfos = new ArrayList();

    @Before
    public void setUp() throws Exception {
        jobinfos.clear();
        int counter = 0;
        TestJob job;
        JobInfo ji;
        job = new TestJob("Job" + (counter++));
        job.setUser(true);
        job.setSystem(false);
        job.setPriority(Job.INTERACTIVE);
        // JOB STATE
        job.setInternalJobState(Job.NONE);
        ji = new ExtendedJobInfo(job);
        jobinfos.add(ji);
        job = new TestJob("Job" + (counter++));
        job.setUser(true);
        job.setSystem(false);
        job.setPriority(Job.INTERACTIVE);
        // JOB STATE
        job.setInternalJobState(Job.SLEEPING);
        ji = new ExtendedJobInfo(job);
        jobinfos.add(ji);
        job = new TestJob("Job" + (counter++));
        job.setUser(true);
        job.setSystem(false);
        job.setPriority(Job.INTERACTIVE);
        // JOB STATE
        job.setInternalJobState(Job.WAITING);
        ji = new ExtendedJobInfo(job);
        jobinfos.add(ji);
        job = new TestJob("Job" + (counter++));
        job.setUser(true);
        job.setSystem(false);
        job.setPriority(Job.INTERACTIVE);
        // JOB STATE
        job.setInternalJobState(Job.RUNNING);
        ji = new ExtendedJobInfo(job);
        jobinfos.add(ji);
    }

    /**
* Test that checks when jobs sorted by their state, the running ones
* are ordered to first place
*/
    @Test
    public void testJobStateOrdering() {
        Collections.sort(jobinfos);
        assertEquals(Job.RUNNING, ((JobInfo) jobinfos.get(0)).getJob().getState());
        assertEquals(Job.WAITING, ((JobInfo) jobinfos.get(1)).getJob().getState());
        assertEquals(Job.SLEEPING, ((JobInfo) jobinfos.get(2)).getJob().getState());
        assertEquals(Job.NONE, ((JobInfo) jobinfos.get(3)).getJob().getState());
    }
}
