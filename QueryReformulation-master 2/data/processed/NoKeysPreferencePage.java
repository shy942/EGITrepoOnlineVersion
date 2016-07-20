/***/
package org.eclipse.ui.internal.keys;

import org.eclipse.jface.preference.PreferencePage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

public class NoKeysPreferencePage extends PreferencePage implements IWorkbenchPreferencePage {

    @Override
    public void init(IWorkbench workbench) {
    // do nothing, we don't have content
    }

    @Override
    protected Control createContents(Composite parent) {
        Label info = new Label(parent, SWT.NONE);
        //$NON-NLS-1$
        info.setText("Custom key preferences are not available at this time.\nPlease create key bindings through the model.");
        return info;
    }
}
