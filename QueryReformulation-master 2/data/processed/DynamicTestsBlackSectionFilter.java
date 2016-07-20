/***/
package org.eclipse.ui.tests.views.properties.tabbed.dynamic.filters;

import org.eclipse.ui.tests.views.properties.tabbed.dynamic.model.DynamicTestsColor;

/**
* A section filter for the dynamic tests view.
*
* @author Anthony Hunter
*/
public class DynamicTestsBlackSectionFilter extends DynamicTestsElementSectionFilter {

    @Override
    protected boolean appliesToColor(DynamicTestsColor color) {
        return DynamicTestsColor.BLACK.equals(color);
    }
}
