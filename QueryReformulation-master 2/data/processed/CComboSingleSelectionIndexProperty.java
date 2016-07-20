/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;

/**
* @since 3.3
*
*/
public class CComboSingleSelectionIndexProperty extends SingleSelectionIndexProperty {

    /**
*
*/
    public  CComboSingleSelectionIndexProperty() {
        super(new int[] { SWT.Selection, SWT.DefaultSelection });
    }

    @Override
    int doGetIntValue(Object source) {
        return ((CCombo) source).getSelectionIndex();
    }

    @Override
    void doSetIntValue(Object source, int value) {
        if (value == -1)
            ((CCombo) source).deselectAll();
        else
            ((CCombo) source).select(value);
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "CCombo.selectionIndex <int>";
    }
}
