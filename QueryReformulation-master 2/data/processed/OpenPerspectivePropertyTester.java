/***/
package org.eclipse.ui.internal;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.ui.IPerspectiveDescriptor;
import org.eclipse.ui.IWorkbenchPage;

/**
* Tests if any Perspective is open or not.
*
* @since 3.3
*
*/
public class OpenPerspectivePropertyTester extends PropertyTester {

    //$NON-NLS-1$
    private static final String PROPERTY_IS_PERSPECTIVE_OPEN = "isPerspectiveOpen";

    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        if (args.length == 0 && receiver instanceof WorkbenchWindow) {
            final WorkbenchWindow window = (WorkbenchWindow) receiver;
            if (PROPERTY_IS_PERSPECTIVE_OPEN.equals(property)) {
                IWorkbenchPage page = window.getActivePage();
                if (page != null) {
                    IPerspectiveDescriptor persp = page.getPerspective();
                    if (persp != null) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
