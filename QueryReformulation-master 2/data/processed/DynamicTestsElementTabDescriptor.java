/***/
package org.eclipse.ui.tests.views.properties.tabbed.dynamic.tab.descriptors;

import org.eclipse.ui.tests.views.properties.tabbed.dynamic.section.descriptors.DynamicTestsElementSectionDescriptor;
import org.eclipse.ui.tests.views.properties.tabbed.dynamic.views.DynamicTestsTypeMapper;
import org.eclipse.ui.views.properties.tabbed.AbstractTabDescriptor;

/**
* A tab descriptor for the dynamic tests view.
*
* @author Anthony Hunter
*/
public class DynamicTestsElementTabDescriptor extends AbstractTabDescriptor {

    public  DynamicTestsElementTabDescriptor() {
        super();
        getSectionDescriptors().add(new DynamicTestsElementSectionDescriptor(new DynamicTestsTypeMapper()));
    }

    @Override
    public String getCategory() {
        //$NON-NLS-1$
        return "default";
    }

    @Override
    public String getId() {
        //$NON-NLS-1$
        return "ElementTab";
    }

    @Override
    public String getLabel() {
        //$NON-NLS-1$
        return "Element";
    }
}
