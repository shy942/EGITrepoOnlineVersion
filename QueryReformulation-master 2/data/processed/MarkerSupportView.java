/***/
package org.eclipse.ui.views.markers;

import org.eclipse.ui.internal.views.markers.ExtendedMarkersView;

/**
* The MarkerSupportView is a view that supports the extensions
* in the markerSupport extension point.
* @since 3.4
*
*/
public abstract class MarkerSupportView extends ExtendedMarkersView {

    /**
* Create a new instance of the receiver on contentGeneratorId.
* @param contentGeneratorId the id of a markerContentGenerator
* 	defined in an extension of the markerSupport extension.
*/
    public  MarkerSupportView(String contentGeneratorId) {
        super(contentGeneratorId);
    }
}
