/***/
package org.eclipse.ui;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.core.runtime.IExecutableExtensionFactory;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.internal.ShowInMenu;
import org.eclipse.ui.internal.dialogs.ContentTypesPreferencePage;
import org.eclipse.ui.internal.dialogs.DecoratorsPreferencePage;
import org.eclipse.ui.internal.dialogs.EditorsPreferencePage;
import org.eclipse.ui.internal.dialogs.FileEditorsPreferencePage;
import org.eclipse.ui.internal.dialogs.PerspectivesPreferencePage;
import org.eclipse.ui.internal.dialogs.ViewsPreferencePage;
import org.eclipse.ui.internal.dialogs.WorkbenchPreferencePage;
import org.eclipse.ui.internal.keys.KeysPreferencePage;
import org.eclipse.ui.internal.keys.NewKeysPreferencePage;
import org.eclipse.ui.internal.progress.ProgressView;
import org.eclipse.ui.internal.themes.ColorsAndFontsPreferencePage;
import org.eclipse.ui.internal.wizards.preferences.PreferencesExportWizard;
import org.eclipse.ui.internal.wizards.preferences.PreferencesImportWizard;

/**
* Factory for the workbench's public extensions.
* <p>
* This allows the extensions to be made available for use by RCP applications
* without exposing their concrete implementation classes.
* </p>
*
* @since 3.1
*/
public class ExtensionFactory implements IExecutableExtensionFactory, IExecutableExtension {

    /**
* Factory ID for the Appearance preference page.
*/
    //$NON-NLS-1$
    public static final String APPEARANCE_PREFERENCE_PAGE = "appearancePreferencePage";

    /**
* Factory ID for the Colors and Fonts preference page.
*/
    //$NON-NLS-1$
    public static final String COLORS_AND_FONTS_PREFERENCE_PAGE = "colorsAndFontsPreferencePage";

    /**
* Factory ID for the Decorators preference page.
*/
    //$NON-NLS-1$
    public static final String DECORATORS_PREFERENCE_PAGE = "decoratorsPreferencePage";

    /**
* Factory ID for the Editors preference page.
*/
    //$NON-NLS-1$
    public static final String EDITORS_PREFERENCE_PAGE = "editorsPreferencePage";

    /**
* Factory ID for the File Associations preference page.
*/
    //$NON-NLS-1$
    public static final String FILE_ASSOCIATIONS_PREFERENCE_PAGE = "fileAssociationsPreferencePage";

    /**
* Factory ID for the Keys preference page.
*/
    //$NON-NLS-1$
    public static final String KEYS_PREFERENCE_PAGE = "keysPreferencePage";

    /**
* Factory ID for the new (and improved) keys preference page.
*
* @since 3.2
*/
    //$NON-NLS-1$
    public static final String NEW_KEYS_PREFERENCE_PAGE = "newKeysPreferencePage";

    /**
* Factory ID for the Perspectives preference page.
*/
    //$NON-NLS-1$
    public static final String PERSPECTIVES_PREFERENCE_PAGE = "perspectivesPreferencePage";

    /**
* Factory ID for the Preferences export wizard.
*/
    // $//$NON-NLS-1$
    public static final String PREFERENCES_EXPORT_WIZARD = "preferencesExportWizard";

    /**
* Factory ID for the Preferences import wizard.
*/
    // $//$NON-NLS-1$
    public static final String PREFERENCES_IMPORT_WIZARD = "preferencesImportWizard";

    /**
* Factory ID for the Progress view.
*/
    //$NON-NLS-1$
    public static final String PROGRESS_VIEW = "progressView";

    /**
* Factory ID for the Workbench preference page.
*/
    //$NON-NLS-1$
    public static final String WORKBENCH_PREFERENCE_PAGE = "workbenchPreferencePage";

    /**
* Factory ID for the ContentTypes preference page.
*/
    //$NON-NLS-1$
    public static final String CONTENT_TYPES_PREFERENCE_PAGE = "contentTypesPreferencePage";

    /**
* Factory ID for the show in contribution.
*
* @since 3.4
*/
    //$NON-NLS-1$
    public static final String SHOW_IN_CONTRIBUTION = "showInContribution";

    private IConfigurationElement config;

    private String id;

    private String propertyName;

    /**
* Constructs a new workbench extension factory.
*/
    public  ExtensionFactory() {
    // do nothing
    }

    private Object configure(Object obj) throws CoreException {
        if (obj instanceof IExecutableExtension) {
            ((IExecutableExtension) obj).setInitializationData(config, propertyName, null);
        }
        return obj;
    }

    /**
* Creates the object referenced by the factory id obtained from the
* extension data.
*/
    @Override
    public Object create() throws CoreException {
        if (APPEARANCE_PREFERENCE_PAGE.equals(id)) {
            return configure(new ViewsPreferencePage());
        }
        if (COLORS_AND_FONTS_PREFERENCE_PAGE.equals(id)) {
            return configure(new ColorsAndFontsPreferencePage());
        }
        if (DECORATORS_PREFERENCE_PAGE.equals(id)) {
            return configure(new DecoratorsPreferencePage());
        }
        if (EDITORS_PREFERENCE_PAGE.equals(id)) {
            return configure(new EditorsPreferencePage());
        }
        if (FILE_ASSOCIATIONS_PREFERENCE_PAGE.equals(id)) {
            return configure(new FileEditorsPreferencePage());
        }
        if (KEYS_PREFERENCE_PAGE.equals(id)) {
            return configure(new KeysPreferencePage());
        }
        if (NEW_KEYS_PREFERENCE_PAGE.equals(id)) {
            return configure(new NewKeysPreferencePage());
        }
        if (PERSPECTIVES_PREFERENCE_PAGE.equals(id)) {
            return configure(new PerspectivesPreferencePage());
        }
        if (PREFERENCES_EXPORT_WIZARD.equals(id)) {
            return configure(new PreferencesExportWizard());
        }
        if (PREFERENCES_IMPORT_WIZARD.equals(id)) {
            return configure(new PreferencesImportWizard());
        }
        if (PROGRESS_VIEW.equals(id)) {
            return configure(new ProgressView());
        }
        if (WORKBENCH_PREFERENCE_PAGE.equals(id)) {
            return configure(new WorkbenchPreferencePage());
        }
        if (CONTENT_TYPES_PREFERENCE_PAGE.equals(id)) {
            return configure(new ContentTypesPreferencePage());
        }
        if (SHOW_IN_CONTRIBUTION.equals(id)) {
            ShowInMenu showInMenu = new ShowInMenu();
            return showInMenu;
        }
        throw new CoreException(new Status(IStatus.ERROR, PlatformUI.PLUGIN_ID, 0, "Unknown id in data argument for " + getClass(), //$NON-NLS-1$
        null));
    }

    @Override
    public void setInitializationData(IConfigurationElement config, String propertyName, Object data) throws CoreException {
        if (data instanceof String) {
            id = (String) data;
        } else {
            throw new CoreException(new Status(IStatus.ERROR, PlatformUI.PLUGIN_ID, 0, "Data argument must be a String for " + getClass(), //$NON-NLS-1$
            null));
        }
        this.config = config;
        this.propertyName = propertyName;
    }
}
