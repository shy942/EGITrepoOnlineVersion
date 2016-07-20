/***/
package org.eclipse.ui.tests.views.properties.tabbed.override.items;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.ScrolledForm;
import org.eclipse.ui.forms.widgets.Section;
import org.eclipse.ui.forms.widgets.TableWrapData;
import org.eclipse.ui.forms.widgets.TableWrapLayout;
import org.eclipse.ui.tests.views.properties.tabbed.model.Information;

/**
* An item for when the Information element is the selected element in the
* override tests view.
*
* @author Anthony Hunter
* @since 3.4
*/
public class InformationItem implements IOverrideTestsItem {

    private Composite composite;

    @Override
    public void createControls(Composite parent) {
        FormToolkit toolkit = new FormToolkit(parent.getDisplay());
        composite = toolkit.createComposite(parent);
        composite.setLayout(new FillLayout());
        ScrolledForm form = toolkit.createScrolledForm(composite);
        form.getBody().setLayout(new TableWrapLayout());
        Section section = toolkit.createSection(form.getBody(), Section.DESCRIPTION);
        TableWrapData td = new TableWrapData(TableWrapData.FILL, TableWrapData.TOP);
        td.grabHorizontal = true;
        section.setLayoutData(td);
        //$NON-NLS-1$
        section.setText(getText() + " Properties");
        toolkit.createCompositeSeparator(section);
        section.setDescription(//$NON-NLS-1$
        "Set the properties of the selected " + getText() + //$NON-NLS-1$
        " element.");
        Composite sectionClient = toolkit.createComposite(section);
        FormLayout layout = new FormLayout();
        layout.marginWidth = 5;
        layout.marginHeight = 5;
        layout.spacing = 2;
        sectionClient.setLayout(layout);
        section.setClient(sectionClient);
        toolkit.paintBordersFor(sectionClient);
        Button radioLeft = //$NON-NLS-1$
        toolkit.createButton(//$NON-NLS-1$
        sectionClient, //$NON-NLS-1$
        "Choice 1", SWT.RADIO);
        FormData data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.top = new FormAttachment(0, 5);
        radioLeft.setLayoutData(data);
        Button radioRight = //$NON-NLS-1$
        toolkit.createButton(//$NON-NLS-1$
        sectionClient, //$NON-NLS-1$
        "Choice 2", SWT.RADIO);
        data = new FormData();
        data.left = new FormAttachment(radioLeft, 5);
        data.top = new FormAttachment(0, 5);
        radioRight.setLayoutData(data);
        Button radioRight2 = //$NON-NLS-1$
        toolkit.createButton(//$NON-NLS-1$
        sectionClient, //$NON-NLS-1$
        "Choice 3", SWT.RADIO);
        data = new FormData();
        data.left = new FormAttachment(radioRight, 5);
        data.right = new FormAttachment(100, 0);
        data.top = new FormAttachment(0, 5);
        radioRight2.setLayoutData(data);
        Button flag = toolkit.createButton(sectionClient, "Value of the flag property", //$NON-NLS-1$
        SWT.CHECK);
        data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(100, 0);
        data.top = new FormAttachment(radioLeft, 5);
        flag.setLayoutData(data);
        //$NON-NLS-1$
        Label nameLabel = toolkit.createLabel(sectionClient, "Text Property:");
        data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.top = new FormAttachment(flag, 5);
        nameLabel.setLayoutData(data);
        //$NON-NLS-1$
        Text nameText = toolkit.createText(sectionClient, "");
        data = new FormData();
        data.left = new FormAttachment(nameLabel, 5);
        data.right = new FormAttachment(100, 0);
        data.top = new FormAttachment(flag, 5);
        nameText.setLayoutData(data);
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
        return Information.class;
    }

    @Override
    public Image getImage() {
        return PlatformUI.getWorkbench().getSharedImages().getImage(ISharedImages.IMG_OBJS_INFO_TSK);
    }

    @Override
    public String getText() {
        //$NON-NLS-1$
        return "Information";
    }
}
