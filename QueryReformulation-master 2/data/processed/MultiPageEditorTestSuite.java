/***/
package org.eclipse.ui.tests.multipageeditor;

import org.junit.runner.RunWith;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
* The suite of tests for multi-page editors.
*
* @since 3.0
*/
@RunWith(org.junit.runners.AllTests.class)
public class MultiPageEditorTestSuite extends TestSuite {

    /**
* Returns the suite. This is required to use the JUnit Launcher.
* @return A new test suite; never <code>null</code>.;
*/
    public static Test suite() {
        return new MultiPageEditorTestSuite();
    }

    /**
* Construct the test suite.
*/
    public  MultiPageEditorTestSuite() {
        addTestSuite(MultiEditorInputTest.class);
        addTestSuite(MultiVariablePageTest.class);
    // Focus issues
    // addTest(new TestSuite(MultiPageKeyBindingTest.class));
    }
}
