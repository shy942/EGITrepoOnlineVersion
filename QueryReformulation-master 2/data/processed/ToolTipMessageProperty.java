/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.swt.widgets.ToolTip;

/**
* @since 3.3
*
*/
public class ToolTipMessageProperty extends WidgetStringValueProperty {

    @Override
    String doGetStringValue(Object source) {
        return ((ToolTip) source).getMessage();
    }

    @Override
    void doSetStringValue(Object source, String value) {
        //$NON-NLS-1$
        ((ToolTip) source).setMessage(value == null ? "" : value);
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "ToolTip.message<String>";
    }
}
