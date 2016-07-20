/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.swt.widgets.TreeColumn;

/**
* @since 3.3
*
*/
public class TreeColumnTooltipTextProperty extends WidgetStringValueProperty {

    @Override
    String doGetStringValue(Object source) {
        return ((TreeColumn) source).getToolTipText();
    }

    @Override
    void doSetStringValue(Object source, String value) {
        ((TreeColumn) source).setToolTipText(value);
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "TreeColumn.toolTipText <String>";
    }
}
