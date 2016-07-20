/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MenuItem;

public class MenuItemSelectionProperty extends WidgetBooleanValueProperty {

    /**
*
*/
    public  MenuItemSelectionProperty() {
        super(SWT.Selection);
    }

    @Override
    boolean doGetBooleanValue(Object source) {
        return ((MenuItem) source).getSelection();
    }

    @Override
    void doSetBooleanValue(Object source, boolean value) {
        ((MenuItem) source).setSelection(value);
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "MenuItem.selection <Boolean>";
    }
}
