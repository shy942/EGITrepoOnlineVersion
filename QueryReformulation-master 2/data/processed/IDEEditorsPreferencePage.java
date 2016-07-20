/***/
package org.eclipse.ui.internal.ide.dialogs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.dialogs.PreferenceLinkArea;
import org.eclipse.ui.internal.dialogs.EditorsPreferencePage;
import org.eclipse.ui.internal.ide.IDEWorkbenchMessages;
import org.eclipse.ui.internal.tweaklets.TabBehaviour;
import org.eclipse.ui.internal.tweaklets.Tweaklets;
import org.eclipse.ui.preferences.IWorkbenchPreferenceContainer;

/**
* Extends the Editors preference page with IDE-specific settings.
*
* Note: want IDE settings to appear in main Editors preference page (via
* subclassing), however the superclass, EditorsPreferencePage, is internal
*/
public class IDEEditorsPreferencePage extends EditorsPreferencePage {

    @Override
    protected Control createContents(Composite parent) {
        Composite composite = createComposite(parent);
        PreferenceLinkArea fileEditorsArea = new PreferenceLinkArea(composite, SWT.NONE, //$NON-NLS-1$
        "org.eclipse.ui.preferencePages.FileEditors", //$NON-NLS-1$
        IDEWorkbenchMessages.IDEEditorsPreferencePage_WorkbenchPreference_FileEditorsRelatedLink, (IWorkbenchPreferenceContainer) getContainer(), null);
        GridData data = new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL);
        fileEditorsArea.getControl().setLayoutData(data);
        PreferenceLinkArea contentTypeArea = new PreferenceLinkArea(composite, SWT.NONE, //$NON-NLS-1$
        "org.eclipse.ui.preferencePages.ContentTypes", //$NON-NLS-1$
        IDEWorkbenchMessages.IDEEditorsPreferencePage_WorkbenchPreference_contentTypesRelatedLink, (IWorkbenchPreferenceContainer) getContainer(), null);
        data = new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL);
        contentTypeArea.getControl().setLayoutData(data);
        PreferenceLinkArea appearanceArea = new PreferenceLinkArea(composite, SWT.NONE, //$NON-NLS-1$
        "org.eclipse.ui.preferencePages.Views", //$NON-NLS-1$
        IDEWorkbenchMessages.IDEEditorsPreferencePage_WorkbenchPreference_viewsRelatedLink, (IWorkbenchPreferenceContainer) getContainer(), null);
        data = new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL);
        appearanceArea.getControl().setLayoutData(data);
        createEditorHistoryGroup(composite);
        createSpace(composite);
        createShowMultipleEditorTabsPref(composite);
        createAllowInplaceEditorPref(composite);
        createUseIPersistablePref(composite);
        createPromptWhenStillOpenPref(composite);
        createEditorReuseGroup(composite);
        ((TabBehaviour) Tweaklets.get(TabBehaviour.KEY)).setPreferenceVisibility(editorReuseGroup, showMultipleEditorTabs);
        applyDialogFont(composite);
        super.setHelpContext(parent);
        return composite;
    }
}
