/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.swt.widgets.ToolItem;

/**
*
*/
public class ToolItemEnabledProperty extends WidgetBooleanValueProperty {

    @Override
    public boolean doGetBooleanValue(Object source) {
        return ((ToolItem) source).getEnabled();
    }

    @Override
    void doSetBooleanValue(Object source, boolean value) {
        ((ToolItem) source).setEnabled(value);
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "ToolItem.enabled <boolean>";
    }
}
