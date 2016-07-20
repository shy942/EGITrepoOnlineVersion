/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;

/**
* @since 3.3
*
*/
public class ComboSingleSelectionIndexProperty extends SingleSelectionIndexProperty {

    /**
*
*/
    public  ComboSingleSelectionIndexProperty() {
        super(new int[] { SWT.Selection, SWT.DefaultSelection });
    }

    @Override
    int doGetIntValue(Object source) {
        return ((Combo) source).getSelectionIndex();
    }

    @Override
    void doSetIntValue(Object source, int value) {
        if (value == -1)
            ((Combo) source).deselectAll();
        else
            ((Combo) source).select(value);
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "Combo.selectionIndex <int>";
    }
}
