/***/
package org.eclipse.ui.internal.views.markers;

import java.util.HashMap;
import java.util.Map;
import org.eclipse.core.resources.IMarker;
import org.eclipse.ui.views.markers.FiltersContributionParameters;

/**
* ErrorsWarningsParameters is the parameters for errors and warnings.
*
* @since 3.5
*
*/
public class ErrorsWarningsParameters extends FiltersContributionParameters {

    private static Map<String, Integer> parametersMap;

    static {
        parametersMap = new HashMap();
        parametersMap.put(IMarker.SEVERITY, SeverityAndDescriptionFieldFilter.SEVERITY_WARNING | SeverityAndDescriptionFieldFilter.SEVERITY_ERROR);
    }

    /**
* Create a new instance of the receiver.
*/
    public  ErrorsWarningsParameters() {
        super();
    }

    @Override
    public Map<String, Integer> getParameterValues() {
        return parametersMap;
    }
}
