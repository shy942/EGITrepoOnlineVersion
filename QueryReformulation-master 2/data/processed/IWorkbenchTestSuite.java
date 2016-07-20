/***/
package org.eclipse.ui.tests.api;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
* Test the workbench. This suite was created as a
* workaround for problems running the suites from the
* command line.
*/
public class IWorkbenchTestSuite extends TestSuite {

    /**
* Returns the suite.  This is required to
* use the JUnit Launcher.
*/
    public static Test suite() {
        return new IWorkbenchTestSuite();
    }

    /**
* Construct the test suite.
*/
    public  IWorkbenchTestSuite() {
        addTest(new TestSuite(IWorkbenchTest.class));
        addTest(new TestSuite(IWorkbenchWindowTest.class));
    }
}
