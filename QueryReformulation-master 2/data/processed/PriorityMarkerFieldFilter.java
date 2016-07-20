/***/
package org.eclipse.ui.internal.views.markers;

import org.eclipse.core.resources.IMarker;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.views.markers.MarkerFieldFilter;
import org.eclipse.ui.views.markers.MarkerItem;
import org.eclipse.ui.views.markers.internal.ProblemFilter;

/**
* PriorityMarkerFieldFilter is the field filter for priority in markers
*
* @since 3.4
*
*/
public class PriorityMarkerFieldFilter extends CompatibilityFieldFilter {

    static final int PRIORITY_HIGH = 1 << IMarker.PRIORITY_HIGH;

    static final int PRIORITY_NORMAL = 1 << IMarker.PRIORITY_NORMAL;

    static final int PRIORITY_LOW = 1 << IMarker.PRIORITY_LOW;

    //$NON-NLS-1$
    private static final String TAG_SELECTED_PRIORITIES = "selectedPriorities";

    /**
* Tag for the priority value.
*/
    //$NON-NLS-1$
    private static final String TAG_PRIORITY = "priority";

    int selectedPriorities = PRIORITY_HIGH + PRIORITY_LOW + PRIORITY_NORMAL;

    /**
* Create a new instance of the receiver
*/
    public  PriorityMarkerFieldFilter() {
        super();
    }

    @Override
    public void loadSettings(IMemento memento) {
        Integer priority = memento.getInteger(TAG_SELECTED_PRIORITIES);
        if (priority == null)
            return;
        selectedPriorities = priority.intValue();
    }

    @Override
    void loadLegacySettings(IMemento memento, MarkerContentGenerator generator) {
        String setting = memento.getString(TAG_PRIORITY);
        if (setting != null) {
            try {
                selectedPriorities = Integer.parseInt(setting);
            } catch (NumberFormatException eNumberFormat) {
            }
        }
    }

    @Override
    public void initialize(ProblemFilter problemFilter) {
    //There is no problem filter support for priority
    }

    @Override
    public void saveSettings(IMemento memento) {
        memento.putInteger(TAG_SELECTED_PRIORITIES, selectedPriorities);
    }

    @Override
    public boolean select(MarkerItem item) {
        if (selectedPriorities == 0)
            return true;
        IMarker marker = item.getMarker();
        if (marker == null)
            return false;
        int markerPriority = 1 << marker.getAttribute(IMarker.PRIORITY, IMarker.PRIORITY_NORMAL);
        switch(markerPriority) {
            case PRIORITY_HIGH:
                return (selectedPriorities & PRIORITY_HIGH) > 0;
            case PRIORITY_NORMAL:
                return (selectedPriorities & PRIORITY_NORMAL) > 0;
            case PRIORITY_LOW:
                return (selectedPriorities & PRIORITY_LOW) > 0;
            default:
                return true;
        }
    }

    @Override
    public void populateWorkingCopy(MarkerFieldFilter copy) {
        super.populateWorkingCopy(copy);
        ((PriorityMarkerFieldFilter) copy).selectedPriorities = selectedPriorities;
    }
}
