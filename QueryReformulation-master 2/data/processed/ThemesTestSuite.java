/***/
package org.eclipse.ui.tests.themes;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import junit.framework.TestSuite;

/**
* @since 3.0
*/
@RunWith(Suite.class)
@Suite.SuiteClasses({ ThemeAPITest.class, JFaceThemeTest.class, WorkbenchThemeChangedHandlerTest.class, ThemeRegistryModifiedHandlerTest.class, StylingPreferencesHandlerTest.class })
public class ThemesTestSuite extends TestSuite {
}
