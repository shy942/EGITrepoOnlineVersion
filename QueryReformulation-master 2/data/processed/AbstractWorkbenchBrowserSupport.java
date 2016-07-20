/***/
package org.eclipse.ui.browser;

import org.eclipse.ui.PartInitException;

/**
* Implements <code>IWorkbenchBrowserSupport</code> while leaving some methods
* to the implementors. Classes that extend this abstract class are meant to be
* contributed via 'org.eclipse.ui.browserSupport' extension point.
*
* @since 3.1
*/
public abstract class AbstractWorkbenchBrowserSupport implements IWorkbenchBrowserSupport {

    //$NON-NLS-1$
    private static final String SHARED_EXTERNAL_BROWSER_ID = "org.eclipse.ui.externalBrowser";

    /**
* The default constructor.
*/
    public  AbstractWorkbenchBrowserSupport() {
    }

    @Override
    public IWebBrowser getExternalBrowser() throws PartInitException {
        return createBrowser(AS_EXTERNAL, SHARED_EXTERNAL_BROWSER_ID, null, null);
    }

    @Override
    public boolean isInternalWebBrowserAvailable() {
        return false;
    }
}
