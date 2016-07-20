/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.swt.widgets.Link;

/**
* @since 3.3
*
*/
public class LinkTextProperty extends WidgetStringValueProperty {

    @Override
    String doGetStringValue(Object source) {
        return ((Link) source).getText();
    }

    @Override
    void doSetStringValue(Object source, String value) {
        //$NON-NLS-1$
        ((Link) source).setText(value == null ? "" : value);
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "Link.text <String>";
    }
}
