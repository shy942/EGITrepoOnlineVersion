/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.core.databinding.property.value.IValueProperty;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolTip;

/**
* @since 3.3
*
*/
public class WidgetMessageProperty extends WidgetDelegatingValueProperty {

    private IValueProperty text;

    private IValueProperty toolTip;

    /**
*
*/
    public  WidgetMessageProperty() {
        super(String.class);
    }

    @Override
    protected IValueProperty doGetDelegate(Object source) {
        if (source instanceof Text) {
            if (text == null)
                text = new TextMessageProperty();
            return text;
        }
        if (source instanceof ToolTip) {
            if (toolTip == null)
                toolTip = new ToolTipMessageProperty();
            return toolTip;
        }
        throw notSupported(source);
    }
}
