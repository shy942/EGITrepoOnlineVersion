/***/
package org.eclipse.ui.internal;

import org.eclipse.core.expressions.PropertyTester;

/**
* Tests various workbench window properties.
*
* @since 3.3
*
*/
public class WorkbenchWindowPropertyTester extends PropertyTester {

    //$NON-NLS-1$
    private static final String PROPERTY_IS_COOLBAR_VISIBLE = "isCoolbarVisible";

    //$NON-NLS-1$
    private static final String PROPERTY_IS_PERSPECTIVEBAR_VISIBLE = "isPerspectiveBarVisible";

    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        if (args.length == 0 && receiver instanceof WorkbenchWindow) {
            boolean defaultExpectedValue = true;
            if (expectedValue != null) {
                if (expectedValue instanceof Boolean)
                    defaultExpectedValue = ((Boolean) expectedValue).booleanValue();
                else
                    // cant work with anything else
                    return false;
            }
            final WorkbenchWindow window = (WorkbenchWindow) receiver;
            if (PROPERTY_IS_COOLBAR_VISIBLE.equals(property)) {
                return defaultExpectedValue == window.getCoolBarVisible();
            } else if (PROPERTY_IS_PERSPECTIVEBAR_VISIBLE.equals(property)) {
                return defaultExpectedValue == window.getPerspectiveBarVisible();
            }
        }
        return false;
    }
}
