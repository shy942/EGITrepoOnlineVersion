/***/
package org.eclipse.ui.tests.views.properties.tabbed.views;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.TreeNode;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.tests.views.properties.tabbed.model.Element;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.tabbed.ITabbedPropertySheetPageContributor;
import org.eclipse.ui.views.properties.tabbed.TabbedPropertySheetPage;

public class TestsView extends ViewPart implements ITabbedPropertySheetPageContributor {

    private TreeViewer viewer;

    protected TabbedPropertySheetPage tabbedPropertySheetPage;

    //$NON-NLS-1$
    public static final String TESTS_VIEW_ID = "org.eclipse.ui.tests.views.properties.tabbed.views.TestsView";

    class ViewLabelProvider extends LabelProvider {

        @Override
        public String getText(Object obj) {
            Element element = (Element) ((TreeNode) obj).getValue();
            return element.getName();
        }

        @Override
        public Image getImage(Object obj) {
            Element element = (Element) ((TreeNode) obj).getValue();
            return element.getImage();
        }
    }

    /**
* The constructor.
*/
    public  TestsView() {
    //
    }

    /**
* This is a callback that will allow us to create the viewer and initialize
* it.
*/
    @Override
    public void createPartControl(Composite parent) {
        viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
        viewer.setContentProvider(new TestsViewContentProvider(this));
        viewer.setLabelProvider(new ViewLabelProvider());
        viewer.setInput(getViewSite());
        getSite().setSelectionProvider(viewer);
    }

    /**
* Passing the focus request to the viewer's control.
*/
    @Override
    public void setFocus() {
        viewer.getControl().setFocus();
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
        return TESTS_VIEW_ID;
    }

    public TreeViewer getViewer() {
        return viewer;
    }

    public TabbedPropertySheetPage getTabbedPropertySheetPage() {
        return tabbedPropertySheetPage;
    }
}
