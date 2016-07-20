/***/
package org.eclipse.ui.tests.stress;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
* Test all areas of the UI API.
*/
public class OpenCloseTestSuite extends TestSuite {

    /**
* Returns the suite.  This is required to
* use the JUnit Launcher.
*/
    public static Test suite() {
        return new OpenCloseTestSuite();
    }

    /**
* Construct the test suite.
*/
    public  OpenCloseTestSuite() {
        addTest(new TestSuite(OpenCloseTest.class));
    }
}
