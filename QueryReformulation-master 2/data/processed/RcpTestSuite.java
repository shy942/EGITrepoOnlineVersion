/***/
package org.eclipse.ui.tests.rcp;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
* The test suite for the RCP APIs in the generic workbench.
* To run, use a headless JUnit Plug-in Test launcher, configured
* to have [No Application] as its application.
*/
@RunWith(Suite.class)
@Suite.SuiteClasses({ PlatformUITest.class, WorkbenchAdvisorTest.class, WorkbenchConfigurerTest.class, WorkbenchWindowConfigurerTest.class, ActionBarConfigurerTest.class, IWorkbenchPageTest.class, WorkbenchSaveRestoreStateTest.class, WorkbenchListenerTest.class })
public class RcpTestSuite {

    public  RcpTestSuite() {
    }
}
