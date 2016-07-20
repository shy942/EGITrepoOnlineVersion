/***/
package org.eclipse.e4.ui.internal.workbench.swt;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.ui.workbench.IWorkbench;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.testing.TestableObject;

/**
* The Workbench's testable object facade to a test harness.
*
* @since 3.0
*/
public class E4Testable extends TestableObject {

    private Display display;

    private IWorkbench workbench;

    private boolean oldAutomatedMode;

    private boolean oldIgnoreErrors;

    /**
* Constructs a new workbench testable object.
*/
    public  E4Testable() {
    // do nothing
    }

    /**
* Initializes the workbench testable with the display and workbench, and
* notifies all listeners that the tests can be run.
*
* @param display
*            the display
* @param workbench
*            the workbench
*/
    public void init(Display display, IWorkbench workbench) {
        Assert.isNotNull(display);
        Assert.isNotNull(workbench);
        this.display = display;
        this.workbench = workbench;
        if (getTestHarness() != null) {
            // don't use a job, since tests often wait for all jobs to complete
            // before proceeding
            Runnable runnable = new Runnable() {

                @Override
                public void run() {
                    // regression caused by workbench harness change
                    if (!"false".equalsIgnoreCase(System.getProperty("org.eclipse.ui.testsWaitForEarlyStartup"))) {
                        //$NON-NLS-1$ //$NON-NLS-2$
                        waitForEarlyStartup();
                    }
                    getTestHarness().runTests();
                }
            };
            //$NON-NLS-1$
            new Thread(runnable, "WorkbenchTestable").start();
        }
    }

    /**
* Waits for the early startup job to complete.
*/
    private void waitForEarlyStartup() {
        try {
            Job.getJobManager().join("earlyStartup", null);
        } catch (OperationCanceledException e) {
        } catch (InterruptedException e) {
        }
    }

    /**
* The <code>WorkbenchTestable</code> implementation of this
* <code>TestableObject</code> method ensures that the workbench has been
* set.
*/
    @Override
    public void testingStarting() {
        Assert.isNotNull(workbench);
        oldAutomatedMode = ErrorDialog.AUTOMATED_MODE;
        ErrorDialog.AUTOMATED_MODE = true;
        oldIgnoreErrors = SafeRunnable.getIgnoreErrors();
        SafeRunnable.setIgnoreErrors(true);
    }

    /**
* The <code>WorkbenchTestable</code> implementation of this
* <code>TestableObject</code> method flushes the event queue, runs the test
* in a <code>syncExec</code>, then flushes the event queue again.
*/
    @Override
    public void runTest(Runnable testRunnable) {
        Assert.isNotNull(workbench);
        display.syncExec(testRunnable);
    }

    /**
* The <code>WorkbenchTestable</code> implementation of this
* <code>TestableObject</code> method flushes the event queue, then closes
* the workbench.
*/
    @Override
    public void testingFinished() {
        // force events to be processed, and ensure the close is done in the UI
        // thread
        display.syncExec(new Runnable() {

            @Override
            public void run() {
                Assert.isTrue(workbench.close());
            }
        });
        ErrorDialog.AUTOMATED_MODE = oldAutomatedMode;
        SafeRunnable.setIgnoreErrors(oldIgnoreErrors);
    }
}
