/***/
package org.eclipse.ui.internal.activities.ws;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.ui.activities.WorkbenchActivityHelper;

/**
* Generic viewer filter that works based on activity enablement.
*
* @since 3.0
*/
public class ActivityViewerFilter extends ViewerFilter {

    private boolean hasEncounteredFilteredItem = false;

    @Override
    public boolean select(Viewer viewer, Object parentElement, Object element) {
        if (WorkbenchActivityHelper.filterItem(element)) {
            setHasEncounteredFilteredItem(true);
            return false;
        }
        return true;
    }

    /**
* @return returns whether the filter has filtered an item
*/
    public boolean getHasEncounteredFilteredItem() {
        return hasEncounteredFilteredItem;
    }

    /**
* @param sets whether the filter has filtered an item
*/
    public void setHasEncounteredFilteredItem(boolean hasEncounteredFilteredItem) {
        this.hasEncounteredFilteredItem = hasEncounteredFilteredItem;
    }
}
