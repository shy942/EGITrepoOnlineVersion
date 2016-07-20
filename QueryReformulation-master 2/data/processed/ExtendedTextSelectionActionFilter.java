/***/
package org.eclipse.ui.tests.internal;

import org.eclipse.jface.text.ITextSelection;
import org.eclipse.ui.IActionFilter;

public class ExtendedTextSelectionActionFilter implements IActionFilter {

    /**
* An attribute indicating the selection length (value <code>"isEmpty"</code>).
* The attribute value in xml must be one of <code>"true" or "false"</code>.
*/
    //$NON-NLS-1$
    public static final String IS_EMPTY = "isEmpty";

    /**
* An attribute indicating the selection text (value <code>"text"</code>).
* The attribute value in xml is unconstrained.
*/
    //$NON-NLS-1$
    public static final String TEXT = "text";

    /**
* An attribute indicating the selection text (value <code>"text"</code>).
* The attribute value in xml is unconstrained.
*/
    //$NON-NLS-1$
    public static final String CASE_INSENSITIVE_TEXT = "caseInsensitiveText";

    /*
* @see IActionFilter#testAttribute(Object, String, String)
*/
    @Override
    public boolean testAttribute(Object target, String name, String value) {
        ITextSelection sel = (ITextSelection) target;
        if (name.equals(IS_EMPTY)) {
            return (sel.getLength() == 0);
        } else if (name.equals(TEXT)) {
            String text = sel.getText();
            return (text.indexOf(value) >= 0);
        } else if (name.equals(CASE_INSENSITIVE_TEXT)) {
            String text = sel.getText().toLowerCase();
            value = value.toLowerCase();
            return (text.indexOf(value) >= 0);
        }
        return false;
    }
}
