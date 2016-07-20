/***/
package org.eclipse.ui.tests.views.properties.tabbed.dynamic.section.descriptors;

import org.eclipse.ui.tests.views.properties.tabbed.dynamic.views.DynamicTestsTypeMapper;
import org.eclipse.ui.views.properties.tabbed.ISectionDescriptor;
import org.eclipse.ui.views.properties.tabbed.ISectionDescriptorProvider;

/**
* A section descriptor provider for the dynamic tests view.
*
* @author Anthony Hunter
*/
public class DynamicTestsSectionDescriptorProvider implements ISectionDescriptorProvider {

    @Override
    public ISectionDescriptor[] getSectionDescriptors() {
        return new ISectionDescriptor[] { new DynamicTestsStarSectionDescriptor(new DynamicTestsTypeMapper()), new DynamicTestsBlueSectionDescriptor(new DynamicTestsTypeMapper()), new DynamicTestsCircleSectionDescriptor(new DynamicTestsTypeMapper()), new DynamicTestsElementSectionDescriptor(new DynamicTestsTypeMapper()), new DynamicTestsGreenSectionDescriptor(new DynamicTestsTypeMapper()), new DynamicTestsAdvancedSectionDescriptor(new DynamicTestsTypeMapper()), new DynamicTestsRedSectionDescriptor(new DynamicTestsTypeMapper()), new DynamicTestsSquareSectionDescriptor(new DynamicTestsTypeMapper()), new DynamicTestsTriangleSectionDescriptor(new DynamicTestsTypeMapper()) };
    }
}
