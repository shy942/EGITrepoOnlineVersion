/***/
package org.eclipse.ui.tests.zoom;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
* A test suite to test the zooming behavior of Eclipse.
*/
public class ZoomTestSuite extends TestSuite {

    /**
* Returns the suite.  This is required to
* use the JUnit Launcher.
*/
    public static Test suite() {
        return new ZoomTestSuite();
    }

    /**
* Construct the test suite.
*/
    public  ZoomTestSuite() {
        addTest(new TestSuite(ZoomedViewActivateTest.class));
        addTest(new TestSuite(ZoomedEditorCloseTest.class));
        addTest(new TestSuite(ZoomedViewCloseTest.class));
        addTest(new TestSuite(ShowViewTest.class));
        addTest(new TestSuite(OpenEditorTest.class));
    }
}
