/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.swt.custom.CLabel;

/**
* @since 3.3
*
*/
public class CLabelTextProperty extends WidgetStringValueProperty {

    @Override
    String doGetStringValue(Object source) {
        return ((CLabel) source).getText();
    }

    @Override
    void doSetStringValue(Object source, String value) {
        //$NON-NLS-1$
        ((CLabel) source).setText(value == null ? "" : value);
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "CLabel.text <String>";
    }
}
