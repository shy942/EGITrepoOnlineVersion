/***/
package org.eclipse.ui.internal.views.markers;

import org.eclipse.ui.views.markers.internal.ProblemFilter;

/**
* CompatibilityMarkerFieldFilterGroup is a filter group that uses a
* {@link ProblemFilter}.
*
* @since 3.4
*
*/
public class CompatibilityMarkerFieldFilterGroup extends MarkerFieldFilterGroup {

    ProblemFilter problemFilter;

    /**
* Create a new instance of the receiver based on the ProblemFilter.
*
* @param filter
* @param cachedMarkerBuilder
*/
    public  CompatibilityMarkerFieldFilterGroup(ProblemFilter filter, MarkerContentGenerator generator) {
        super(null, generator);
        problemFilter = filter;
        setEnabled(filter.isEnabled());
        setScope(filter.getOnResource());
    }

    @Override
    public String getID() {
        return problemFilter.getId();
    }

    @Override
    public String getName() {
        return problemFilter.getName();
    }

    @Override
    public boolean isSystem() {
        return true;
    }

    @Override
    MarkerFieldFilterGroup makeWorkingCopy() {
        CompatibilityMarkerFieldFilterGroup clone = new CompatibilityMarkerFieldFilterGroup(this.problemFilter, this.generator);
        if (populateClone(clone))
            return clone;
        return null;
    }

    @Override
    protected void calculateFilters() {
        super.calculateFilters();
        // Now initialize with the ProblemFilter
        for (int i = 0; i < fieldFilters.length; i++) {
            if (fieldFilters[i] instanceof CompatibilityFieldFilter)
                ((CompatibilityFieldFilter) fieldFilters[i]).initialize(problemFilter);
        }
    }
}
