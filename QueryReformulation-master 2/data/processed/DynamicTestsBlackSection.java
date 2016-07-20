/***/
package org.eclipse.ui.tests.views.properties.tabbed.dynamic.sections;

/**
* A section for the dynamic tests view.
*
* @author Anthony Hunter
*/
public class DynamicTestsBlackSection extends DynamicTestsAbstractLabelSection {

    @Override
    public String getGroup() {
        //$NON-NLS-1$
        return "Black";
    }

    @Override
    public String getLabel() {
        //$NON-NLS-1$
        return "A section for black elements.";
    }
}
