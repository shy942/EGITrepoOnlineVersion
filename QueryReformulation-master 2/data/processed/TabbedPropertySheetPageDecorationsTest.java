/***/
package org.eclipse.ui.tests.views.properties.tabbed;

import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreeNode;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.views.properties.tabbed.view.TabbedPropertyComposite;
import org.eclipse.ui.tests.views.properties.tabbed.decorations.TabbedPropertySheetPageWithDecorations;
import org.eclipse.ui.tests.views.properties.tabbed.decorations.views.DecorationTestsView;
import org.eclipse.ui.tests.views.properties.tabbed.views.TestsPerspective;
import org.eclipse.ui.tests.views.properties.tabbed.views.TestsViewContentProvider;
import org.eclipse.ui.views.properties.tabbed.ITabDescriptor;
import junit.framework.TestCase;

public class TabbedPropertySheetPageDecorationsTest extends TestCase {

    private DecorationTestsView decorationTestsView;

    private TreeNode[] treeNodes;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        /**
* Close the existing perspectives.
*/
        IWorkbenchWindow workbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        assertNotNull(workbenchWindow);
        IWorkbenchPage workbenchPage = workbenchWindow.getActivePage();
        assertNotNull(workbenchPage);
        closeIntroView(workbenchPage);
        processUiEvents();
        workbenchPage.closeAllPerspectives(false, false);
        processUiEvents();
        /**
* Open the tests perspective.
*/
        PlatformUI.getWorkbench().showPerspective(TestsPerspective.TESTS_PERSPECTIVE_ID, workbenchWindow);
        processUiEvents();
        /**
* Select the Decoration Tests view.
*/
        IViewPart view = workbenchPage.showView(DecorationTestsView.DECORATION_TESTS_VIEW_ID);
        processUiEvents();
        assertNotNull(view);
        assertTrue(view instanceof DecorationTestsView);
        assertTrue(workbenchPage.isPartVisible(view));
        decorationTestsView = (DecorationTestsView) view;
        /**
* get the list of tree nodes from the view.
*/
        IContentProvider contentProvider = decorationTestsView.getViewer().getContentProvider();
        assertTrue(contentProvider instanceof TestsViewContentProvider);
        TestsViewContentProvider viewContentProvider = (TestsViewContentProvider) contentProvider;
        treeNodes = viewContentProvider.getInvisibleRoot().getChildren();
        assertEquals(treeNodes.length, 8);
    }

    private void closeIntroView(IWorkbenchPage workbenchPage) {
        IViewPart intro = workbenchPage.findView("org.eclipse.ui.internal.introview");
        if (intro != null) {
            workbenchPage.hideView(intro);
        }
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        /**
* Bug 175070: Make sure the views have finished painting.
*/
        processUiEvents();
        /**
* Deselect everything in the Tests view.
*/
        setSelection(new TreeNode[] {});
    }

    private void processUiEvents() {
        while (Display.getCurrent().readAndDispatch()) {
        //
        }
    }

    /**
* Set the selection in the view to cause the properties view to change.
*
* @param selectedNodes
*            nodes to select in the view.
*/
    private void setSelection(TreeNode[] selectedNodes) {
        StructuredSelection selection = new StructuredSelection(selectedNodes);
        decorationTestsView.getViewer().setSelection(selection, true);
    }

    /**
* When Information node is selected, the Information tab is widest if decorations are not used.
*/
    public void test_widestLabelIndex1_WithoutDecorations() {
        ((TabbedPropertySheetPageWithDecorations) decorationTestsView.getTabbedPropertySheetPage()).useDecorations(false);
        /**
* select Information node
*/
        setSelection(new TreeNode[] { treeNodes[0] });
        ITabDescriptor[] tabDescriptors = decorationTestsView.getTabbedPropertySheetPage().getActiveTabs();
        /**
* First tab is Name
*/
        //$NON-NLS-1$
        assertEquals("Name", tabDescriptors[0].getLabel());
        /**
* Second tab is Information
*/
        //$NON-NLS-1$
        assertEquals("Information", tabDescriptors[1].getLabel());
        /**
* Third tab is Message
*/
        //$NON-NLS-1$
        assertEquals("Message", tabDescriptors[2].getLabel());
        /**
* No fourth tab
*/
        assertEquals(3, tabDescriptors.length);
        /**
* Information tab is widest
*/
        assertEquals(1, ((TabbedPropertyComposite) decorationTestsView.getTabbedPropertySheetPage().getControl()).getList().getWidestLabelIndex());
    }

    /**
* When Information node is selected, the Name tab is widest if decorations are used.
*/
    public void test_widestLabelIndex1_WithDecorations() {
        ((TabbedPropertySheetPageWithDecorations) decorationTestsView.getTabbedPropertySheetPage()).useDecorations(true);
        setSelection(new TreeNode[] { treeNodes[0] });
        ITabDescriptor[] tabDescriptors = decorationTestsView.getTabbedPropertySheetPage().getActiveTabs();
        //$NON-NLS-1$
        assertEquals("Name", tabDescriptors[0].getLabel());
        //$NON-NLS-1$
        assertEquals("Information", tabDescriptors[1].getLabel());
        //$NON-NLS-1$
        assertEquals("Message", tabDescriptors[2].getLabel());
        assertEquals(3, tabDescriptors.length);
        /**
* Name tab is widest
*/
        assertEquals(0, ((TabbedPropertyComposite) decorationTestsView.getTabbedPropertySheetPage().getControl()).getList().getWidestLabelIndex());
    }

    /**
* When Two Information nodes are selected, the Information tab is widest if decorations are not used.
*/
    public void test_widestLabelIndex2_WithoutDecorations() {
        ((TabbedPropertySheetPageWithDecorations) decorationTestsView.getTabbedPropertySheetPage()).useDecorations(false);
        /**
* select nodes
*/
        setSelection(new TreeNode[] { treeNodes[0], treeNodes[1] });
        ITabDescriptor[] tabDescriptors = decorationTestsView.getTabbedPropertySheetPage().getActiveTabs();
        /**
* First tab is Information
*/
        //$NON-NLS-1$
        assertEquals("Information", tabDescriptors[0].getLabel());
        /**
* Second tab is Message
*/
        //$NON-NLS-1$
        assertEquals("Message", tabDescriptors[1].getLabel());
        /**
* No other tab
*/
        assertEquals(2, tabDescriptors.length);
        assertEquals(0, ((TabbedPropertyComposite) decorationTestsView.getTabbedPropertySheetPage().getControl()).getList().getWidestLabelIndex());
    }

    /**
* When Two Information nodes are selected, the Message tab is widest if decorations are used.
*/
    public void test_widestLabelIndex2_WithDecorations() {
        ((TabbedPropertySheetPageWithDecorations) decorationTestsView.getTabbedPropertySheetPage()).useDecorations(true);
        setSelection(new TreeNode[] { treeNodes[0], treeNodes[1] });
        ITabDescriptor[] tabDescriptors = decorationTestsView.getTabbedPropertySheetPage().getActiveTabs();
        //$NON-NLS-1$
        assertEquals("Information", tabDescriptors[0].getLabel());
        //$NON-NLS-1$
        assertEquals("Message", tabDescriptors[1].getLabel());
        assertEquals(2, tabDescriptors.length);
        /**
* Message tab is widest
*/
        assertEquals(1, ((TabbedPropertyComposite) decorationTestsView.getTabbedPropertySheetPage().getControl()).getList().getWidestLabelIndex());
    }
}
