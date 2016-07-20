/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.swt.widgets.Menu;

/**
*
*/
public class MenuEnabledProperty extends WidgetBooleanValueProperty {

    @Override
    public boolean doGetBooleanValue(Object source) {
        return ((Menu) source).getEnabled();
    }

    @Override
    void doSetBooleanValue(Object source, boolean value) {
        ((Menu) source).setEnabled(value);
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "Menu.enabled <boolean>";
    }
}
