/***/
package org.eclipse.ui.tests.views.properties.tabbed.override.items;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetWidgetFactory;

/**
* An item for the emply selection when there is no selected element in the
* override tests view.
*
* @author Anthony Hunter
* @since 3.4
*/
public class EmptyItem implements IOverrideTestsItem {

    private Composite composite;

    @Override
    public void createControls(Composite parent) {
        TabbedPropertySheetWidgetFactory factory = new TabbedPropertySheetWidgetFactory();
        composite = factory.createFlatFormComposite(parent);
        Label label = factory.createLabel(composite, //$NON-NLS-1$
        "Empty Item (no selected element)");
        label.setLayoutData(new FormData());
    }

    @Override
    public void dispose() {
        if (composite != null && !composite.isDisposed()) {
            composite.dispose();
            composite = null;
        }
    }

    @Override
    public Composite getComposite() {
        return composite;
    }

    @Override
    public Class getElement() {
        return null;
    }

    @Override
    public Image getImage() {
        return null;
    }

    @Override
    public String getText() {
        //$NON-NLS-1$
        return "Empty Item";
    }
}
