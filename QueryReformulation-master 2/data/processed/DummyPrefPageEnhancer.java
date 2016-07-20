/***/
package org.eclipse.ui.internal.tweaklets;

import org.eclipse.swt.widgets.Composite;

/**
* @since 3.8
*
*/
public class DummyPrefPageEnhancer extends PreferencePageEnhancer {

    @Override
    public void createContents(Composite parent) {
    }

    @Override
    public void setSelection(Object selection) {
    }

    @Override
    public void performOK() {
    }

    @Override
    public void performCancel() {
    }

    @Override
    public void performDefaults() {
    }
}
