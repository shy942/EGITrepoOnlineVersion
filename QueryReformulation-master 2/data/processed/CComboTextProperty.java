/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;

/**
* @since 3.3
*
*/
public class CComboTextProperty extends WidgetStringValueProperty {

    /**
*
*/
    public  CComboTextProperty() {
        super(SWT.Modify);
    }

    @Override
    String doGetStringValue(Object source) {
        return ((CCombo) source).getText();
    }

    @Override
    void doSetStringValue(Object source, String value) {
        //$NON-NLS-1$
        ((CCombo) source).setText(value != null ? value : "");
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "CCombo.text <String>";
    }
}
