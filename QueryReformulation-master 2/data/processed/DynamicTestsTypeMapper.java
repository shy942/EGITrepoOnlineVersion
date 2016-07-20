/***/
package org.eclipse.ui.tests.views.properties.tabbed.dynamic.views;

import org.eclipse.ui.views.properties.tabbed.AbstractTypeMapper;

/**
* A type mapper for nodes in the dynamic tests view.
*
* @author Anthony Hunter
*/
public class DynamicTestsTypeMapper extends AbstractTypeMapper {

    @Override
    public Class mapType(Object object) {
        if (object instanceof DynamicTestsTreeNode) {
            return ((DynamicTestsTreeNode) object).getDynamicTestsElement().getClass();
        }
        return super.mapType(object);
    }
}
