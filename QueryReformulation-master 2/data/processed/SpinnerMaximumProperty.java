/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.swt.widgets.Spinner;

/**
* @since 3.3
*
*/
public class SpinnerMaximumProperty extends WidgetIntValueProperty {

    @Override
    int doGetIntValue(Object source) {
        return ((Spinner) source).getMaximum();
    }

    @Override
    void doSetIntValue(Object source, int value) {
        ((Spinner) source).setMaximum(value);
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "Spinner.maximum <int>";
    }
}
