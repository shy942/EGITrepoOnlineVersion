/***/
package org.eclipse.ui.tests.api.workbenchpart;

import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.tests.harness.util.UITestCase;

/**
* @since 3.4
*
*/
public class LifecycleViewTest extends UITestCase {

    /**
* @param testName
*/
    public  LifecycleViewTest(String testName) {
        super(testName);
    }

    public void testLifecycle() throws Exception {
        IWorkbenchWindow window = openTestWindow();
        IWorkbenchPage page = window.getActivePage();
        IViewPart v = page.showView(LifecycleView.ID);
        assertTrue(v instanceof LifecycleView);
        LifecycleView view = (LifecycleView) v;
        processEvents();
        page.hideView(v);
        processEvents();
        assertTrue(view.callPartDispose);
        assertTrue(view.callWidgetDispose);
        assertFalse(view.callSiteDispose);
    }
}
