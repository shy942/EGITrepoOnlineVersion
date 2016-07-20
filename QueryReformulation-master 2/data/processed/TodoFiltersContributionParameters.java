/***/
package org.eclipse.ui.internal.views.markers;

import java.util.HashMap;
import java.util.Map;
import org.eclipse.ui.views.markers.FiltersContributionParameters;
import org.eclipse.ui.views.markers.MarkerSupportConstants;

/**
* TodoFiltersContributionParameters is the filter to just show TODOs.
* @since 3.4
*
*/
public class TodoFiltersContributionParameters extends FiltersContributionParameters {

    private static Map<String, String> todoMap;

    static {
        todoMap = new HashMap();
        //$NON-NLS-1$
        todoMap.put(MarkerSupportConstants.CONTAINS_KEY, "TODO");
    }

    /**
* Return a new instance of the receiver.
*/
    public  TodoFiltersContributionParameters() {
        super();
    }

    @Override
    public Map<String, String> getParameterValues() {
        return todoMap;
    }
}
