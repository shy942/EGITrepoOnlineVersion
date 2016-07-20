/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.swt.widgets.Button;

/**
* @since 3.3
*
*/
public class ButtonTextProperty extends WidgetStringValueProperty {

    @Override
    String doGetStringValue(Object source) {
        return ((Button) source).getText();
    }

    @Override
    void doSetStringValue(Object source, String value) {
        //$NON-NLS-1$
        ((Button) source).setText(value == null ? "" : value);
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "Button.text <String>";
    }
}
