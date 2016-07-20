/***/
package org.eclipse.ui.tests.api;

import org.eclipse.ui.tests.TestPlugin;
import org.eclipse.ui.tests.harness.util.UITestCase;

public class StartupTest extends UITestCase {

    /**
* Construct an instance.
*/
    public  StartupTest(String arg) {
        super(arg);
    }

    public void testStartup() {
        assertTrue("Startup - explicit", StartupClass.getEarlyStartupCalled());
        assertTrue("Startup - implicit", TestPlugin.getEarlyStartupCalled());
        assertTrue("Startup - completed before tests", StartupClass.getEarlyStartupCompleted());
    }

    @Override
    protected void doTearDown() throws Exception {
        super.doTearDown();
        // NOTE:  tearDown will run after each test.  Therefore, we
        // only want one test in this suite (or the values set when
        // this plugin started up will be lost).
        StartupClass.reset();
        TestPlugin.clearEarlyStartup();
    }
}
