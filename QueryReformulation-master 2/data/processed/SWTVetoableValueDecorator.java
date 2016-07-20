/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.core.databinding.observable.Diffs;
import org.eclipse.core.databinding.observable.value.DecoratingVetoableValue;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Widget;

/**
* @since 3.3
*
*/
public class SWTVetoableValueDecorator extends DecoratingVetoableValue implements ISWTObservableValue {

    private Widget widget;

    private WidgetStringValueProperty property;

    private Listener verifyListener = new Listener() {

        @Override
        public void handleEvent(Event event) {
            String currentText = (String) property.getValue(widget);
            String newText = currentText.substring(0, event.start) + event.text + currentText.substring(event.end);
            if (!fireValueChanging(Diffs.createValueDiff(currentText, newText))) {
                event.doit = false;
            }
        }
    };

    private Listener disposeListener = new Listener() {

        @Override
        public void handleEvent(Event event) {
            SWTVetoableValueDecorator.this.dispose();
        }
    };

    /**
* @param widget
* @param property
* @param decorated
*/
    public  SWTVetoableValueDecorator(Widget widget, WidgetStringValueProperty property, IObservableValue decorated) {
        super(decorated, true);
        this.property = property;
        this.widget = widget;
        Assert.isTrue(decorated.getValueType().equals(String.class), //$NON-NLS-1$
        "SWTVetoableValueDecorator can only decorate observable values of String value type");
        WidgetListenerUtil.asyncAddListener(widget, SWT.Dispose, disposeListener);
    }

    @Override
    protected void firstListenerAdded() {
        super.firstListenerAdded();
        WidgetListenerUtil.asyncAddListener(widget, SWT.Verify, verifyListener);
    }

    @Override
    protected void lastListenerRemoved() {
        WidgetListenerUtil.asyncRemoveListener(widget, SWT.Verify, verifyListener);
        super.lastListenerRemoved();
    }

    @Override
    public synchronized void dispose() {
        WidgetListenerUtil.asyncRemoveListener(widget, SWT.Verify, verifyListener);
        WidgetListenerUtil.asyncRemoveListener(widget, SWT.Dispose, disposeListener);
        this.widget = null;
        super.dispose();
    }

    @Override
    public Widget getWidget() {
        return widget;
    }
}
