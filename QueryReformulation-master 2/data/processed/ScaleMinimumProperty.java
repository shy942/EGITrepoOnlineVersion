/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.swt.widgets.Scale;

/**
* @since 3.3
*
*/
public class ScaleMinimumProperty extends WidgetIntValueProperty {

    @Override
    int doGetIntValue(Object source) {
        return ((Scale) source).getMinimum();
    }

    @Override
    void doSetIntValue(Object source, int value) {
        ((Scale) source).setMinimum(value);
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "Scale.minimum <int>";
    }
}
