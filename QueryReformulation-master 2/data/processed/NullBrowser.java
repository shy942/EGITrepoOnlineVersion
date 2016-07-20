/***/
package org.eclipse.ui.internal.browser.browsers;

import java.net.URL;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.browser.AbstractWebBrowser;
import org.eclipse.ui.internal.browser.Messages;
import org.eclipse.ui.internal.browser.WebBrowserUIPlugin;

public class NullBrowser extends AbstractWebBrowser {

    public  NullBrowser(String id) {
        super(id);
    }

    @Override
    public void openURL(URL url) throws PartInitException {
        WebBrowserUIPlugin.logError(//$NON-NLS-1$
        "There is no browser adapter configured to display " + url + //$NON-NLS-1$
        ".  Ensure that you have a required browser and adapter installed, and that the browser program is available on the system path.", null);
        throw new PartInitException(Messages.errorNoBrowser);
    }
}
