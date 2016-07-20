/***/
package org.eclipse.ui.tests.session;

import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.themes.IThemeManager;

/**
* Tests various persistent theme properties.
*
* @since 3.4
*/
public class ThemeStateTest extends TestCase {

    public static TestSuite suite() {
        TestSuite ts = new TestSuite("org.eclipse.ui.tests.session.ThemeStateTest");
        ts.addTest(new ThemeStateTest("testBadPreference"));
        return ts;
    }

    public  ThemeStateTest(final String name) {
        super(name);
    }

    /**
* Tests to ensure that the workbench still comes up if there's a bad theme
* ID in the preference store.
*/
    public void testBadPreference() {
        String themeId = PlatformUI.getWorkbench().getThemeManager().getCurrentTheme().getId();
        assertNotNull(themeId);
        assertEquals(IThemeManager.DEFAULT_THEME, themeId);
    }
}
