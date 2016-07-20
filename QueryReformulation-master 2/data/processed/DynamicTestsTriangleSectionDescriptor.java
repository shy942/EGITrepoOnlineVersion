/***/
package org.eclipse.ui.tests.views.properties.tabbed.dynamic.section.descriptors;

import org.eclipse.jface.viewers.IFilter;
import org.eclipse.ui.tests.views.properties.tabbed.dynamic.filters.DynamicTestsTriangleSectionFilter;
import org.eclipse.ui.tests.views.properties.tabbed.dynamic.sections.DynamicTestsTriangleSection;
import org.eclipse.ui.views.properties.tabbed.AbstractSectionDescriptor;
import org.eclipse.ui.views.properties.tabbed.ISection;
import org.eclipse.ui.views.properties.tabbed.ITypeMapper;

/**
* A section descriptor for the dynamic tests view.
*
* @author Anthony Hunter
*/
public class DynamicTestsTriangleSectionDescriptor extends AbstractSectionDescriptor {

    /**
* Constructor for DynamicTestsTriangleSectionDescriptor.
*
* @param typeMapper
*            the optional type mapper for the section.
*/
    public  DynamicTestsTriangleSectionDescriptor(ITypeMapper typeMapper) {
        super(typeMapper);
    }

    @Override
    public IFilter getFilter() {
        return new DynamicTestsTriangleSectionFilter();
    }

    @Override
    public String getId() {
        //$NON-NLS-1$
        return "DynamicTestsTriangleSection";
    }

    @Override
    public ISection getSectionClass() {
        return new DynamicTestsTriangleSection();
    }

    @Override
    public String getTargetTab() {
        //$NON-NLS-1$
        return "ShapeTab";
    }
}
