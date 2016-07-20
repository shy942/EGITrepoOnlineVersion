/***/
package org.eclipse.jface.tests.databinding;

import org.eclipse.core.tests.databinding.SideEffectTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import junit.framework.TestCase;
import junit.framework.TestSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ BindingTestSuiteJunit3.class, SideEffectTest.class })
public class BindingTestSuite {

    /**
* @param testCase
*            TODO
* @return true if the given test is temporarily disabled
*/
    public static boolean failingTestsDisabled(TestCase testCase) {
        System.out.println("Ignoring disabled test: " + testCase.getClass().getName() + "." + testCase.getName());
        return true;
    }

    /**
* @param testSuite
*            TODO
* @return true if the given test is temporarily disabled
*/
    public static boolean failingTestsDisabled(TestSuite testSuite) {
        System.out.println("Ignoring disabled test: " + testSuite.getClass().getName() + "." + testSuite.getName());
        return true;
    }
}
