/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.core.databinding.property.value.DelegatingValueProperty;
import org.eclipse.jface.databinding.swt.DisplayRealm;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.jface.databinding.swt.IWidgetValueProperty;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.swt.widgets.Widget;

abstract class WidgetDelegatingValueProperty extends DelegatingValueProperty implements IWidgetValueProperty {

    RuntimeException notSupported(Object source) {
        return new IllegalArgumentException(//$NON-NLS-1$//$NON-NLS-2$
        "Widget [" + source.getClass().getName() + "] is not supported.");
    }

    public  WidgetDelegatingValueProperty() {
    }

    public  WidgetDelegatingValueProperty(Object valueType) {
        super(valueType);
    }

    @Override
    public ISWTObservableValue observe(Widget widget) {
        return (ISWTObservableValue) observe(DisplayRealm.getRealm(widget.getDisplay()), widget);
    }

    @Override
    public ISWTObservableValue observeDelayed(int delay, Widget widget) {
        return SWTObservables.observeDelayedValue(delay, observe(widget));
    }
}
