/***/
package org.eclipse.ui.internal.views.markers;

import org.eclipse.core.resources.IMarker;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.views.markers.MarkerItem;

/**
* MarkerSeverityAndDescriptionField can handle severities for all markers.
*
* @since 3.4
*
*/
public class MarkerSeverityAndDescriptionField extends MarkerDescriptionField {

    /**
* Create a new instance of the receiver.
*/
    public  MarkerSeverityAndDescriptionField() {
        super();
    }

    @Override
    public int compare(MarkerItem item1, MarkerItem item2) {
        int severity1 = MarkerSupportInternalUtilities.getSeverity(item1);
        int severity2 = MarkerSupportInternalUtilities.getSeverity(item2);
        if (severity1 == severity2)
            return super.compare(item1, item2);
        return severity2 - severity1;
    }

    /**
* Return the image for item.
*
* @param item
* @return Image or <code>null</code>
*/
    private Image getImage(MarkerItem item) {
        if (item.getMarker() == null) {
            int severity = ((MarkerCategory) item).getHighestSeverity();
            if (severity >= IMarker.SEVERITY_WARNING)
                return MarkerSupportInternalUtilities.getSeverityImage(severity);
            return null;
        }
        int severity = MarkerSupportInternalUtilities.getSeverity(item);
        return MarkerSupportInternalUtilities.getSeverityImage(severity);
    }

    @Override
    public void update(ViewerCell cell) {
        super.update(cell);
        MarkerItem item = (MarkerItem) cell.getElement();
        cell.setImage(annotateImage(item, getImage(item)));
    }
}
