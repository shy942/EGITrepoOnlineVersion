/***/
package org.eclipse.ui.tests.views.properties.tabbed.dynamic.section.descriptors;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.ui.tests.views.properties.tabbed.dynamic.model.DynamicTestsElement;
import org.eclipse.ui.views.properties.tabbed.AbstractSectionDescriptor;
import org.eclipse.ui.views.properties.tabbed.AdvancedPropertySection;
import org.eclipse.ui.views.properties.tabbed.ISection;
import org.eclipse.ui.views.properties.tabbed.ITypeMapper;

/**
* A section descriptor for the dynamic tests view.
*
* @author Anthony Hunter
*/
public class DynamicTestsAdvancedSectionDescriptor extends AbstractSectionDescriptor {

    /**
* Constructor for DynamicTestsAdvancedSectionDescriptor.
*
* @param typeMapper
*            the optional type mapper for the section.
*/
    public  DynamicTestsAdvancedSectionDescriptor(ITypeMapper typeMapper) {
        super(typeMapper);
    }

    @Override
    public int getEnablesFor() {
        return 1;
    }

    @Override
    public String getId() {
        //$NON-NLS-1$
        return "AdvancedSection";
    }

    @Override
    public List getInputTypes() {
        List list = new ArrayList();
        list.add(DynamicTestsElement.class.getName());
        return list;
    }

    @Override
    public ISection getSectionClass() {
        return new AdvancedPropertySection();
    }

    @Override
    public String getTargetTab() {
        //$NON-NLS-1$
        return "AdvancedTab";
    }
}
