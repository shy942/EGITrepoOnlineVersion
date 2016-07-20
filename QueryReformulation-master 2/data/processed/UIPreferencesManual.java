/***/
package org.eclipse.ui.tests.manual;

import org.eclipse.jface.preference.IPreferenceNode;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.tests.dialogs.PreferenceDialogWrapper;
import org.eclipse.ui.tests.dialogs.UIPreferencesAuto;

/**
* The UIPreferencesManual is a test case that requires
* the user to click OK on message dialog when it is
* run
*/
public class UIPreferencesManual extends UIPreferencesAuto {

    public  UIPreferencesManual(String name) {
        super(name);
    }

    /**
* Test the bad update preference page by generating all
* of the dialog errors.
*/
    public void testBrokenListenerPref() {
        PreferenceDialogWrapper dialog = null;
        PreferenceManager manager = WorkbenchPlugin.getDefault().getPreferenceManager();
        if (manager != null) {
            dialog = new PreferenceDialogWrapper(getShell(), manager);
            dialog.create();
            for (Object element : manager.getElements(PreferenceManager.PRE_ORDER)) {
                IPreferenceNode node = (IPreferenceNode) element;
                if (node.getId().equals("org.eclipse.ui.tests.manual.BrokenUpdatePreferencePage")) {
                    dialog.showPage(node);
                    BrokenUpdatePreferencePage page = (BrokenUpdatePreferencePage) dialog.getPage(node);
                    page.changeFont();
                    page.changePluginPreference();
                    break;
                }
            }
        }
    }
}
