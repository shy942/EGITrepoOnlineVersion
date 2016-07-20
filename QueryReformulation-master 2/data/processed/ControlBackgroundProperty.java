/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.jface.databinding.swt.WidgetValueProperty;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Control;

/**
* @since 3.3
*
*/
public class ControlBackgroundProperty extends WidgetValueProperty {

    @Override
    public Object getValueType() {
        return Color.class;
    }

    @Override
    protected Object doGetValue(Object source) {
        return ((Control) source).getBackground();
    }

    @Override
    protected void doSetValue(Object source, Object value) {
        ((Control) source).setBackground((Color) value);
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "Control.background <Color>";
    }
}
