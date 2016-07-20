/***/
package org.eclipse.ui.internal.dialogs;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.ui.activities.WorkbenchActivityHelper;

/**
* The CapabilityFilter is a filter that uses the capabilities
* support as filter for items.
*/
public class CapabilityFilter extends ViewerFilter {

    /**
* Create a new instance of a capability filter.
*/
    public  CapabilityFilter() {
        super();
    }

    @Override
    public boolean select(Viewer viewer, Object parentElement, Object element) {
        return !WorkbenchActivityHelper.filterItem(element);
    }
}
