/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.swt.widgets.Text;

/**
* @since 3.3
*
*/
public class TextMessageProperty extends WidgetStringValueProperty {

    @Override
    String doGetStringValue(Object source) {
        return ((Text) source).getMessage();
    }

    @Override
    void doSetStringValue(Object source, String value) {
        //$NON-NLS-1$
        ((Text) source).setMessage(value == null ? "" : value);
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "Text.message<String>";
    }
}
