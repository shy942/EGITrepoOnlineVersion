/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;

/**
* @since 3.3
*
*/
public class CComboSelectionProperty extends WidgetStringValueProperty {

    /**
*
*/
    public  CComboSelectionProperty() {
        super(SWT.Modify);
    }

    @Override
    String doGetStringValue(Object source) {
        return ((CCombo) source).getText();
    }

    @Override
    void doSetStringValue(Object source, String value) {
        CCombo ccombo = (CCombo) source;
        String items[] = ccombo.getItems();
        int index = -1;
        if (value == null) {
            ccombo.select(-1);
        } else if (items != null) {
            for (int i = 0; i < items.length; i++) {
                if (value.equals(items[i])) {
                    index = i;
                    break;
                }
            }
            if (index == -1) {
                ccombo.setText(value);
            } else {
                // -1 will not "unselect"
                ccombo.select(index);
            }
        }
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "CCombo.selection <String>";
    }
}
