/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.swt.custom.CTabItem;

/**
* @since 3.3
*
*/
public class CTabItemTooltipTextProperty extends WidgetStringValueProperty {

    @Override
    String doGetStringValue(Object source) {
        return ((CTabItem) source).getToolTipText();
    }

    @Override
    void doSetStringValue(Object source, String value) {
        ((CTabItem) source).setToolTipText(value);
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "CTabItem.toolTipText <String>";
    }
}
