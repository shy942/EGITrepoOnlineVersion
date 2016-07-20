/***/
package org.eclipse.ui.tests.session;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.eclipse.ui.PlatformUI;

/**
* Tests intro-related session properties.
*
* @since 3.1
*/
public class IntroSessionTests extends TestCase {

    public static TestSuite suite() {
        TestSuite ts = new TestSuite("org.eclipse.ui.tests.session.IntroSessionTests");
        ts.addTest(new IntroSessionTests("testIntro"));
        return ts;
    }

    /**
* @param name
*/
    public  IntroSessionTests(String name) {
        super(name);
    }

    public void testIntro() {
        //assert that the intro was not shown
        assertNull(PlatformUI.getWorkbench().getIntroManager().getIntro());
    }
}
