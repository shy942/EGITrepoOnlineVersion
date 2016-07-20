/***/
package org.eclipse.jface.operation;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IProgressMonitorWithBlocking;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.ProgressMonitorWrapper;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.widgets.Display;

/* package */
class AccumulatingProgressMonitor extends ProgressMonitorWrapper {

    /**
* The display.
*/
    private Display display;

    /**
* The collector, or <code>null</code> if none.
*/
    private Collector collector;

    //$NON-NLS-1$
    private String currentTask = "";

    private class Collector implements Runnable {

        private String taskName;

        private String subTask;

        private double worked;

        private IProgressMonitor monitor;

        /**
* Create a new collector.
*
* @param taskName
* @param subTask
* @param work
* @param monitor
*/
        public  Collector(String taskName, String subTask, double work, IProgressMonitor monitor) {
            this.taskName = taskName;
            this.subTask = subTask;
            this.worked = work;
            this.monitor = monitor;
        }

        /**
* Set the task name
*
* @param name
*/
        public void setTaskName(String name) {
            this.taskName = name;
        }

        /**
* Add worked to the work.
* @param workedIncrement
*/
        public void worked(double workedIncrement) {
            this.worked = this.worked + workedIncrement;
        }

        /**
* Set the subTask name.
* @param subTaskName
*/
        public void subTask(String subTaskName) {
            this.subTask = subTaskName;
        }

        /**
* Run the collector.
*/
        @Override
        public void run() {
            clearCollector(this);
            if (taskName != null) {
                monitor.setTaskName(taskName);
            }
            if (subTask != null) {
                monitor.subTask(subTask);
            }
            if (worked > 0) {
                monitor.internalWorked(worked);
            }
        }
    }

    /**
* Creates an accumulating progress monitor wrapping the given one
* that uses the given display.
*
* @param monitor the actual progress monitor to be wrapped
* @param display the SWT display used to forward the calls
*  to the wrapped progress monitor
*/
    public  AccumulatingProgressMonitor(IProgressMonitor monitor, Display display) {
        super(monitor);
        Assert.isNotNull(display);
        this.display = display;
    }

    @Override
    public void beginTask(final String name, final int totalWork) {
        synchronized (this) {
            collector = null;
        }
        display.asyncExec(() -> {
            currentTask = name;
            getWrappedProgressMonitor().beginTask(name, totalWork);
        });
    }

    /**
* Clears the collector object used to accumulate work and subtask calls if
* it matches the given one.
*
* @param collectorToClear
*/
    private synchronized void clearCollector(Collector collectorToClear) {
        // If not, don't clear it.
        if (this.collector == collectorToClear) {
            this.collector = null;
        }
    }

    /**
* Creates a collector object to accumulate work and subtask calls.
*
* @param subTask
* @param work
*/
    private void createCollector(String taskName, String subTask, double work) {
        collector = new Collector(taskName, subTask, work, getWrappedProgressMonitor());
        display.asyncExec(collector);
    }

    @Override
    public void done() {
        synchronized (this) {
            collector = null;
        }
        display.asyncExec(() -> getWrappedProgressMonitor().done());
    }

    @Override
    public synchronized void internalWorked(final double work) {
        if (collector == null) {
            createCollector(null, null, work);
        } else {
            collector.worked(work);
        }
    }

    @Override
    public synchronized void setTaskName(final String name) {
        currentTask = name;
        if (collector == null) {
            createCollector(name, null, 0);
        } else {
            collector.setTaskName(name);
        }
    }

    @Override
    public synchronized void subTask(final String name) {
        if (collector == null) {
            createCollector(null, name, 0);
        } else {
            collector.subTask(name);
        }
    }

    @Override
    public synchronized void worked(int work) {
        internalWorked(work);
    }

    @Override
    public void clearBlocked() {
        //If this is a monitor that can report blocking do so.
        //Don't bother with a collector as this should only ever
        //happen once and prevent any more progress.
        final IProgressMonitor pm = getWrappedProgressMonitor();
        if (!(pm instanceof IProgressMonitorWithBlocking)) {
            return;
        }
        display.asyncExec(() -> {
            ((IProgressMonitorWithBlocking) pm).clearBlocked();
            Dialog.getBlockedHandler().clearBlocked();
        });
    }

    @Override
    public void setBlocked(final IStatus reason) {
        //If this is a monitor that can report blocking do so.
        //Don't bother with a collector as this should only ever
        //happen once and prevent any more progress.
        final IProgressMonitor pm = getWrappedProgressMonitor();
        if (!(pm instanceof IProgressMonitorWithBlocking)) {
            return;
        }
        display.asyncExec(() -> {
            ((IProgressMonitorWithBlocking) pm).setBlocked(reason);
            //Do not give a shell as we want it to block until it opens.
            Dialog.getBlockedHandler().showBlocked(pm, reason, currentTask);
        });
    }
}
