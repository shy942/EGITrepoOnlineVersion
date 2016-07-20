/***/
package org.eclipse.ui.internal.browser;

import java.net.URL;
import org.eclipse.osgi.util.NLS;
import org.eclipse.swt.program.Program;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.browser.AbstractWebBrowser;

/**
* An instance of a running system Web browser.
*/
public class SystemBrowserInstance extends AbstractWebBrowser {

    public  SystemBrowserInstance(String id) {
        super(id);
    }

    @Override
    public void openURL(URL url) throws PartInitException {
        String urlText = url.toExternalForm();
        //$NON-NLS-1$
        Trace.trace(Trace.FINEST, "Launching system Web browser: " + urlText);
        //$NON-NLS-1$
        Program program = Program.findProgram("html");
        if (program != null) {
            if (program.execute(urlText))
                return;
        }
        if (!Program.launch(urlText))
            throw new PartInitException(NLS.bind(Messages.errorCouldNotLaunchExternalWebBrowser, urlText));
    }
}
