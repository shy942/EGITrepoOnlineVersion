/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.swt.widgets.Shell;

/**
* @since 3.3
*
*/
public class ShellTextProperty extends WidgetStringValueProperty {

    @Override
    String doGetStringValue(Object source) {
        return ((Shell) source).getText();
    }

    @Override
    void doSetStringValue(Object source, String value) {
        //$NON-NLS-1$
        ((Shell) source).setText(value == null ? "" : value);
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "Shell.text <String>";
    }
}
