/***/
package org.eclipse.ui.tests.services;

import org.junit.runner.RunWith;
import junit.framework.Test;
import junit.framework.TestSuite;

/**
* Tests general to services.
* @since 3.3
*/
@RunWith(org.junit.runners.AllTests.class)
public final class ServicesTestSuite extends TestSuite {

    /**
* Returns the suite. This is required to use the JUnit Launcher.
*/
    public static final Test suite() {
        return new ServicesTestSuite();
    }

    /**
* Construct the test suite.
*/
    public  ServicesTestSuite() {
        addTest(new TestSuite(EvaluationServiceTest.class));
        addTest(ContributedServiceTest.suite());
        addTest(new TestSuite(WorkbenchSiteProgressServiceTest.class));
    // TODO addTest(new TestSuite(EditorSourceTest.class));
    }
}
