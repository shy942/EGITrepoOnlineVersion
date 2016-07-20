/***/
package org.eclipse.ui.internal.views.markers;

import java.util.HashMap;
import java.util.Map;
import org.eclipse.core.resources.IMarker;
import org.eclipse.ui.views.markers.FiltersContributionParameters;

/**
* SelectedWarningsParameters is the parameters for warnings.
*
* @since 3.4
*
*/
public class SelectedWarningsParameters extends FiltersContributionParameters {

    private static Map<String, Integer> warningsMap;

    static {
        warningsMap = new HashMap();
        warningsMap.put(IMarker.SEVERITY, SeverityAndDescriptionFieldFilter.SEVERITY_WARNING);
    }

    /**
* Create a new instance of the receiver.
*/
    public  SelectedWarningsParameters() {
        super();
    }

    @Override
    public Map<String, Integer> getParameterValues() {
        return warningsMap;
    }
}
