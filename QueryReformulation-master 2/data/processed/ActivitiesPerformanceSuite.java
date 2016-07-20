/***/
package org.eclipse.ui.tests.performance;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
* @since 3.1
*
*/
public class ActivitiesPerformanceSuite extends TestSuite {

    /**
* Returns the suite. This is required to use the JUnit Launcher.
*/
    public static Test suite() {
        return new ActivitiesPerformanceSuite();
    }

    /**
*
*/
    public  ActivitiesPerformanceSuite() {
        super();
        addTest(new GenerateIdentifiersTest(10000));
    }
}
