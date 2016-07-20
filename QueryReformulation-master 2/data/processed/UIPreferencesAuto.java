/***/
package org.eclipse.ui.tests.dialogs;

import junit.framework.TestCase;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.preference.IPreferenceNode;
import org.eclipse.jface.preference.PreferenceDialog;
import org.eclipse.jface.preference.PreferenceManager;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.help.WorkbenchHelp;
import org.eclipse.ui.internal.IWorkbenchHelpContextIds;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.tests.harness.util.DialogCheck;

public class UIPreferencesAuto extends TestCase {

    public  UIPreferencesAuto(String name) {
        super(name);
    }

    protected Shell getShell() {
        return DialogCheck.getShell();
    }

    private PreferenceDialog getPreferenceDialog(String id) {
        PreferenceDialogWrapper dialog = null;
        PreferenceManager manager = WorkbenchPlugin.getDefault().getPreferenceManager();
        if (manager != null) {
            dialog = new PreferenceDialogWrapper(getShell(), manager);
            dialog.create();
            WorkbenchHelp.setHelp(dialog.getShell(), IWorkbenchHelpContextIds.PREFERENCE_DIALOG);
            for (Object element : manager.getElements(PreferenceManager.PRE_ORDER)) {
                IPreferenceNode node = (IPreferenceNode) element;
                if (node.getId().equals(id)) {
                    dialog.showPage(node);
                    break;
                }
            }
        }
        return dialog;
    }

    public void testWorkbenchPref() {
        Dialog dialog = getPreferenceDialog("org.eclipse.ui.preferencePages.Workbench");
        DialogCheck.assertDialogTexts(dialog, this);
    }

    public void testAppearancePref() {
        Dialog dialog = getPreferenceDialog("org.eclipse.ui.preferencePages.Views");
        DialogCheck.assertDialogTexts(dialog, this);
    }

    public void testDefaultTextEditorPref() {
        Dialog dialog = getPreferenceDialog("org.eclipse.ui.preferencePages.TextEditor");
        DialogCheck.assertDialogTexts(dialog, this);
    }

    public void testFileEditorsPref() {
        Dialog dialog = getPreferenceDialog("org.eclipse.ui.preferencePages.FileEditors");
        DialogCheck.assertDialogTexts(dialog, this);
    }

    public void testLocalHistoryPref() {
        Dialog dialog = getPreferenceDialog("org.eclipse.ui.preferencePages.FileStates");
        DialogCheck.assertDialogTexts(dialog, this);
    }

    public void testPerspectivesPref() {
        Dialog dialog = getPreferenceDialog("org.eclipse.ui.preferencePages.Perspectives");
        DialogCheck.assertDialogTexts(dialog, this);
    }

    //Only really checking if this opens without an exception
    public void testFontEditorsPref() {
        Dialog dialog = getPreferenceDialog("org.eclipse.ui.tests.dialogs.FontFieldEditorTestPreferencePage");
        DialogCheck.assertDialogTexts(dialog, this);
    }

    public void testInfoProp() {
    /*
* Commented out because it generates a failure
* of expect and actual width values. Suspect this
* is an SWT issue.
*
Dialog dialog = getPropertyDialog("org.eclipse.ui.propertypages.info.file");
DialogCheck.assertDialogTexts(dialog, this);
*/
    }

    public void testProjectReferencesProp() {
    /*
* Commented out because it generates a failure
* of expect and actual width values. Suspect this
* is an SWT issue.
*
Dialog dialog = getPropertyDialog("org.eclipse.ui.propertypages.project.reference");
DialogCheck.assertDialogTexts(dialog, this);
*/
    }

    /**
* Test the editors preference page and toggle the
* enable state twice to be sure there are no errors.
*/
    public void testFieldEditorEnablePref() {
        PreferenceDialogWrapper dialog = null;
        PreferenceManager manager = WorkbenchPlugin.getDefault().getPreferenceManager();
        if (manager != null) {
            dialog = new PreferenceDialogWrapper(getShell(), manager);
            dialog.create();
            for (Object element : manager.getElements(PreferenceManager.PRE_ORDER)) {
                IPreferenceNode node = (IPreferenceNode) element;
                if (node.getId().equals("org.eclipse.ui.tests.dialogs.EnableTestPreferencePage")) {
                    dialog.showPage(node);
                    EnableTestPreferencePage page = (EnableTestPreferencePage) dialog.getPage(node);
                    page.flipState();
                    page.flipState();
                    break;
                }
            }
        }
    }
}
