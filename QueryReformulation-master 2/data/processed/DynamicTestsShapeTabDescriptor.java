/***/
package org.eclipse.ui.tests.views.properties.tabbed.dynamic.tab.descriptors;

import org.eclipse.ui.tests.views.properties.tabbed.dynamic.section.descriptors.DynamicTestsCircleSectionDescriptor;
import org.eclipse.ui.tests.views.properties.tabbed.dynamic.section.descriptors.DynamicTestsSquareSectionDescriptor;
import org.eclipse.ui.tests.views.properties.tabbed.dynamic.section.descriptors.DynamicTestsTriangleSectionDescriptor;
import org.eclipse.ui.tests.views.properties.tabbed.dynamic.views.DynamicTestsTypeMapper;
import org.eclipse.ui.views.properties.tabbed.AbstractTabDescriptor;

/**
* A tab descriptor for the dynamic tests view.
*
* @author Anthony Hunter
*/
public class DynamicTestsShapeTabDescriptor extends AbstractTabDescriptor {

    public  DynamicTestsShapeTabDescriptor() {
        super();
        getSectionDescriptors().add(new DynamicTestsCircleSectionDescriptor(new DynamicTestsTypeMapper()));
        getSectionDescriptors().add(new DynamicTestsSquareSectionDescriptor(new DynamicTestsTypeMapper()));
        getSectionDescriptors().add(new DynamicTestsTriangleSectionDescriptor(new DynamicTestsTypeMapper()));
    }

    @Override
    public String getAfterTab() {
        //$NON-NLS-1$
        return "ColorTab";
    }

    @Override
    public String getCategory() {
        //$NON-NLS-1$
        return "default";
    }

    @Override
    public String getId() {
        //$NON-NLS-1$
        return "ShapeTab";
    }

    @Override
    public String getLabel() {
        //$NON-NLS-1$
        return "Shape";
    }
}
