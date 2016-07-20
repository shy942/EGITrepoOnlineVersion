/***/
package org.eclipse.ui.tests.views.properties.tabbed.dynamic.section.descriptors;

import org.eclipse.jface.viewers.IFilter;
import org.eclipse.ui.tests.views.properties.tabbed.dynamic.filters.DynamicTestsBlueSectionFilter;
import org.eclipse.ui.tests.views.properties.tabbed.dynamic.sections.DynamicTestsBlueSection;
import org.eclipse.ui.views.properties.tabbed.AbstractSectionDescriptor;
import org.eclipse.ui.views.properties.tabbed.ISection;
import org.eclipse.ui.views.properties.tabbed.ITypeMapper;

/**
* A section descriptor for the dynamic tests view.
*
* @author Anthony Hunter
*/
public class DynamicTestsBlueSectionDescriptor extends AbstractSectionDescriptor {

    /**
* Constructor for DynamicTestsBlueSectionDescriptor.
*
* @param typeMapper
*            the optional type mapper for the section.
*/
    public  DynamicTestsBlueSectionDescriptor(ITypeMapper typeMapper) {
        super(typeMapper);
    }

    @Override
    public IFilter getFilter() {
        return new DynamicTestsBlueSectionFilter();
    }

    @Override
    public String getId() {
        //$NON-NLS-1$
        return "DynamicTestsBlueSection";
    }

    @Override
    public ISection getSectionClass() {
        return new DynamicTestsBlueSection();
    }

    @Override
    public String getTargetTab() {
        //$NON-NLS-1$
        return "ColorTab";
    }
}
