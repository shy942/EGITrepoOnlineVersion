/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;

/**
* @since 3.3
*
*/
public class TableSingleSelectionIndexProperty extends SingleSelectionIndexProperty {

    /**
*
*/
    public  TableSingleSelectionIndexProperty() {
        super(new int[] { SWT.Selection, SWT.DefaultSelection });
    }

    @Override
    int doGetIntValue(Object source) {
        return ((Table) source).getSelectionIndex();
    }

    @Override
    void doSetIntValue(Object source, int value) {
        if (value == -1)
            ((Table) source).deselectAll();
        else
            ((Table) source).setSelection(value);
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "Table.selectionIndex <int>";
    }
}
