/***/
package org.eclipse.ui.tests.api;

import junit.framework.Assert;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IStartup;

public class StartupClass implements IStartup {

    /**
* This boolean should only be true if the earlyStartup() method
* has been called.
*/
    private static boolean earlyStartupCalled = false;

    /**
* This boolean should only be true if the earlyStartup() method
* has completed.
*/
    private static boolean earlyStartupCompleted = false;

    @Override
    public void earlyStartup() {
        earlyStartupCalled = true;
        Assert.assertNull("IStartup should run in non-UI thread", Display.getCurrent());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        earlyStartupCompleted = true;
    }

    public static boolean getEarlyStartupCalled() {
        return earlyStartupCalled;
    }

    public static boolean getEarlyStartupCompleted() {
        return earlyStartupCompleted;
    }

    /**
* Reset the flags.
*/
    public static void reset() {
        earlyStartupCalled = false;
        earlyStartupCompleted = false;
    }
}
