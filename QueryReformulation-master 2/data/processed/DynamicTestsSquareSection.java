/***/
package org.eclipse.ui.tests.views.properties.tabbed.dynamic.sections;

/**
* A section for the dynamic tests view.
*
* @author Anthony Hunter
*/
public class DynamicTestsSquareSection extends DynamicTestsAbstractLabelSection {

    @Override
    public String getGroup() {
        //$NON-NLS-1$
        return "Shape";
    }

    @Override
    public String getLabel() {
        //$NON-NLS-1$
        return "A section for square elements.";
    }
}
