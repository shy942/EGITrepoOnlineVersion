/***/
package org.eclipse.ui.part;

import org.eclipse.ui.PartInitException;

/**
* Interface for a page in a pagebook view.
* <p>
* Pages should implement this interface.
* </p>
* @see PageBookView
* @see Page
*/
public interface IPageBookViewPage extends IPage {

    /**
* Returns the site for this page. May be <code>null</code>
* if no site has been set.
*
* @return the page site or <code>null</code>
*/
    public IPageSite getSite();

    /**
* Initializes this page with the given page site.
* <p>
* This method is automatically called by the workbench shortly after page
* construction.  It marks the start of the pages's lifecycle. Clients must
* not call this method.
* </p>
*
* @param site the page site
* @exception PartInitException if this page was not initialized successfully
*/
    public void init(IPageSite site) throws PartInitException;
}
