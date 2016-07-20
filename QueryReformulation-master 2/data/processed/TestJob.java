/***/
package org.eclipse.ui.tests.progress;

import java.lang.reflect.Field;
import org.eclipse.core.internal.jobs.InternalJob;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.jobs.Job;

class TestJob extends Job {

    public  TestJob(String name) {
        super(name);
    }

    @Override
    protected IStatus run(IProgressMonitor monitor) {
        throw new UnsupportedOperationException("Not implemented, because of just a unit test");
    }

    public void setInternalJobState(int state) {
        try {
            final Field field = InternalJob.class.getDeclaredField("flags");
            // hack for testing
            field.setAccessible(true);
            field.set(this, Integer.valueOf(state));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
