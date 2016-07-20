/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;

/**
* @since 3.3
*
*/
public class ButtonSelectionProperty extends WidgetBooleanValueProperty {

    /**
*
*/
    public  ButtonSelectionProperty() {
        super(SWT.Selection);
    }

    @Override
    boolean doGetBooleanValue(Object source) {
        return ((Button) source).getSelection();
    }

    @Override
    void doSetBooleanValue(Object source, boolean value) {
        ((Button) source).setSelection(value);
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "Button.selection <Boolean>";
    }
}
