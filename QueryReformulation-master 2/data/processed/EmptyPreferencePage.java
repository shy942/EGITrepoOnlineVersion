/***/
package org.eclipse.ui.internal.dialogs;

import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.internal.WorkbenchPlugin;

/*
* A page used as a filler for nodes in the preference tree
* for which no page is suppplied.
*/
public class EmptyPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

    @Override
    protected Control createContents(Composite parent) {
        return new Composite(parent, SWT.NULL);
    }

    /**
* Hook method to get a page specific preference store. Reimplement this
* method if a page don't want to use its parent's preference store.
*/
    @Override
    protected IPreferenceStore doGetPreferenceStore() {
        return WorkbenchPlugin.getDefault().getPreferenceStore();
    }

    /**
* @see IWorkbenchPreferencePage
*/
    @Override
    public void init(IWorkbench workbench) {
    }
}
