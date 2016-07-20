/***/
package org.eclipse.ui.tests.preferences;

import org.eclipse.ui.tests.propertyPages.PropertyPageEnablementTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
* Test suite for preferences.
*/
@RunWith(Suite.class)
@Suite.SuiteClasses({ FontPreferenceTestCase.class, DeprecatedFontPreferenceTestCase.class, ScopedPreferenceStoreTestCase.class, WorkingCopyPreferencesTestCase.class, PropertyPageEnablementTest.class, ListenerRemovalTestCase.class, PreferencesDialogTest.class, ZoomAndPreferencesFontTest.class })
public class PreferencesTestSuite {
}
