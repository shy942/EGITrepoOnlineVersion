/***/
package org.eclipse.ui.tests.api;

import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;

public class IViewSiteTest extends IWorkbenchPartSiteTest {

    /**
* Constructor for IViewPartSiteTest
*/
    public  IViewSiteTest(String testName) {
        super(testName);
    }

    /**
* @see IWorkbenchPartSiteTest#getTestPartName()
*/
    @Override
    protected String getTestPartName() throws Throwable {
        return MockViewPart.NAME;
    }

    /**
* @see IWorkbenchPartSiteTest#getTestPartId()
*/
    @Override
    protected String getTestPartId() throws Throwable {
        return MockViewPart.ID;
    }

    /**
* @see IWorkbenchPartSiteTest#createTestPart(IWorkbenchPage)
*/
    @Override
    protected IWorkbenchPart createTestPart(IWorkbenchPage page) throws Throwable {
        return page.showView(MockViewPart.ID);
    }

    public void testGetActionBars() throws Throwable {
        // From Javadoc: "Returns the action bars for this part site."
        IViewPart view = (IViewPart) createTestPart(fPage);
        IViewSite site = view.getViewSite();
        assertNotNull(site.getActionBars());
    }
}
