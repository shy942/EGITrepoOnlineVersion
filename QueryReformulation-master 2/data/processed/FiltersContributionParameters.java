/***/
package org.eclipse.ui.views.markers;

import java.util.Map;

/**
* A MarkerFieldParameters is a class that specifies a Map of parameters
* to be passed to a {@link MarkerFieldFilter}.
* @since 3.4
*
*/
public abstract class FiltersContributionParameters {

    /**
* Returns a map keyed names for parameter values. The values should be
* actual values that will be interpreted by the {@link MarkerFieldFilter}
* these parameters are designed for.
*
* Note that these parameters will be sent to the MarkerFieldFilter for
* every visible {@link MarkerField} in a markers view.
*
*
* @return A map of the name of the parameter value (<code>String</code>)
*         to the actual value of the parameter (<code>String</code>).
*/
    public abstract Map getParameterValues();
}
