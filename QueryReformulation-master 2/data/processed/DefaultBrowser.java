/***/
package org.eclipse.ui.internal.browser.macosx;

import java.io.*;
import java.net.URL;
import org.eclipse.ui.browser.AbstractWebBrowser;
import org.eclipse.ui.internal.browser.WebBrowserUIPlugin;

public class DefaultBrowser extends AbstractWebBrowser {

    public  DefaultBrowser(String id) {
        super(id);
    }

    /**
* @see org.eclipse.help.browser.IBrowser#displayURL(String)
*/
    @Override
    public void openURL(URL url2) {
        String url = url2.toExternalForm();
        /*
* Code from Marc-Antoine Parent
*/
        try {
            Runtime.getRuntime().exec(new String[] { //$NON-NLS-1$
            "/usr/bin/osascript", //$NON-NLS-1$
            "-e", //$NON-NLS-1$ //$NON-NLS-2$
            "open location \"" + url + "\"" });
        } catch (IOException ioe) {
            WebBrowserUIPlugin.logError("Launching \"osascript\" has failed.", ioe);
        }
    }
}
