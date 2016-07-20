/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.swt.widgets.Label;

/**
* @since 3.3
*
*/
public class LabelTextProperty extends WidgetStringValueProperty {

    @Override
    String doGetStringValue(Object source) {
        return ((Label) source).getText();
    }

    @Override
    void doSetStringValue(Object source, String value) {
        //$NON-NLS-1$
        ((Label) source).setText(value == null ? "" : value);
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "Label.text <String>";
    }
}
