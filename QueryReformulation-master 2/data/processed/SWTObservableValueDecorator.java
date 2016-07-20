/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.core.databinding.observable.value.DecoratingObservableValue;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Widget;

/**
* @since 3.3
*
*/
public class SWTObservableValueDecorator extends DecoratingObservableValue implements ISWTObservableValue, Listener {

    private Widget widget;

    /**
* @param decorated
* @param widget
*/
    public  SWTObservableValueDecorator(IObservableValue decorated, Widget widget) {
        super(decorated, true);
        this.widget = widget;
        WidgetListenerUtil.asyncAddListener(widget, SWT.Dispose, this);
    }

    @Override
    public void handleEvent(Event event) {
        if (event.type == SWT.Dispose)
            dispose();
    }

    @Override
    public Widget getWidget() {
        return widget;
    }

    @Override
    public synchronized void dispose() {
        if (widget != null) {
            WidgetListenerUtil.asyncRemoveListener(widget, SWT.Dispose, this);
            widget = null;
        }
        super.dispose();
    }
}
