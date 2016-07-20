/***/
package org.eclipse.ui.tests.performance;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
* Runs a test specified by the org.eclipse.ui.performance.test property.
* This test is prepped via the UIPerformanceTestSetup test setup.
*
* @since 3.1
*/
public class PerformanceTestRunner extends TestSuite {

    /**
* Returns the suite. This is required to use the JUnit Launcher.
*/
    public static Test suite() {
        return new UIPerformanceTestSetup(new PerformanceTestRunner());
    }

    public  PerformanceTestRunner() {
        String className = System.getProperty("org.eclipse.ui.performance.test");
        try {
            Class clazz = Class.forName(className);
            if (TestSuite.class.isAssignableFrom(clazz))
                addTest((Test) clazz.newInstance());
            else
                addTestSuite(clazz);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
