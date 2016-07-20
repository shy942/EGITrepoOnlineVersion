/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Spinner;

/**
* @since 3.3
*
*/
public class SpinnerSelectionProperty extends WidgetIntValueProperty {

    /**
*
*/
    public  SpinnerSelectionProperty() {
        super(SWT.Modify);
    }

    @Override
    int doGetIntValue(Object source) {
        return ((Spinner) source).getSelection();
    }

    @Override
    void doSetIntValue(Object source, int value) {
        ((Spinner) source).setSelection(value);
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "Spinner.selection <int>";
    }
}
