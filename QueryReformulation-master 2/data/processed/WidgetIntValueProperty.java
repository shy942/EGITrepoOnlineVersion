/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.jface.databinding.swt.WidgetValueProperty;

/**
* @since 3.3
*
*/
public abstract class WidgetIntValueProperty extends WidgetValueProperty {

     WidgetIntValueProperty() {
        super();
    }

     WidgetIntValueProperty(int event) {
        super(event);
    }

     WidgetIntValueProperty(int[] events) {
        super(events);
    }

    @Override
    public Object getValueType() {
        return Integer.TYPE;
    }

    @Override
    protected Object doGetValue(Object source) {
        return Integer.valueOf(doGetIntValue(source));
    }

    @Override
    protected void doSetValue(Object source, Object value) {
        doSetIntValue(source, ((Integer) value).intValue());
    }

    abstract int doGetIntValue(Object source);

    abstract void doSetIntValue(Object source, int intValue);
}
