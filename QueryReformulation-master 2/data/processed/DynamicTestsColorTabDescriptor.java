/***/
package org.eclipse.ui.tests.views.properties.tabbed.dynamic.tab.descriptors;

import org.eclipse.ui.tests.views.properties.tabbed.dynamic.section.descriptors.DynamicTestsBlueSectionDescriptor;
import org.eclipse.ui.tests.views.properties.tabbed.dynamic.section.descriptors.DynamicTestsGreenSectionDescriptor;
import org.eclipse.ui.tests.views.properties.tabbed.dynamic.section.descriptors.DynamicTestsRedSectionDescriptor;
import org.eclipse.ui.tests.views.properties.tabbed.dynamic.views.DynamicTestsTypeMapper;
import org.eclipse.ui.views.properties.tabbed.AbstractTabDescriptor;

/**
* A tab descriptor for the dynamic tests view.
*
* @author Anthony Hunter
*/
public class DynamicTestsColorTabDescriptor extends AbstractTabDescriptor {

    public  DynamicTestsColorTabDescriptor() {
        super();
        getSectionDescriptors().add(new DynamicTestsRedSectionDescriptor(new DynamicTestsTypeMapper()));
        getSectionDescriptors().add(new DynamicTestsGreenSectionDescriptor(new DynamicTestsTypeMapper()));
        getSectionDescriptors().add(new DynamicTestsBlueSectionDescriptor(new DynamicTestsTypeMapper()));
    }

    @Override
    public String getAfterTab() {
        //$NON-NLS-1$
        return "ElementTab";
    }

    @Override
    public String getCategory() {
        //$NON-NLS-1$
        return "default";
    }

    @Override
    public String getId() {
        //$NON-NLS-1$
        return "ColorTab";
    }

    @Override
    public String getLabel() {
        //$NON-NLS-1$
        return "Color";
    }
}
