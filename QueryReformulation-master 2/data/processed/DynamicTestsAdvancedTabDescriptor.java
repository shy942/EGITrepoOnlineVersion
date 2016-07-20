/***/
package org.eclipse.ui.tests.views.properties.tabbed.dynamic.tab.descriptors;

import org.eclipse.ui.tests.views.properties.tabbed.dynamic.section.descriptors.DynamicTestsAdvancedSectionDescriptor;
import org.eclipse.ui.tests.views.properties.tabbed.dynamic.views.DynamicTestsTypeMapper;
import org.eclipse.ui.views.properties.tabbed.AbstractTabDescriptor;

/**
* A tab descriptor for the dynamic tests view.
*
* @author Anthony Hunter
*/
public class DynamicTestsAdvancedTabDescriptor extends AbstractTabDescriptor {

    public  DynamicTestsAdvancedTabDescriptor() {
        super();
        getSectionDescriptors().add(new DynamicTestsAdvancedSectionDescriptor(new DynamicTestsTypeMapper()));
    }

    @Override
    public String getCategory() {
        //$NON-NLS-1$
        return "advanced";
    }

    @Override
    public String getId() {
        //$NON-NLS-1$
        return "AdvancedTab";
    }

    @Override
    public String getLabel() {
        //$NON-NLS-1$
        return "Advanced";
    }
}
