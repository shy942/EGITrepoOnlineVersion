/***/
package org.eclipse.ui.tests.concurrency;

import java.lang.reflect.InvocationTargetException;
import junit.framework.TestCase;
import org.eclipse.core.resources.*;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

/**
* Tests the following sequence of events:
* 1) acquire a lock in the UI thread
* 2) execute an operation (e.g. MoveFilesAndFoldersOperation) which spawns a modal context thread
* 3) modal context tries to acquire lock held by UI thread
*
* This sequence would cause a deadlock, so an exception is thrown by ModalContext.
* This test asserts that the exception is thrown and that deadlock does not occur.
*/
public class TestBug108162 extends TestCase {

    class LockAcquiringOperation extends WorkspaceModifyOperation {

        @Override
        public void execute(final IProgressMonitor pm) {
        //empty operation is sufficient to cause deadlock
        }
    }

    private IWorkspace workspace = ResourcesPlugin.getWorkspace();

    public  TestBug108162() {
        super();
    }

    public  TestBug108162(String name) {
        super(name);
    }

    /**
* Performs the test
*/
    public void testBug() throws CoreException {
        workspace.run(new IWorkspaceRunnable() {

            @Override
            public void run(IProgressMonitor monitor) {
                ProgressMonitorDialog dialog = new ProgressMonitorDialog(new Shell());
                try {
                    dialog.run(true, false, new LockAcquiringOperation());
                    //should not succeed
                    assertTrue("Should not get here", false);
                } catch (InvocationTargetException e) {
                } catch (InterruptedException e) {
                } catch (IllegalStateException e) {
                }
            }
        }, workspace.getRoot(), IResource.NONE, null);
    }
}
