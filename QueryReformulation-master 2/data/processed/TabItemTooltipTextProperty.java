/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.swt.widgets.TabItem;

/**
* @since 3.3
*
*/
public class TabItemTooltipTextProperty extends WidgetStringValueProperty {

    @Override
    String doGetStringValue(Object source) {
        return ((TabItem) source).getToolTipText();
    }

    @Override
    void doSetStringValue(Object source, String value) {
        ((TabItem) source).setToolTipText(value);
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "TabItem.toolTipText <String>";
    }
}
