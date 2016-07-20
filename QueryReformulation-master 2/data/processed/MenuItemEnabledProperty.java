/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.swt.widgets.MenuItem;

/**
*
*/
public class MenuItemEnabledProperty extends WidgetBooleanValueProperty {

    @Override
    public boolean doGetBooleanValue(Object source) {
        return ((MenuItem) source).getEnabled();
    }

    @Override
    void doSetBooleanValue(Object source, boolean value) {
        ((MenuItem) source).setEnabled(value);
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "MenuItem.enabled <boolean>";
    }
}
