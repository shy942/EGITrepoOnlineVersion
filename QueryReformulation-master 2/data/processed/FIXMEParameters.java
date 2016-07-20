/***/
package org.eclipse.ui.tests.markers;

import java.util.HashMap;
import java.util.Map;
import org.eclipse.ui.views.markers.FiltersContributionParameters;
import org.eclipse.ui.views.markers.MarkerSupportConstants;

public class FIXMEParameters extends FiltersContributionParameters {

    private static Map fixmeMap;

    static {
        fixmeMap = new HashMap();
        //$NON-NLS-1$
        fixmeMap.put(MarkerSupportConstants.CONTAINS_KEY, "FIXME");
    }

    /**
* The parameters for the fixme test.
*/
    public  FIXMEParameters() {
    }

    @Override
    public Map getParameterValues() {
        return fixmeMap;
    }
}
