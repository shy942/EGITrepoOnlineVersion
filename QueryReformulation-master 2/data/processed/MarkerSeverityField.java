/***/
package org.eclipse.ui.internal.views.markers;

import org.eclipse.core.resources.IMarker;
import org.eclipse.ui.views.markers.MarkerField;
import org.eclipse.ui.views.markers.MarkerItem;
import org.eclipse.ui.views.markers.internal.MarkerMessages;

/**
* MarkerSeverityField is the field for showing severity categories.
*
* @since 3.4
*
*/
public class MarkerSeverityField extends MarkerField {

    @Override
    public String getValue(MarkerItem item) {
        switch(item.getAttributeValue(IMarker.SEVERITY, -1)) {
            case 2:
                return MarkerMessages.filtersDialog_severityError;
            case 1:
                return MarkerMessages.filtersDialog_severityWarning;
            case 0:
                return MarkerMessages.filtersDialog_severityInfo;
            default:
                return MarkerSupportInternalUtilities.EMPTY_STRING;
        }
    }

    @Override
    public int compare(MarkerItem item1, MarkerItem item2) {
        return MarkerSupportInternalUtilities.getSeverity(item2) - MarkerSupportInternalUtilities.getSeverity(item1);
    }
}
