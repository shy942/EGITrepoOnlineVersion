/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.swt.custom.StyledText;

/**
* @since 3.3
*
*/
public class StyledTextEditableProperty extends WidgetBooleanValueProperty {

    @Override
    boolean doGetBooleanValue(Object source) {
        return ((StyledText) source).getEditable();
    }

    @Override
    void doSetBooleanValue(Object source, boolean value) {
        ((StyledText) source).setEditable(value);
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "StyledText.editable <boolean>";
    }
}
