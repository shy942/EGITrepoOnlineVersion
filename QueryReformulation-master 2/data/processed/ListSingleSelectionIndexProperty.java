/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.List;

/**
* @since 3.3
*
*/
public class ListSingleSelectionIndexProperty extends SingleSelectionIndexProperty {

    /**
*
*/
    public  ListSingleSelectionIndexProperty() {
        super(new int[] { SWT.Selection, SWT.DefaultSelection });
    }

    @Override
    int doGetIntValue(Object source) {
        return ((List) source).getSelectionIndex();
    }

    @Override
    void doSetIntValue(Object source, int value) {
        if (value == -1)
            ((List) source).deselectAll();
        else
            ((List) source).setSelection(value);
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "List.selectionIndex <int>";
    }
}
