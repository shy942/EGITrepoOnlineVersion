/***/
package org.eclipse.ui.examples.jobs;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.*;
import org.eclipse.ui.progress.UIJob;

/**
* Base class for a simple test UI job with configurable parameters
*/
public class UITestJob extends UIJob {

    private long duration;

    private boolean failure;

    private boolean unknown;

    public  UITestJob(long duration, boolean lock, boolean failure, boolean indeterminate) {
        //$NON-NLS-1$
        super("Test job");
        this.duration = duration;
        this.failure = failure;
        this.unknown = indeterminate;
        if (lock)
            setRule(ResourcesPlugin.getWorkspace().getRoot());
    }

    @Override
    public IStatus runInUIThread(IProgressMonitor monitor) {
        if (failure)
            throw new RuntimeException();
        final long sleep = 10;
        int ticks = (int) (duration / sleep);
        if (unknown)
            monitor.beginTask(toString(), IProgressMonitor.UNKNOWN);
        else
            monitor.beginTask(toString(), ticks);
        try {
            for (int i = 0; i < ticks; i++) {
                if (monitor.isCanceled())
                    return Status.CANCEL_STATUS;
                //$NON-NLS-1$
                monitor.subTask("Processing tick #" + i);
                try {
                    Thread.sleep(sleep);
                } catch (InterruptedException e) {
                    return Status.CANCEL_STATUS;
                }
                monitor.worked(1);
            }
        } finally {
            monitor.done();
        }
        return Status.OK_STATUS;
    }
}
