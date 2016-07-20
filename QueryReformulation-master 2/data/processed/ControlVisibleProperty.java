/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.swt.widgets.Control;

/**
* @since 3.3
*
*/
public class ControlVisibleProperty extends WidgetBooleanValueProperty {

    @Override
    boolean doGetBooleanValue(Object source) {
        return ((Control) source).getVisible();
    }

    @Override
    void doSetBooleanValue(Object source, boolean value) {
        ((Control) source).setVisible(value);
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "Control.visible <boolean>";
    }
}
