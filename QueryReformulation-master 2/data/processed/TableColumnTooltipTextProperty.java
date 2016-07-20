/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.swt.widgets.TableColumn;

/**
* @since 3.3
*
*/
public class TableColumnTooltipTextProperty extends WidgetStringValueProperty {

    @Override
    String doGetStringValue(Object source) {
        return ((TableColumn) source).getToolTipText();
    }

    @Override
    void doSetStringValue(Object source, String value) {
        ((TableColumn) source).setToolTipText(value);
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "TableColumn.toolTipText <String>";
    }
}
