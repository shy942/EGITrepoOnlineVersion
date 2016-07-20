/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.swt.widgets.ScrollBar;

/**
*
*/
public class ScrollBarEnabledProperty extends WidgetBooleanValueProperty {

    @Override
    public boolean doGetBooleanValue(Object source) {
        return ((ScrollBar) source).getEnabled();
    }

    @Override
    void doSetBooleanValue(Object source, boolean value) {
        ((ScrollBar) source).setEnabled(value);
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "ScrollBar.enabled <boolean>";
    }
}
