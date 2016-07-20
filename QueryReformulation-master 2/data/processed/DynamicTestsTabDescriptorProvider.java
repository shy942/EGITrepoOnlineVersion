/***/
package org.eclipse.ui.tests.views.properties.tabbed.dynamic.tab.descriptors;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.views.properties.tabbed.ITabDescriptor;
import org.eclipse.ui.views.properties.tabbed.ITabDescriptorProvider;

/**
* A tab descriptor provider for the dynamic tests view.
*
* @author Anthony Hunter
*/
public class DynamicTestsTabDescriptorProvider implements ITabDescriptorProvider {

    @Override
    public ITabDescriptor[] getTabDescriptors(IWorkbenchPart part, ISelection selection) {
        return new ITabDescriptor[] { new DynamicTestsElementTabDescriptor(), new DynamicTestsShapeTabDescriptor(), new DynamicTestsColorTabDescriptor(), new DynamicTestsAdvancedTabDescriptor(), new DynamicTestsBlackTabDescriptor() };
    }
}
