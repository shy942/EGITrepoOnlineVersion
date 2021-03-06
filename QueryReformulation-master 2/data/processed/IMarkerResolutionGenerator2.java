/***/
package org.eclipse.ui;

import org.eclipse.core.resources.IMarker;

/**
* A marker resolution generator should implement this interface rather than
* <code>IMarkerResolutionGenerator</code> if it can determine whether a particular marker
* has any resolutions more efficiently than computing all the resolutions.
*
* @since 2.1
*/
public interface IMarkerResolutionGenerator2 extends IMarkerResolutionGenerator {

    /**
* Returns whether there are any resolutions for the given marker.
*
* @param marker the marker
* @return <code>true</code> if there are resolutions for the given marker,
*   <code>false</code> if not
*/
    public boolean hasResolutions(IMarker marker);
}
