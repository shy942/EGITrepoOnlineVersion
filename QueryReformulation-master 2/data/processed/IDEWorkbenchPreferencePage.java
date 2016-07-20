/***/
package org.eclipse.ui.internal.ide.dialogs;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.IWorkbenchHelpContextIds;
import org.eclipse.ui.internal.dialogs.WorkbenchPreferencePage;
import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;

/**
* The IDE workbench main preference page.
*
*Note: want IDE settings to appear in main Workbench preference page (via subclassing),
*   however the superclass, WorkbenchPreferencePage, is internal
*/
public class IDEWorkbenchPreferencePage extends WorkbenchPreferencePage implements IWorkbenchPreferencePage {

    @Override
    protected Control createContents(Composite parent) {
        PlatformUI.getWorkbench().getHelpSystem().setHelp(parent, IWorkbenchHelpContextIds.WORKBENCH_PREFERENCE_PAGE);
        Composite composite = createComposite(parent);
        createSettings(composite);
        createOpenModeGroup(composite);
        applyDialogFont(composite);
        return composite;
    }

    /**
* Returns the IDE preference store.
* @return the preference store.
*/
    protected IPreferenceStore getIDEPreferenceStore() {
        return IDEWorkbenchPlugin.getDefault().getPreferenceStore();
    }

    /**
* The default button has been pressed.
*/
    @Override
    protected void performDefaults() {
        super.performDefaults();
    }

    /**
* The user has pressed Ok. Store/apply this page's values appropriately.
*/
    @Override
    public boolean performOk() {
        return super.performOk();
    }
}
