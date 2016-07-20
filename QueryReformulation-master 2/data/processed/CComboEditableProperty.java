/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.swt.custom.CCombo;

/**
* @since 3.3
*
*/
public class CComboEditableProperty extends WidgetBooleanValueProperty {

    @Override
    boolean doGetBooleanValue(Object source) {
        return ((CCombo) source).getEditable();
    }

    @Override
    void doSetBooleanValue(Object source, boolean value) {
        ((CCombo) source).setEditable(value);
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "CCombo.editable <boolean>";
    }
}
