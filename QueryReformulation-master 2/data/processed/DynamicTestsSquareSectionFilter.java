/***/
package org.eclipse.ui.tests.views.properties.tabbed.dynamic.filters;

import org.eclipse.ui.tests.views.properties.tabbed.dynamic.model.DynamicTestsShape;

/**
* A section filter for the dynamic tests view.
*
* @author Anthony Hunter
*/
public class DynamicTestsSquareSectionFilter extends DynamicTestsElementSectionFilter {

    @Override
    protected boolean appliesToShape(DynamicTestsShape shape) {
        return DynamicTestsShape.SQUARE.equals(shape);
    }
}