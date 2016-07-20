/***/
package org.eclipse.ui.tests.forms;

import org.eclipse.ui.tests.forms.performance.FormsPerformanceTest;
import junit.framework.Test;
import junit.framework.TestSuite;

/*
* Tests forms performance (automated).
*/
public class AllFormsPerformanceTests extends TestSuite {

    /*
* Returns the entire test suite.
*/
    public static Test suite() {
        return new AllFormsPerformanceTests();
    }

    /*
* Constructs a new performance test suite.
*/
    public  AllFormsPerformanceTests() {
        addTestSuite(FormsPerformanceTest.class);
    }
}
