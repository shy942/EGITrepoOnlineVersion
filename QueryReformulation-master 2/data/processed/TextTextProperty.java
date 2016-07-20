/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.ISWTObservableValue;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Widget;

/**
* @since 3.3
*
*/
public class TextTextProperty extends WidgetStringValueProperty {

    /**
*
*/
    public  TextTextProperty() {
        this(null);
    }

    /**
* @param events
*/
    public  TextTextProperty(int[] events) {
        super(checkEvents(events), staleEvents(events));
    }

    private static int[] checkEvents(int[] events) {
        if (events != null)
            for (int i = 0; i < events.length; i++) checkEvent(events[i]);
        return events;
    }

    private static void checkEvent(int event) {
        if (event != SWT.None && event != SWT.Modify && event != SWT.FocusOut && event != SWT.DefaultSelection)
            throw new IllegalArgumentException(//$NON-NLS-1$
            "UpdateEventType [" + event + //$NON-NLS-1$
            "] is not supported.");
    }

    private static int[] staleEvents(int[] changeEvents) {
        if (changeEvents != null)
            for (int i = 0; i < changeEvents.length; i++) if (changeEvents[i] == SWT.Modify)
                return null;
        return new int[] { SWT.Modify };
    }

    @Override
    String doGetStringValue(Object source) {
        return ((Text) source).getText();
    }

    @Override
    void doSetStringValue(Object source, String value) {
        //$NON-NLS-1$
        ((Text) source).setText(value == null ? "" : value);
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "Text.text <String>";
    }

    @Override
    protected ISWTObservableValue wrapObservable(IObservableValue observable, Widget widget) {
        return new SWTVetoableValueDecorator(widget, this, observable);
    }
}
