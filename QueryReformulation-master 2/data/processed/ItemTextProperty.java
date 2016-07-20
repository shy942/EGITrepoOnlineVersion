/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.swt.widgets.Item;

/**
* @since 3.3
*
*/
public class ItemTextProperty extends WidgetStringValueProperty {

    @Override
    String doGetStringValue(Object source) {
        return ((Item) source).getText();
    }

    @Override
    void doSetStringValue(Object source, String value) {
        //$NON-NLS-1$
        ((Item) source).setText(value == null ? "" : value);
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "Item.text <String>";
    }
}
