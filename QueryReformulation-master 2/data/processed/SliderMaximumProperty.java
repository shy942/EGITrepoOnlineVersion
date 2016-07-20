/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.swt.widgets.Slider;

/**
*
*/
public class SliderMaximumProperty extends WidgetIntValueProperty {

    @Override
    int doGetIntValue(Object source) {
        return ((Slider) source).getMaximum();
    }

    @Override
    void doSetIntValue(Object source, int value) {
        ((Slider) source).setMaximum(value);
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "Slider.maximum <int>";
    }
}
