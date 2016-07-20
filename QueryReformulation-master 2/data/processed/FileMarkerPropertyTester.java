/***/
package org.eclipse.ui.internal.views.markers;

import org.eclipse.core.expressions.PropertyTester;
import org.eclipse.core.resources.IResource;

/**
* FileMarkerPropertyTester is a property tester for a marker entry to see if it
* is a marker that has a file behind it.
*
* @since 3.4
*
*/
public class FileMarkerPropertyTester extends PropertyTester {

    //$NON-NLS-1$
    private static final Object FILE_MARKER = "fileMarker";

    /**
* Create a new instance of the receiver.
*/
    public  FileMarkerPropertyTester() {
        super();
    }

    @Override
    public boolean test(Object receiver, String property, Object[] args, Object expectedValue) {
        if (property.equals(FILE_MARKER)) {
            if (((MarkerEntry) receiver).getMarker().getResource().getType() == IResource.FILE)
                return true;
        }
        return false;
    }
}
