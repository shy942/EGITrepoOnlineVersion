/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.core.databinding.property.value.IValueProperty;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Text;

/**
* @since 3.3
*
*/
public class WidgetTextWithEventsProperty extends WidgetDelegatingValueProperty {

    private final int[] events;

    private IValueProperty styledText;

    private IValueProperty text;

    /**
* @param events
*/
    public  WidgetTextWithEventsProperty(int[] events) {
        super(String.class);
        this.events = checkEvents(events);
    }

    private static int[] checkEvents(int[] events) {
        for (int i = 0; i < events.length; i++) checkEvent(events[i]);
        return events;
    }

    private static void checkEvent(int event) {
        if (event != SWT.None && event != SWT.Modify && event != SWT.FocusOut && event != SWT.DefaultSelection)
            throw new IllegalArgumentException(//$NON-NLS-1$
            "UpdateEventType [" + event + //$NON-NLS-1$
            "] is not supported.");
    }

    @Override
    protected IValueProperty doGetDelegate(Object source) {
        if (source instanceof StyledText) {
            if (styledText == null)
                styledText = new StyledTextTextProperty(events);
            return styledText;
        }
        if (source instanceof Text) {
            if (text == null)
                text = new TextTextProperty(events);
            return text;
        }
        throw notSupported(source);
    }
}
