/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.swt.widgets.Text;

/**
* @since 3.3
*
*/
public class TextEditableProperty extends WidgetBooleanValueProperty {

    @Override
    boolean doGetBooleanValue(Object source) {
        return ((Text) source).getEditable();
    }

    @Override
    void doSetBooleanValue(Object source, boolean value) {
        ((Text) source).setEditable(value);
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "Text.editable <boolean>";
    }
}
