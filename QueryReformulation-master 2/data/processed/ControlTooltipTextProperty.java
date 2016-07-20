/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.swt.widgets.Control;

/**
* @since 3.3
*
*/
public class ControlTooltipTextProperty extends WidgetStringValueProperty {

    @Override
    String doGetStringValue(Object source) {
        return ((Control) source).getToolTipText();
    }

    @Override
    void doSetStringValue(Object source, String value) {
        ((Control) source).setToolTipText(value);
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "Control.tooltipText <String>";
    }
}
