/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.swt.widgets.TrayItem;

/**
* @since 3.3
*
*/
public class TrayItemTooltipTextProperty extends WidgetStringValueProperty {

    @Override
    String doGetStringValue(Object source) {
        return ((TrayItem) source).getToolTipText();
    }

    @Override
    void doSetStringValue(Object source, String value) {
        ((TrayItem) source).setToolTipText(value);
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "TrayItem.toolTipText <String>";
    }
}
