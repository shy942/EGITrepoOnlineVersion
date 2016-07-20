/***/
package org.eclipse.ui.tests.views.properties.tabbed.dynamic.section.descriptors;

import org.eclipse.jface.viewers.IFilter;
import org.eclipse.ui.tests.views.properties.tabbed.dynamic.filters.DynamicTestsGreenSectionFilter;
import org.eclipse.ui.tests.views.properties.tabbed.dynamic.sections.DynamicTestsGreenSection;
import org.eclipse.ui.views.properties.tabbed.AbstractSectionDescriptor;
import org.eclipse.ui.views.properties.tabbed.ISection;
import org.eclipse.ui.views.properties.tabbed.ITypeMapper;

/**
* A section descriptor for the dynamic tests view.
*
* @author Anthony Hunter
*/
public class DynamicTestsGreenSectionDescriptor extends AbstractSectionDescriptor {

    /**
* Constructor for DynamicTestsGreenSectionDescriptor.
*
* @param typeMapper
*            the optional type mapper for the section.
*/
    public  DynamicTestsGreenSectionDescriptor(ITypeMapper typeMapper) {
        super(typeMapper);
    }

    @Override
    public IFilter getFilter() {
        return new DynamicTestsGreenSectionFilter();
    }

    @Override
    public String getId() {
        //$NON-NLS-1$
        return "DynamicTestsGreenSection";
    }

    @Override
    public ISection getSectionClass() {
        return new DynamicTestsGreenSection();
    }

    @Override
    public String getTargetTab() {
        //$NON-NLS-1$
        return "ColorTab";
    }
}
