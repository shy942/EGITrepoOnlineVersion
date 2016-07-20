/***/
package org.eclipse.jface.internal.databinding.swt;

/**
* @since 3.3
*
*/
public abstract class SingleSelectionIndexProperty extends WidgetIntValueProperty {

    /**
* @param events
*/
    public  SingleSelectionIndexProperty(int[] events) {
        super(events);
    }

    @Override
    protected void doSetValue(Object source, Object value) {
        super.doSetValue(source, value == null ? Integer.valueOf(-1) : value);
    }
}
