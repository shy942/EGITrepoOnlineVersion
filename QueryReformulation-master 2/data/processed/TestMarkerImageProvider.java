/***/
package org.eclipse.ui.tests.adaptable;

import org.eclipse.core.resources.IMarker;
import org.eclipse.ui.internal.ide.IMarkerImageProvider;

/**
* A test marker image provider.
*/
public class TestMarkerImageProvider implements IMarkerImageProvider {

    @Override
    public String getImagePath(IMarker marker) {
        //$NON-NLS-1$
        return "icons/anything.gif";
    }
}
