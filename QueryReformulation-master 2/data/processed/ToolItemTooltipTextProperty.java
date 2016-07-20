/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.swt.widgets.ToolItem;

/**
* @since 3.3
*
*/
public class ToolItemTooltipTextProperty extends WidgetStringValueProperty {

    @Override
    String doGetStringValue(Object source) {
        return ((ToolItem) source).getToolTipText();
    }

    @Override
    void doSetStringValue(Object source, String value) {
        ((ToolItem) source).setToolTipText(value);
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "ToolItem.toolTipText <String>";
    }
}
