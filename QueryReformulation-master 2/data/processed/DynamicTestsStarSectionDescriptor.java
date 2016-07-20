/***/
package org.eclipse.ui.tests.views.properties.tabbed.dynamic.section.descriptors;

import org.eclipse.jface.viewers.IFilter;
import org.eclipse.ui.tests.views.properties.tabbed.dynamic.filters.DynamicTestsStarSectionFilter;
import org.eclipse.ui.tests.views.properties.tabbed.dynamic.sections.DynamicTestsStarSection;
import org.eclipse.ui.views.properties.tabbed.AbstractSectionDescriptor;
import org.eclipse.ui.views.properties.tabbed.ISection;
import org.eclipse.ui.views.properties.tabbed.ITypeMapper;

/**
* A section descriptor for the dynamic tests view.
*
* @author Anthony Hunter
*/
public class DynamicTestsStarSectionDescriptor extends AbstractSectionDescriptor {

    /**
* Constructor for DynamicTestsStarSectionDescriptor.
*
* @param typeMapper
*            the optional type mapper for the section.
*/
    public  DynamicTestsStarSectionDescriptor(ITypeMapper typeMapper) {
        super(typeMapper);
    }

    @Override
    public IFilter getFilter() {
        return new DynamicTestsStarSectionFilter();
    }

    @Override
    public String getId() {
        //$NON-NLS-1$
        return "DynamicTestsStarSection";
    }

    @Override
    public ISection getSectionClass() {
        return new DynamicTestsStarSection();
    }

    @Override
    public String getTargetTab() {
        //$NON-NLS-1$
        return "StarTab";
    }
}
