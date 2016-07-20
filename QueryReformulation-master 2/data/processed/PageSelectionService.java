/***/
package org.eclipse.ui.internal;

import org.eclipse.ui.IWorkbenchPage;

/* package */
class PageSelectionService extends AbstractSelectionService {

    private IWorkbenchPage page;

    /**
* Creates a new selection service for a specific workbench page.
*/
    public  PageSelectionService(IWorkbenchPage page) {
        setPage(page);
    }

    /**
* Sets the page.
*/
    private void setPage(IWorkbenchPage page) {
        this.page = page;
    }

    /**
* Returns the page.
*/
    protected IWorkbenchPage getPage() {
        return page;
    }

    /*
* @see AbstractSelectionService#createPartTracker(String)
*/
    @Override
    protected AbstractPartSelectionTracker createPartTracker(String partId) {
        return new PagePartSelectionTracker(getPage(), partId);
    }
}
