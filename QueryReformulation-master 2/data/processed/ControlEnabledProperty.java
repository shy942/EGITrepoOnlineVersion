/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.swt.widgets.Control;

/**
* @since 3.3
*
*/
public class ControlEnabledProperty extends WidgetBooleanValueProperty {

    @Override
    public boolean doGetBooleanValue(Object source) {
        return ((Control) source).getEnabled();
    }

    @Override
    void doSetBooleanValue(Object source, boolean value) {
        ((Control) source).setEnabled(value);
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "Control.enabled <boolean>";
    }
}
