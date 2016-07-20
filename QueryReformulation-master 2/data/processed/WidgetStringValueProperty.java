/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.jface.databinding.swt.WidgetValueProperty;

/**
* @since 3.3
*
*/
public abstract class WidgetStringValueProperty extends WidgetValueProperty {

     WidgetStringValueProperty() {
        super();
    }

     WidgetStringValueProperty(int event) {
        super(event);
    }

     WidgetStringValueProperty(int[] events) {
        super(events);
    }

     WidgetStringValueProperty(int[] events, int[] staleEvents) {
        super(events, staleEvents);
    }

    @Override
    public Object getValueType() {
        return String.class;
    }

    @Override
    protected Object doGetValue(Object source) {
        return doGetStringValue(source);
    }

    @Override
    protected void doSetValue(Object source, Object value) {
        doSetStringValue(source, (String) value);
    }

    abstract String doGetStringValue(Object source);

    abstract void doSetStringValue(Object source, String value);
}
