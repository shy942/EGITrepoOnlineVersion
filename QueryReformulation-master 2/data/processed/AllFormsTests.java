/***/
package org.eclipse.ui.tests.forms;

import org.eclipse.ui.tests.forms.layout.AllLayoutTests;
import org.eclipse.ui.tests.forms.util.AllUtilityTests;
import org.eclipse.ui.tests.forms.widgets.ExpandableCompositeTest;
import junit.framework.Test;
import junit.framework.TestSuite;

/*
* Tests all cheat sheet functionality (automated).
*/
public class AllFormsTests extends TestSuite {

    /*
* Returns the entire test suite.
*/
    public static Test suite() {
        return new AllFormsTests();
    }

    /*
* Constructs a new test suite.
*/
    public  AllFormsTests() {
        addTest(AllLayoutTests.suite());
        addTest(AllUtilityTests.suite());
        addTestSuite(ExpandableCompositeTest.class);
    }
}
