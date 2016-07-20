/***/
package org.eclipse.ui.internal.views.markers;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.ui.ide.IDE;

/**
* QuickFixPropertyTester is the property tester for the quick fix object.
*
* @since 3.4
*
*/
public class QuickFixPropertyTester extends PropertyTester {

    //$NON-NLS-1$
    private static final String QUICK_FIX = "quickFix";

    /**
* Create a new instance of the receiver.
*/
    public  QuickFixPropertyTester() {
        super();
    }

    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        if (property.equals(QUICK_FIX))
            return IDE.getMarkerHelpRegistry().hasResolutions(((MarkerEntry) receiver).getMarker());
        return false;
    }
}
