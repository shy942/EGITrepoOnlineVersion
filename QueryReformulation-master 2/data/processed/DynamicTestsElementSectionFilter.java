/***/
package org.eclipse.ui.tests.views.properties.tabbed.dynamic.filters;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.IFilter;
import org.eclipse.ui.tests.views.properties.tabbed.dynamic.model.DynamicTestsColor;
import org.eclipse.ui.tests.views.properties.tabbed.dynamic.model.DynamicTestsElement;
import org.eclipse.ui.tests.views.properties.tabbed.dynamic.model.DynamicTestsShape;
import org.eclipse.ui.tests.views.properties.tabbed.dynamic.views.DynamicTestsTreeNode;

/**
* A section filter for the dynamic tests view.
*
* @author Anthony Hunter
*/
public class DynamicTestsElementSectionFilter implements IFilter {

    /**
* Determine if the filter applies to the provided color.
*
* @param element
*            the element.
* @return true if the filter applies to the provided color.
*/
    protected boolean appliesToColor(DynamicTestsColor color) {
        Assert.isNotNull(color);
        return true;
    }

    /**
* Determine if the filter applies to the provided shape.
*
* @param element
*            the element.
* @return true if the filter applies to the provided shape.
*/
    protected boolean appliesToShape(DynamicTestsShape shape) {
        Assert.isNotNull(shape);
        return true;
    }

    @Override
    public boolean select(Object object) {
        if (object instanceof DynamicTestsTreeNode) {
            DynamicTestsElement element = ((DynamicTestsTreeNode) object).getDynamicTestsElement();
            DynamicTestsColor color = (DynamicTestsColor) element.getPropertyValue(DynamicTestsElement.ID_COLOR);
            DynamicTestsShape shape = (DynamicTestsShape) element.getPropertyValue(DynamicTestsElement.ID_SHAPE);
            return (appliesToColor(color) && appliesToShape(shape));
        }
        return false;
    }
}
