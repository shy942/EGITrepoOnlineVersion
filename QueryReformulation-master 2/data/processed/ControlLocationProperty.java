/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.jface.databinding.swt.WidgetValueProperty;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;

/**
* @since 3.3
*
*/
public class ControlLocationProperty extends WidgetValueProperty {

    /**
*
*/
    public  ControlLocationProperty() {
        super(SWT.Move);
    }

    @Override
    public Object getValueType() {
        return Point.class;
    }

    @Override
    protected Object doGetValue(Object source) {
        return ((Control) source).getLocation();
    }

    @Override
    protected void doSetValue(Object source, Object value) {
        ((Control) source).setLocation((Point) value);
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "Control.location <Point>";
    }
}
