/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.core.databinding.observable.Diffs;
import org.eclipse.core.databinding.property.INativePropertyListener;
import org.eclipse.core.databinding.property.IProperty;
import org.eclipse.core.databinding.property.ISimplePropertyListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;

/**
* @since 3.3
*
*/
public class ControlFocusedProperty extends WidgetBooleanValueProperty {

    /**
*
*/
    public  ControlFocusedProperty() {
        super();
    }

    @Override
    public boolean doGetBooleanValue(Object source) {
        return ((Control) source).isFocusControl();
    }

    @Override
    public void doSetBooleanValue(Object source, boolean value) {
        if (value)
            ((Control) source).setFocus();
    }

    @Override
    public INativePropertyListener adaptListener(ISimplePropertyListener listener) {
        int[] events = { SWT.FocusIn, SWT.FocusOut };
        return new ControlFocusListener(this, listener, events, null);
    }

    private class ControlFocusListener extends WidgetListener {

        /**
* @param property
* @param listener
* @param changeEvents
* @param staleEvents
*/
        private  ControlFocusListener(IProperty property, ISimplePropertyListener listener, int[] changeEvents, int[] staleEvents) {
            super(property, listener, changeEvents, staleEvents);
        }

        @Override
        public void handleEvent(Event event) {
            switch(event.type) {
                case SWT.FocusIn:
                    fireChange(event.widget, Diffs.createValueDiff(Boolean.FALSE, Boolean.TRUE));
                    break;
                case SWT.FocusOut:
                    fireChange(event.widget, Diffs.createValueDiff(Boolean.TRUE, Boolean.FALSE));
                    break;
            }
        }
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "Control.focus <boolean>";
    }
}