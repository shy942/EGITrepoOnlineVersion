/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.jface.databinding.swt.WidgetValueProperty;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Control;

/**
* @since 3.3
*
*/
public class ControlBoundsProperty extends WidgetValueProperty {

    /**
*
*/
    public  ControlBoundsProperty() {
        super(new int[] { SWT.Resize, SWT.Move });
    }

    @Override
    public Object getValueType() {
        return Rectangle.class;
    }

    @Override
    protected Object doGetValue(Object source) {
        return ((Control) source).getBounds();
    }

    @Override
    protected void doSetValue(Object source, Object value) {
        ((Control) source).setBounds((Rectangle) value);
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "Control.bounds <Rectangle>";
    }
}
