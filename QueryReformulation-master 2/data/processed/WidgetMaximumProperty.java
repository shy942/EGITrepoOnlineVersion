/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.core.databinding.property.value.IValueProperty;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Slider;
import org.eclipse.swt.widgets.Spinner;

/**
* @since 3.3
*
*/
public class WidgetMaximumProperty extends WidgetDelegatingValueProperty {

    private IValueProperty scale;

    private IValueProperty slider;

    private IValueProperty spinner;

    /**
*
*/
    public  WidgetMaximumProperty() {
        super(Integer.TYPE);
    }

    @Override
    protected IValueProperty doGetDelegate(Object source) {
        if (source instanceof Scale) {
            if (scale == null)
                scale = new ScaleMaximumProperty();
            return scale;
        }
        if (source instanceof Slider) {
            if (slider == null) {
                slider = new SliderMaximumProperty();
            }
            return slider;
        }
        if (source instanceof Spinner) {
            if (spinner == null)
                spinner = new SpinnerMaximumProperty();
            return spinner;
        }
        throw notSupported(source);
    }
}
