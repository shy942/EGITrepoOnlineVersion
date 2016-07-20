/***/
package org.eclipse.ui.tests.decorators;

import org.junit.runner.RunWith;
import junit.framework.Test;
import junit.framework.TestSuite;

@RunWith(org.junit.runners.AllTests.class)
public class DecoratorsTestSuite extends TestSuite {

    /**
* Returns the suite.  This is required to
* use the JUnit Launcher.
*/
    public static Test suite() {
        return new DecoratorsTestSuite();
    }

    /**
* Construct the test suite.
*/
    public  DecoratorsTestSuite() {
        addTest(new TestSuite(ExceptionDecoratorTestCase.class));
        addTest(new TestSuite(DecoratorTestCase.class));
        addTest(new TestSuite(LightweightDecoratorTestCase.class));
        addTest(new TestSuite(BadIndexDecoratorTestCase.class));
        //		addTest(new TestSuite(DecoratorTreeTest.class));
        //		addTest(new TestSuite(DecoratorTableTest.class));
        //		addTest(new TestSuite(DecoratorTableTreeTest.class));
        addTest(DecoratorAdaptableTests.suite());
        addTest(new TestSuite(DecoratorCacheTest.class));
    }
}
