/***/
package org.eclipse.ui.tests.dialogs;

import org.eclipse.ui.tests.compare.UIComparePreferencesAuto;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import junit.framework.TestSuite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ UIDialogsAuto.class, DeprecatedUIDialogsAuto.class, UIWizardsAuto.class, DeprecatedUIWizardsAuto.class, UIPreferencesAuto.class, UIComparePreferencesAuto.class, DeprecatedUIPreferencesAuto.class, UIMessageDialogsAuto.class, UINewWorkingSetWizardAuto.class, UIEditWorkingSetWizardAuto.class, SearchPatternAuto.class, UIFilteredResourcesSelectionDialogAuto.class })
public class UIAutomatedSuite extends TestSuite {
}
