/***/
package org.eclipse.ui.tests.adaptable;

import org.junit.runner.RunWith;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
* The AdaptableTestSuite is the TestSuite for the
* adaptable support in the UI.
*/
@RunWith(org.junit.runners.AllTests.class)
public class AdaptableTestSuite extends TestSuite {

    /**
* Returns the suite.  This is required to
* use the JUnit Launcher.
*/
    public static Test suite() {
        return new AdaptableTestSuite();
    }

    /**
* Constructor for AdaptableTestSuite.
*/
    public  AdaptableTestSuite() {
        addTest(AdaptableDecoratorTestCase.suite());
        addTest(new TestSuite(MarkerImageProviderTest.class));
        addTest(new TestSuite(WorkingSetTestCase.class));
        addTest(new TestSuite(SelectionAdapterTest.class));
    }
}
