/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.swt.widgets.Group;

/**
* Text Databinding Property for SWT Groups.
*
* @author Eugen Neufeld
*/
public class GroupTextProperty extends WidgetStringValueProperty {

    @Override
    String doGetStringValue(Object source) {
        return ((Group) source).getText();
    }

    @Override
    void doSetStringValue(Object source, String value) {
        //$NON-NLS-1$
        ((Group) source).setText(value == null ? "" : value);
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "Group.text <String>";
    }
}
