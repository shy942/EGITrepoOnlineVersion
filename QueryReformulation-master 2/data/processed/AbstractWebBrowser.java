/***/
package org.eclipse.ui.browser;

/**
* Clients that supply implementation of the workbench browser support should
* extend this class for web browser instances they manage. Clients should not
* implement the <code>IWebBrowser</code> interface.
*
* @since 3.1
*/
public abstract class AbstractWebBrowser implements IWebBrowser {

    private String id;

    /**
* The constructor that accepts the unique browser identifier.
*
* @param id
*            the unique browser identifier
*/
    public  AbstractWebBrowser(String id) {
        this.id = id;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public boolean close() {
        return false;
    }
}
