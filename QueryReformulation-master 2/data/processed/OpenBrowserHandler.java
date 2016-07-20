/***/
package org.eclipse.ui.internal.browser;

import java.net.MalformedURLException;
import java.net.URL;
import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.browser.IWebBrowser;
import org.eclipse.ui.browser.IWorkbenchBrowserSupport;
import org.eclipse.ui.handlers.HandlerUtil;

public class OpenBrowserHandler extends AbstractHandler {

    //$NON-NLS-1$
    private static final String PARAM_ID_URL = "url";

    //$NON-NLS-1$
    private static final String PARAM_ID_BROWSER_ID = "browserId";

    //$NON-NLS-1$
    private static final String PARAM_ID_NAME = "name";

    //$NON-NLS-1$
    private static final String PARAM_ID_TOOLTIP = "tooltip";

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        String urlText = event.getParameter(PARAM_ID_URL);
        final URL url;
        if (urlText == null) {
            url = null;
        } else {
            try {
                url = new URL(urlText);
            } catch (MalformedURLException ex) {
                throw new ExecutionException("malformed URL:" + urlText, ex);
            }
        }
        final String browserId = event.getParameter(PARAM_ID_BROWSER_ID);
        final String name = event.getParameter(PARAM_ID_NAME);
        final String tooltip = event.getParameter(PARAM_ID_TOOLTIP);
        // Can be simplified once Bug 400932 is addressed
        HandlerUtil.getActiveShellChecked(event).getDisplay().asyncExec(new Runnable() {

            @Override
            public void run() {
                try {
                    IWorkbenchBrowserSupport browserSupport = PlatformUI.getWorkbench().getBrowserSupport();
                    IWebBrowser browser = browserSupport.createBrowser(IWorkbenchBrowserSupport.LOCATION_BAR | IWorkbenchBrowserSupport.NAVIGATION_BAR, browserId, name, tooltip);
                    // Must open browser on UI thread
                    browser.openURL(url);
                } catch (PartInitException ex) {
                    WebBrowserUtil.openError(NLS.bind(Messages.errorCouldNotLaunchWebBrowser, url));
                }
            }
        });
        return null;
    }
}
