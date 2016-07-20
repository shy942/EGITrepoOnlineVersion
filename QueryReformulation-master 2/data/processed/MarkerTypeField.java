/***/
package org.eclipse.ui.internal.views.markers;

import org.eclipse.ui.views.markers.MarkerField;
import org.eclipse.ui.views.markers.MarkerItem;

/**
* MarkerTypeField is the field that defines the marker type.
*
* @since 3.3
*
*/
public class MarkerTypeField extends MarkerField {

    @Override
    public String getValue(MarkerItem item) {
        return ((MarkerSupportItem) item).getMarkerTypeName();
    }
}
