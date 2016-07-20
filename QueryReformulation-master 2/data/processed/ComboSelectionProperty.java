/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;

/**
* @since 3.3
*
*/
public class ComboSelectionProperty extends WidgetStringValueProperty {

    /**
*
*/
    public  ComboSelectionProperty() {
        super(SWT.Modify);
    }

    @Override
    String doGetStringValue(Object source) {
        return ((Combo) source).getText();
    }

    @Override
    void doSetStringValue(Object source, String value) {
        Combo combo = (Combo) source;
        String items[] = combo.getItems();
        int index = -1;
        if (items != null && value != null) {
            for (int i = 0; i < items.length; i++) {
                if (value.equals(items[i])) {
                    index = i;
                    break;
                }
            }
            if (index == -1) {
                combo.setText(value);
            } else {
                // -1 will not "unselect"
                combo.select(index);
            }
        }
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "Combo.selection <String>";
    }
}
