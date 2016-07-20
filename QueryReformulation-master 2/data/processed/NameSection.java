/***/
package org.eclipse.ui.tests.views.properties.tabbed.sections;

import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeNode;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.tests.views.properties.tabbed.model.Element;
import org.eclipse.ui.views.properties.tabbed.AbstractPropertySection;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
* The name section on the element tab.
*
* @author Anthony Hunter
*/
public class NameSection extends AbstractPropertySection {

    private TreeNode treeNode;

    private Text nameText;

    /**
* @see org.eclipse.ui.views.properties.tabbed.ITabbedPropertySection#createControls(org.eclipse.swt.widgets.Composite,
*      org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage)
*/
    @Override
    public void createControls(Composite parent, TabbedPropertySheetPage tabbedPropertySheetPage) {
        super.createControls(parent, tabbedPropertySheetPage);
        Composite composite = getWidgetFactory().createFlatFormComposite(parent);
        FormData data;
        //$NON-NLS-1$
        nameText = getWidgetFactory().createText(composite, "");
        nameText.setEditable(false);
        data = new FormData();
        data.left = new FormAttachment(0, STANDARD_LABEL_WIDTH);
        data.right = new FormAttachment(100, 0);
        data.top = new FormAttachment(0, 0);
        nameText.setLayoutData(data);
        //$NON-NLS-1$
        CLabel nameLabel = getWidgetFactory().createCLabel(composite, "Name:");
        data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(nameText, -ITabbedPropertyConstants.HSPACE);
        data.top = new FormAttachment(nameText, 0, SWT.CENTER);
        nameLabel.setLayoutData(data);
    }

    /**
* Get the element.
*
* @return the element.
*/
    public TreeNode getTreeNode() {
        return treeNode;
    }

    /*
* @see org.eclipse.ui.views.properties.tabbed.view.ITabbedPropertySection#refresh()
*/
    @Override
    public void refresh() {
        Element element = (Element) getTreeNode().getValue();
        nameText.setText(element.getName());
    }

    @Override
    public void setInput(IWorkbenchPart part, ISelection selection) {
        super.setInput(part, selection);
        Assert.isTrue(selection instanceof IStructuredSelection);
        Object input = ((IStructuredSelection) selection).getFirstElement();
        Assert.isTrue(input instanceof TreeNode);
        this.treeNode = (TreeNode) input;
    }
}
