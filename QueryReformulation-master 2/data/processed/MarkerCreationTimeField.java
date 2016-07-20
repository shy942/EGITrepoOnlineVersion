/***/
package org.eclipse.ui.internal.views.markers;

import java.util.Date;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.ui.views.markers.MarkerField;
import org.eclipse.ui.views.markers.MarkerItem;
import com.ibm.icu.text.DateFormat;

/**
* MarkerCreationTimeField is the field that shows the creation time of a field.
*
* @since 3.4
*
*/
public class MarkerCreationTimeField extends MarkerField {

    private DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.SHORT);

    @Override
    public String getValue(MarkerItem item) {
        long creationTime = ((MarkerSupportItem) item).getCreationTime();
        if (creationTime < 0)
            return MarkerSupportInternalUtilities.EMPTY_STRING;
        return String.valueOf(creationTime);
    }

    @Override
    public int compare(MarkerItem item1, MarkerItem item2) {
        return (int) (((MarkerSupportItem) item1).getCreationTime() - ((MarkerSupportItem) item2).getCreationTime());
    }

    @Override
    public void update(ViewerCell cell) {
        Object element = cell.getElement();
        String timeStr = MarkerSupportInternalUtilities.EMPTY_STRING;
        if (element instanceof MarkerEntry) {
            long creationTime = ((MarkerEntry) element).getCreationTime();
            if (creationTime > 0) {
                Date date = new Date(creationTime);
                timeStr = dateFormat.format(date);
            }
        }
        cell.setText(timeStr);
    }
}
