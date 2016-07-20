/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.List;

/**
* @since 3.3
*
*/
public class ListSelectionProperty extends WidgetStringValueProperty {

    /**
*
*/
    public  ListSelectionProperty() {
        super(SWT.Selection);
    }

    @Override
    String doGetStringValue(Object source) {
        List list = (List) source;
        int index = list.getSelectionIndex();
        if (index >= 0)
            return list.getItem(index);
        return null;
    }

    @Override
    void doSetStringValue(Object source, String value) {
        List list = (List) source;
        String items[] = list.getItems();
        int index = -1;
        if (items != null && value != null) {
            for (int i = 0; i < items.length; i++) {
                if (value.equals(items[i])) {
                    index = i;
                    break;
                }
            }
            list.select(index);
        }
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "List.selection <String>";
    }
}
