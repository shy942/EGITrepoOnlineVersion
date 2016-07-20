/***/
package org.eclipse.ui.tests.views.properties.tabbed.text;

import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.TextViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertyConstants;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

/**
* A text view to test dynamic contributions to the tabbed properties view.
*
* @author Anthony Hunter
*/
public class TextTestsView extends ViewPart implements ITabbedPropertySheetPageContributor {

    //$NON-NLS-1$
    public static final String TEXT_TESTS_VIEW_ID = "org.eclipse.ui.tests.views.properties.tabbed.text.TextTestsView";

    private TabbedPropertySheetPage tabbedPropertySheetPage;

    private TextViewer viewer;

    @Override
    public void createPartControl(Composite parent) {
        Composite composite = new Composite(parent, SWT.NONE);
        FormLayout layout = new FormLayout();
        layout.marginWidth = ITabbedPropertyConstants.HSPACE;
        layout.marginHeight = ITabbedPropertyConstants.VSPACE;
        layout.spacing = ITabbedPropertyConstants.VMARGIN;
        composite.setLayout(layout);
        Label label = new Label(composite, SWT.NONE);
        label.setText("Enter text, selected words becomes tabs and sections");
        FormData data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(100, 0);
        data.top = new FormAttachment(0, 0);
        label.setLayoutData(data);
        viewer = new TextViewer(composite, SWT.WRAP | SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
        viewer.setDocument(new Document());
        data = new FormData();
        data.left = new FormAttachment(0, 0);
        data.right = new FormAttachment(100, 0);
        data.top = new FormAttachment(label, 0);
        data.bottom = new FormAttachment(100, 0);
        viewer.getControl().setLayoutData(data);
        getSite().setSelectionProvider(viewer);
    }

    @Override
    public Object getAdapter(Class adapter) {
        if (adapter == IPropertySheetPage.class) {
            if (tabbedPropertySheetPage == null) {
                tabbedPropertySheetPage = new TabbedPropertySheetPage(this);
            }
            return tabbedPropertySheetPage;
        }
        return super.getAdapter(adapter);
    }

    @Override
    public String getContributorId() {
        //$NON-NLS-1$
        return "org.eclipse.ui.tests.views.properties.tabbed.text";
    }

    public TabbedPropertySheetPage getTabbedPropertySheetPage() {
        return tabbedPropertySheetPage;
    }

    public TextViewer getViewer() {
        return viewer;
    }

    @Override
    public void setFocus() {
        viewer.getControl().setFocus();
    }
}
