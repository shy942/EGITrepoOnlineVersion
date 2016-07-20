/***/
package org.eclipse.ui.tests.concurrency;

import java.lang.reflect.InvocationTargetException;
import junit.framework.TestCase;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.operation.IThreadListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

/**
* Makes ModalContext thread crash and hang the IDE
*/
public class ModalContextCrashTest extends TestCase {

    public void testCrash() throws Exception {
        IRunnableWithProgress operation = new CrashingRunnable();
        try {
            PlatformUI.getWorkbench().getActiveWorkbenchWindow().run(true, false, operation);
            fail("Should have an invocation target exception");
        } catch (InvocationTargetException e) {
        }
    }

    private static final class CrashingRunnable implements IRunnableWithProgress, IThreadListener {

        @Override
        public void run(IProgressMonitor monitor) {
        }

        @Override
        public void threadChange(Thread thread) {
            // of ModalContextThread
            if (Display.findDisplay(thread) != null) {
                throw new RuntimeException("Simulated exception during threadChange");
            }
        }
    }
}
