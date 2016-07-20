/***/
package org.eclipse.ui.internal.wizards.preferences;

import org.eclipse.osgi.util.NLS;

/**
* NLS messages class for preferences messages.
* @since 3.1
*
*/
public class PreferencesMessages extends NLS {

    //$NON-NLS-1$
    private static final String BUNDLE_NAME = "org.eclipse.ui.internal.wizards.preferences.messages";

    public static String WizardPreferences_description;

    public static String WizardPreferencesPage_noOptionsSelected;

    public static String WizardPreferences_noSpecificPreferenceDescription;

    public static String PreferencesExportWizard_export;

    public static String WizardPreferencesExportPage1_exportTitle;

    public static String WizardPreferencesExportPage1_exportDescription;

    public static String WizardPreferencesExportPage1_preferences;

    public static String WizardPreferencesExportPage1_noPrefFile;

    public static String WizardPreferencesExportPage1_overwrite;

    public static String WizardPreferencesExportPage1_title;

    public static String WizardPreferencesExportPage1_all;

    public static String WizardPreferencesExportPage1_choose;

    public static String WizardPreferencesExportPage1_file;

    public static String PreferencesExport_error;

    public static String PreferencesExport_browse;

    public static String PreferencesExport_createTargetDirectory;

    public static String PreferencesExport_directoryCreationError;

    public static String ExportFile_overwriteExisting;

    public static String PreferencesImportWizard_import;

    public static String WizardPreferencesImportPage1_importTitle;

    public static String WizardPreferencesImportPage1_importDescription;

    public static String WizardPreferencesImportPage1_all;

    public static String WizardPreferencesImportPage1_choose;

    public static String WizardPreferencesImportPage1_file;

    public static String WizardPreferencesImportPage1_title;

    public static String WizardPreferencesImportPage1_invalidPrefFile;

    public static String SelectionDialog_selectLabel;

    public static String SelectionDialog_deselectLabel;

    public static String WizardDataTransfer_existsQuestion;

    public static String WizardDataTransfer_overwriteNameAndPathQuestion;

    public static String Question;

    static {
        // load message values from bundle file
        NLS.initializeMessages(BUNDLE_NAME, PreferencesMessages.class);
    }
}
