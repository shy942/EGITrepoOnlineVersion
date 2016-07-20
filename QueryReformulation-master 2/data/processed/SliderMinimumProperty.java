/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.swt.widgets.Slider;

/**
*
*/
public class SliderMinimumProperty extends WidgetIntValueProperty {

    @Override
    int doGetIntValue(Object source) {
        return ((Slider) source).getMinimum();
    }

    @Override
    void doSetIntValue(Object source, int value) {
        ((Slider) source).setMinimum(value);
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "Slider.minimum <int>";
    }
}
