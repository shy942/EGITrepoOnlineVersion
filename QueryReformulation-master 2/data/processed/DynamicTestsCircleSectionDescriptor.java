/***/
package org.eclipse.ui.tests.views.properties.tabbed.dynamic.section.descriptors;

import org.eclipse.jface.viewers.IFilter;
import org.eclipse.ui.tests.views.properties.tabbed.dynamic.filters.DynamicTestsCircleSectionFilter;
import org.eclipse.ui.tests.views.properties.tabbed.dynamic.sections.DynamicTestsCircleSection;
import org.eclipse.ui.views.properties.tabbed.AbstractSectionDescriptor;
import org.eclipse.ui.views.properties.tabbed.ISection;
import org.eclipse.ui.views.properties.tabbed.ITypeMapper;

/**
* A section descriptor for the dynamic tests view.
*
* @author Anthony Hunter
*/
public class DynamicTestsCircleSectionDescriptor extends AbstractSectionDescriptor {

    /**
* Constructor for DynamicTestsCircleSectionDescriptor.
*
* @param typeMapper
*            the optional type mapper for the section.
*/
    public  DynamicTestsCircleSectionDescriptor(ITypeMapper typeMapper) {
        super(typeMapper);
    }

    @Override
    public IFilter getFilter() {
        return new DynamicTestsCircleSectionFilter();
    }

    @Override
    public String getId() {
        //$NON-NLS-1$
        return "DynamicTestsCircleSection";
    }

    @Override
    public ISection getSectionClass() {
        return new DynamicTestsCircleSection();
    }

    @Override
    public String getTargetTab() {
        //$NON-NLS-1$
        return "ShapeTab";
    }
}
