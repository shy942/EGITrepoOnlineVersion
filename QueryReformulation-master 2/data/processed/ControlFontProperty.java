/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.jface.databinding.swt.WidgetValueProperty;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Control;

/**
* @since 3.3
*
*/
public class ControlFontProperty extends WidgetValueProperty {

    @Override
    public Object getValueType() {
        return Font.class;
    }

    @Override
    protected Object doGetValue(Object source) {
        return ((Control) source).getFont();
    }

    @Override
    protected void doSetValue(Object source, Object value) {
        ((Control) source).setFont((Font) value);
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "Control.font <Font>";
    }
}
