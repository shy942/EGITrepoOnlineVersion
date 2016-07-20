/***/
package org.eclipse.ui.internal.views.markers;

import org.eclipse.ui.views.markers.MarkerField;
import org.eclipse.ui.views.markers.MarkerItem;

/**
* @since 3.4
*
*/
public class MarkerIDField extends MarkerField {

    @Override
    public String getValue(MarkerItem item) {
        if (item.getMarker() != null)
            return String.valueOf(((MarkerSupportItem) item).getID());
        return MarkerSupportInternalUtilities.EMPTY_STRING;
    }

    @Override
    public int compare(MarkerItem item1, MarkerItem item2) {
        return (int) (((MarkerSupportItem) item1).getID() - ((MarkerSupportItem) item2).getID());
    }
}
