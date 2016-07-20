/***/
package org.eclipse.ui.internal.views.markers;

import org.eclipse.core.resources.IMarker;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.views.markers.MarkerItem;
import org.eclipse.ui.views.markers.internal.ProblemFilter;

/**
* ProblemsSeverityAndDescriptionFieldFilter is the filter used by the problems
* view.
*
* @since 3.4
*
*/
public class ProblemsSeverityAndDescriptionFieldFilter extends SeverityAndDescriptionFieldFilter {

    @Override
    public boolean select(MarkerItem item) {
        IMarker marker = item.getMarker();
        if (marker == null)
            return false;
        int markerSeverity = item.getAttributeValue(IMarker.SEVERITY, -1);
        if (markerSeverity < 0)
            return false;
        if (checkSeverity(markerSeverity))
            return super.select(item);
        return false;
    }

    @Override
    void loadLegacySettings(IMemento memento, MarkerContentGenerator generator) {
        super.loadLegacySettings(memento, generator);
        Integer severitySetting = memento.getInteger(ProblemFilter.TAG_SEVERITY);
        if (severitySetting != null) {
            selectedSeverities = severitySetting.intValue();
        }
    }

    @Override
    public void initialize(ProblemFilter problemFilter) {
        super.initialize(problemFilter);
        if (problemFilter.getSeverity() > 0)
            selectedSeverities = problemFilter.getSeverity();
    }
}
