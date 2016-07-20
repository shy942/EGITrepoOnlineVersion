/***/
package org.eclipse.jface.tests.window;

import junit.framework.TestCase;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IProgressMonitorWithBlocking;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.window.ApplicationWindow;
import org.eclipse.swt.widgets.Shell;

public class ApplicationWindowTest extends TestCase {

    private ApplicationWindow window;

    @Override
    protected void tearDown() throws Exception {
        if (window != null) {
            // close the window
            window.close();
            window = null;
        }
        super.tearDown();
    }

    private void testBug334093(boolean fork, boolean cancelable) throws Exception {
        window = new ApplicationWindow(null) {

            @Override
            public void create() {
                addStatusLine();
                super.create();
            }

            @Override
            protected void createTrimWidgets(Shell shell) {
            // don't actually create the status line controls
            }
        };
        window.create();
        window.run(fork, cancelable, new IRunnableWithProgress() {

            @Override
            public void run(IProgressMonitor monitor) {
                monitor.beginTask("beginTask", 10);
                monitor.setTaskName("setTaskName");
                monitor.subTask("subTask");
                if (monitor instanceof IProgressMonitorWithBlocking) {
                    IProgressMonitorWithBlocking blockingMonitor = (IProgressMonitorWithBlocking) monitor;
                    blockingMonitor.setBlocked(Status.CANCEL_STATUS);
                    blockingMonitor.clearBlocked();
                }
                monitor.worked(1);
                monitor.setCanceled(true);
                monitor.isCanceled();
                monitor.setCanceled(false);
                monitor.done();
            }
        });
    }

    public void testBug334093() throws Exception {
        boolean[] options = new boolean[] { true, false };
        for (boolean forkOption : options) {
            for (boolean cancelableOpton : options) {
                testBug334093(forkOption, cancelableOpton);
            }
        }
    }
}
