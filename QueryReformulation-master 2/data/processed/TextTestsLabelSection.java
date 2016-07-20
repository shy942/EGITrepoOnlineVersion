/***/
package org.eclipse.ui.tests.views.properties.tabbed.text;

import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
* A section for the text test view.
*
* @author Anthony Hunter
*/
public class TextTestsLabelSection extends AbstractPropertySection {

    protected String label;

    public  TextTestsLabelSection(String word) {
        super();
        //$NON-NLS-1$//$NON-NLS-2$
        label = "A simple section for a selected word \"" + word + "\".";
    }

    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage aTabbedPropertySheetPage) {
        super.createControls(parent, aTabbedPropertySheetPage);
        Composite composite = getWidgetFactory().createFlatFormComposite(parent);
        CLabel nameLabel = getWidgetFactory().createCLabel(composite, label);
        FormData data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(100, 0);
        data.top = new FormAttachment(0, 0);
        nameLabel.setLayoutData(data);
    }
}
