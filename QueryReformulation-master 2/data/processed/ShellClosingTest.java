/***/
package org.eclipse.ui.tests.quickaccess;

import junit.framework.TestCase;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.junit.Test;

/**
* @since 3.5
*/
public class ShellClosingTest extends TestCase {

    public  ShellClosingTest() {
        super(ShellClosingTest.class.getName());
    }

    /**
* Bug 433746: dispose SearchField shell
*
* Testing bot (with code like in test sample) disposes parent shell of text
* control and it causes the issue because SearchField keeps another
* invisible shell which is not get disposed, so later when that shell is
* disposed it tried to accesses controls which are being disposed already.
*
* @throws Throwable
*/
    @Test
    public void testClosingShellsBug433746() throws Throwable {
        final Throwable result[] = new Throwable[1];
        Display.getDefault().syncExec(new Runnable() {

            @Override
            public void run() {
                try {
                    Shell[] shells = Display.getDefault().getShells();
                    Shell active = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
                    for (Shell shell : shells) {
                        if (!(active == shell) && !shell.isDisposed()) {
                            shell.close();
                        }
                    }
                } catch (Throwable e) {
                    result[0] = e;
                }
            }
        });
        if (result[0] != null) {
            throw result[0];
        }
    }
}
