/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;

/**
* @since 3.3
*
*/
public class ComboTextProperty extends WidgetStringValueProperty {

    /**
*
*/
    public  ComboTextProperty() {
        super(SWT.Modify);
    }

    @Override
    String doGetStringValue(Object source) {
        return ((Combo) source).getText();
    }

    @Override
    void doSetStringValue(Object source, String value) {
        //$NON-NLS-1$
        ((Combo) source).setText(value != null ? value : "");
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "Combo.text <String>";
    }
}
