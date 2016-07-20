/***/
package org.eclipse.jface.tests.databinding;

import org.eclipse.swt.widgets.Shell;

/**
* Abstract test case that handles disposing of the Shell after each test.
*
* @since 1.1
*/
public abstract class AbstractSWTTestCase extends AbstractDefaultRealmTestCase {

    private Shell shell;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        if (shell != null && !shell.isDisposed()) {
            shell.dispose();
        }
        super.tearDown();
    }

    /**
* Returns a shell to be used in a test. The shell is automatically disposed on tearDown.
*
* @return shell
* @see #createShell()
*/
    protected final Shell getShell() {
        if (shell == null || shell.isDisposed()) {
            shell = createShell();
        }
        return shell;
    }

    /**
* Returns a new shell to be used in a test. This method is called by {@link #getShell()}.
* It should not be called by test code, but it can be overridden to configure the created shell.
*
* @return shell
*/
    protected Shell createShell() {
        return new Shell();
    }
}
