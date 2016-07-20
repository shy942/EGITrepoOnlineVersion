/***/
package org.eclipse.ui.internal.views.markers;

import org.eclipse.core.resources.IMarker;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.views.markers.MarkerFieldFilter;
import org.eclipse.ui.views.markers.MarkerItem;

/**
* AllMarkersSeverityAndDescriptionFieldFilter is a
* {@link SeverityAndDescriptionFieldFilter} that handles the no severity case.
*
* @since 3.4
*
*/
public class AllMarkersSeverityAndDescriptionFieldFilter extends SeverityAndDescriptionFieldFilter {

    private boolean filterOnSeverity = false;

    //$NON-NLS-1$
    private static String FILTER_ON_SEVERITY = "FILTER_ON_SEVERITY";

    /**
* Create a new instance of the receiver.
*/
    public  AllMarkersSeverityAndDescriptionFieldFilter() {
        super();
    }

    @Override
    public boolean select(MarkerItem item) {
        if (filterOnSeverity) {
            IMarker marker = item.getMarker();
            if (marker == null)
                return false;
            if (!checkSeverity(item.getAttributeValue(IMarker.SEVERITY, -1)))
                return false;
        }
        return super.select(item);
    }

    /**
* Return whether or not we are filtering on severity.
*
* @return boolean
*/
    boolean getFilterOnSeverity() {
        return filterOnSeverity;
    }

    /**
* Set the whether or not we are filtering on severity
*
* @param filter
*/
    void setFilterOnSeverity(boolean filter) {
        filterOnSeverity = filter;
    }

    @Override
    public void loadSettings(IMemento memento) {
        super.loadSettings(memento);
        Boolean filtering = memento.getBoolean(FILTER_ON_SEVERITY);
        if (filtering != null)
            filterOnSeverity = filtering.booleanValue();
    }

    @Override
    public void saveSettings(IMemento memento) {
        super.saveSettings(memento);
        memento.putBoolean(FILTER_ON_SEVERITY, filterOnSeverity);
    }

    @Override
    public void populateWorkingCopy(MarkerFieldFilter copy) {
        super.populateWorkingCopy(copy);
        ((AllMarkersSeverityAndDescriptionFieldFilter) copy).filterOnSeverity = filterOnSeverity;
    }
}
