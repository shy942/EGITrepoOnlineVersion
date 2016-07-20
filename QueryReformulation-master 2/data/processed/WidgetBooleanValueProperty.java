/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.jface.databinding.swt.WidgetValueProperty;

/**
* @since 3.3
*
*/
public abstract class WidgetBooleanValueProperty extends WidgetValueProperty {

     WidgetBooleanValueProperty() {
        super();
    }

     WidgetBooleanValueProperty(int event) {
        super(event);
    }

     WidgetBooleanValueProperty(int[] events) {
        super(events);
    }

    @Override
    public Object getValueType() {
        return Boolean.TYPE;
    }

    @Override
    protected Object doGetValue(Object source) {
        return doGetBooleanValue(source) ? Boolean.TRUE : Boolean.FALSE;
    }

    @Override
    protected void doSetValue(Object source, Object value) {
        if (value == null)
            value = Boolean.FALSE;
        doSetBooleanValue(source, ((Boolean) value).booleanValue());
    }

    abstract boolean doGetBooleanValue(Object source);

    abstract void doSetBooleanValue(Object source, boolean value);
}
