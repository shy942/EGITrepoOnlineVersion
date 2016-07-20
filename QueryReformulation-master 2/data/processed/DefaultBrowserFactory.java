/***/
package org.eclipse.ui.internal.browser.macosx;

import org.eclipse.ui.browser.BrowserFactory;
import org.eclipse.ui.browser.IWebBrowser;

public class DefaultBrowserFactory extends BrowserFactory {

    /*
* @see BrowserFactory#createBrowser()
*/
    @Override
    public IWebBrowser createBrowser(String id, String location, String parameters) {
        return new DefaultBrowser(id);
    }
}
