/***/
package org.eclipse.ui.internal.views.markers;

import org.eclipse.osgi.util.TextProcessor;
import org.eclipse.ui.views.markers.MarkerField;
import org.eclipse.ui.views.markers.MarkerItem;
import org.eclipse.ui.views.markers.MarkerViewUtil;

/**
* MarkerResourceField is the field that specifies the resource column.
*
* @since 3.4
*
*/
public class MarkerResourceField extends MarkerField {

    @Override
    public String getValue(MarkerItem item) {
        if (item.getMarker() == null)
            return MarkerSupportInternalUtilities.EMPTY_STRING;
        return TextProcessor.process(item.getAttributeValue(MarkerViewUtil.NAME_ATTRIBUTE, item.getMarker().getResource().getName()));
    }
}
