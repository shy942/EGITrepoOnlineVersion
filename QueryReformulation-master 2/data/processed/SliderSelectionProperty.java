/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Slider;

public class SliderSelectionProperty extends WidgetIntValueProperty {

    /**
*
*/
    public  SliderSelectionProperty() {
        super(SWT.Selection);
    }

    @Override
    int doGetIntValue(Object source) {
        return ((Slider) source).getSelection();
    }

    @Override
    void doSetIntValue(Object source, int value) {
        ((Slider) source).setSelection(value);
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "Slider.selection <int>";
    }
}
