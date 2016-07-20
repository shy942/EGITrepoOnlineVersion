/***/
package org.eclipse.ui.tests.dynamicplugins;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.registry.IWorkbenchRegistryConstants;
import org.eclipse.ui.internal.registry.ViewRegistry;
import org.eclipse.ui.tests.leaks.LeakTests;
import org.eclipse.ui.views.IStickyViewDescriptor;
import org.eclipse.ui.views.IViewCategory;
import org.eclipse.ui.views.IViewDescriptor;
import org.eclipse.ui.views.IViewRegistry;

/**
* Tests to ensure the addition of new views with dynamic plug-ins.
*/
public class ViewTests extends DynamicTestCase {

    private static final String VIEW_ID1 = "org.eclipse.newView1.newView1";

    private static final String VIEW_ID2 = "org.eclipse.newView1.newView2";

    private static final String CATEGORY_ID = "org.eclipse.newView1.newCategory1";

    public  ViewTests(String testName) {
        super(testName);
    }

    public void testViewClosure() throws CoreException {
        IWorkbenchWindow window = openTestWindow(IDE.RESOURCE_PERSPECTIVE_ID);
        getBundle();
        ReferenceQueue queue = new ReferenceQueue();
        IViewPart part = window.getActivePage().showView(VIEW_ID1);
        // we need to ensure that the view is closed in all open perspectives but this is not currently possible.
        // window.getActivePage().setPerspective(WorkbenchPlugin.getDefault().getPerspectiveRegistry().findPerspectiveWithId(EmptyPerspective.PERSP_ID2));
        WeakReference ref = new WeakReference(part, queue);
        assertNotNull(part);
        //null the reference
        part = null;
        removeBundle();
        try {
            LeakTests.checkRef(queue, ref);
        } catch (Exception e) {
            fail(e.getMessage());
        }
        assertNull(window.getActivePage().findView(VIEW_ID1));
    }

    public void testViewWithoutCategory() {
        IViewRegistry registry = WorkbenchPlugin.getDefault().getViewRegistry();
        assertNull(registry.find(VIEW_ID2));
        getBundle();
        IViewDescriptor desc = registry.find(VIEW_ID2);
        assertNotNull(desc);
        testViewProperties(desc);
        removeBundle();
        assertNull(registry.find(VIEW_ID2));
        try {
            testViewProperties(desc);
            fail();
        } catch (RuntimeException e) {
        }
    }

    public void testViewWithCategory() {
        IViewRegistry registry = WorkbenchPlugin.getDefault().getViewRegistry();
        assertNull(registry.find(VIEW_ID1));
        getBundle();
        IViewDescriptor desc = registry.find(VIEW_ID1);
        assertNotNull(desc);
        testViewProperties(desc);
        removeBundle();
        assertNull(registry.find(VIEW_ID1));
        try {
            testViewProperties(desc);
            fail();
        } catch (RuntimeException e) {
        }
    }

    public void testStickyViewProperties() {
        ViewRegistry registry = (ViewRegistry) WorkbenchPlugin.getDefault().getViewRegistry();
        IStickyViewDescriptor[] descs = registry.getStickyViews();
        for (IStickyViewDescriptor desc : descs) {
            assertFalse(VIEW_ID1.equals(desc.getId()));
        }
        getBundle();
        descs = registry.getStickyViews();
        IStickyViewDescriptor desc = null;
        for (IStickyViewDescriptor desc2 : descs) {
            if (VIEW_ID1.equals(desc2.getId())) {
                desc = desc2;
                break;
            }
        }
        assertNotNull(desc);
        testStickyViewProperties(desc);
        removeBundle();
        descs = registry.getStickyViews();
        for (IStickyViewDescriptor desc2 : descs) {
            assertFalse(VIEW_ID1.equals(desc2.getId()));
        }
        try {
            testStickyViewProperties(desc);
            fail();
        } catch (RuntimeException e) {
        }
    }

    private void testStickyViewProperties(IStickyViewDescriptor desc) {
        assertNotNull(desc.getId());
        assertFalse(desc.isMoveable());
        assertFalse(desc.isCloseable());
        assertEquals(IPageLayout.BOTTOM, desc.getLocation());
    }

    public void testCategoryViewContainmentProperties() {
        ViewRegistry registry = (ViewRegistry) WorkbenchPlugin.getDefault().getViewRegistry();
        assertNull(registry.find(VIEW_ID1));
        assertNull(registry.findCategory(CATEGORY_ID));
        getBundle();
        IViewDescriptor desc = registry.find(VIEW_ID1);
        assertNotNull(desc);
        IViewCategory category = registry.findCategory(CATEGORY_ID);
        assertNotNull(category);
        testCategoryProperties(category);
        assertTrue(category.getViews()[0] == desc);
        removeBundle();
        assertNull(registry.find(VIEW_ID1));
        assertNull(registry.findCategory(CATEGORY_ID));
        try {
            testCategoryProperties(category);
            fail();
        } catch (RuntimeException e) {
        }
    }

    /**
* @param category
*/
    private void testCategoryProperties(IViewCategory desc) {
        assertNotNull(desc.getId());
        assertNotNull(desc.getLabel());
        assertEquals(1, desc.getViews().length);
    }

    /**
* @param desc
*/
    private void testViewProperties(IViewDescriptor desc) {
        assertNotNull(desc.getId());
        assertNotNull(desc.getLabel());
        assertNotNull(desc.getImageDescriptor());
        assertNotNull(desc.getDescription());
    }

    @Override
    protected String getExtensionId() {
        return "newView1.testDynamicViewAddition";
    }

    @Override
    protected String getExtensionPoint() {
        return IWorkbenchRegistryConstants.PL_VIEWS;
    }

    @Override
    protected String getInstallLocation() {
        return "data/org.eclipse.newView1";
    }

    @Override
    protected String getMarkerClass() {
        return "org.eclipse.ui.dynamic.DynamicView";
    }
}
