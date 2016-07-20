/***/
package org.eclipse.ui.tests.api;

import org.eclipse.ui.IPageListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.tests.harness.util.UITestCase;

/**
* Tests the IPageListener class.
*/
public class IPageListenerTest extends UITestCase implements IPageListener {

    private IWorkbenchWindow fWindow;

    private int eventsReceived = 0;

    private final int OPEN = 0x01;

    private final int CLOSE = 0x02;

    private final int ACTIVATE = 0x04;

    private IWorkbenchPage pageMask;

    public  IPageListenerTest(String testName) {
        super(testName);
    }

    @Override
    protected void doSetUp() throws Exception {
        super.doSetUp();
        fWindow = openTestWindow();
        fWindow.addPageListener(this);
    }

    @Override
    protected void doTearDown() throws Exception {
        fWindow.removePageListener(this);
        super.doTearDown();
    }

    /**
* Tests the pageOpened method.
*/
    public void testPageOpened() throws Throwable {
    /*
* Commented out because until test case can be updated to work
* with new window/page/perspective implementation
*
// From Javadoc: "Notifies this listener that the given page has been opened."
// Test open page.
eventsReceived = 0;
IWorkbenchPage page = fWindow.openPage(EmptyPerspective.PERSP_ID,
fWorkspace);
assertEquals(eventsReceived, OPEN|ACTIVATE);
// Close page.
page.close();
*/
    }

    /**
* Tests the pageClosed method.
*/
    public void testPageClosed() throws Throwable {
    /*
* Commented out because until test case can be updated to work
* with new window/page/perspective implementation
*
// From Javadoc: "Notifies this listener that the given page has been closed."
// Open page.
IWorkbenchPage page = fWindow.openPage(EmptyPerspective.PERSP_ID,
fWorkspace);
// Test close page.
eventsReceived = 0;
pageMask = page;
page.close();
assertEquals(eventsReceived, CLOSE);
*/
    }

    /**
* Tests the pageActivated method.
*/
    public void testPageActivate() throws Throwable {
    /*
* Commented out because until test case can be updated to work
* with new window/page/perspective implementation
*
// From Javadoc: "Notifies this listener that the given page has been activated."
// Add pages.
IWorkbenchPage page1 = fWindow.openPage(EmptyPerspective.PERSP_ID,
fWorkspace);
IWorkbenchPage page2 = fWindow.openPage(EmptyPerspective.PERSP_ID,
fWorkspace);
// Test activation of page 1.
eventsReceived = 0;
pageMask = page1;
fWindow.setActivePage(page1);
assertEquals(eventsReceived, ACTIVATE);
// Test activation of page 2.
eventsReceived = 0;
pageMask = page2;
fWindow.setActivePage(page2);
assertEquals(eventsReceived, ACTIVATE);
// Cleanup.
page1.close();
page2.close();
*/
    }

    /**
* @see IPageListener#pageActivated(IWorkbenchPage)
*/
    @Override
    public void pageActivated(IWorkbenchPage page) {
        if (pageMask == null || page == pageMask) {
            eventsReceived = eventsReceived | ACTIVATE;
        }
    }

    /**
* @see IPageListener#pageClosed(IWorkbenchPage)
*/
    @Override
    public void pageClosed(IWorkbenchPage page) {
        if (pageMask == null || page == pageMask) {
            eventsReceived = eventsReceived | CLOSE;
        }
    }

    /**
* @see IPageListener#pageOpened(IWorkbenchPage)
*/
    @Override
    public void pageOpened(IWorkbenchPage page) {
        if (pageMask == null || page == pageMask) {
            eventsReceived |= OPEN;
        }
    }
}
