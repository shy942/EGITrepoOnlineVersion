/***/
package org.eclipse.ui.internal.browser.browsers;

import java.io.IOException;
import java.net.URL;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.browser.AbstractWebBrowser;
import org.eclipse.ui.internal.browser.IBrowserDescriptor;
import org.eclipse.ui.internal.browser.WebBrowserUIPlugin;
import org.eclipse.ui.internal.browser.WebBrowserUtil;

/**
* Browser adapter for browsers supporting -remote openURL command line option
* i.e. Mozilla and Netscape.
*/
public class MozillaBrowser extends AbstractWebBrowser {

    // delay that it takes mozilla to start responding
    // to remote command after mozilla has been called
    protected static final int DELAY = 5000;

    protected long browserFullyOpenedAt = 0;

    private BrowserThread lastBrowserThread = null;

    protected String executable;

    protected boolean firstLaunch = true;

    private String parameters;

    /**
* Constructor
*
* @executable executable filename to launch
* @executableName name of the program to display when error occurs
*/
    public  MozillaBrowser(String id, String executable, String parameters) {
        super(id);
        this.executable = executable;
        if (parameters == null) {
            //$NON-NLS-1$
            this.parameters = "";
        } else {
            this.parameters = parameters;
        }
    }

    String getExecutable() {
        return executable;
    }

    String getParameters() {
        return parameters;
    }

    @Override
    public void openURL(URL url2) {
        String url = null;
        if (url2 != null) {
            url = url2.toExternalForm();
        } else {
            //$NON-NLS-1$
            url = "";
        }
        if (lastBrowserThread != null)
            lastBrowserThread.exitRequested = true;
        lastBrowserThread = new BrowserThread(url);
        lastBrowserThread.setDaemon(true);
        lastBrowserThread.start();
    }

    private class BrowserThread extends Thread {

        public boolean exitRequested = false;

        private String url;

        public  BrowserThread(String urlName) {
            this.url = urlName;
        }

        /**
* @param command the command
* @param parameters the parameters
* @return int 0 if success
*/
        private int openBrowser(String command, String... parameters) {
            try {
                String[] commandArray = new String[parameters.length + 1];
                commandArray[0] = command;
                System.arraycopy(parameters, 0, commandArray, 1, parameters.length);
                Process pr = Runtime.getRuntime().exec(commandArray);
                StreamConsumer outputs = new StreamConsumer(pr.getInputStream());
                (outputs).start();
                StreamConsumer errors = new StreamConsumer(pr.getErrorStream());
                (errors).start();
                pr.waitFor();
                int ret = pr.exitValue();
                if (ret == 0 && errorsInOutput(outputs, errors)) {
                    return -1;
                }
                return ret;
            } catch (InterruptedException e) {
            } catch (IOException e) {
                WebBrowserUIPlugin.logError("Launching " + executable + " has failed.", e);
                return 0;
            }
            return -1;
        }

        /**
* On some OSes 0 is always returned by netscape -remote. It is
* necessary to examine ouput to find out failure
*
* @param outputs
* @param errors
* @return @throws
*         InterruptedException
*/
        private boolean errorsInOutput(StreamConsumer outputs, StreamConsumer errors) {
            try {
                outputs.join(1000);
                if (outputs.getLastLine() != null && (outputs.getLastLine().indexOf(//$NON-NLS-1$
                "No running window found") >= 0 || outputs.getLastLine().indexOf(//$NON-NLS-1$
                "not running on display") >= 0)) {
                    return true;
                }
                errors.join(1000);
                if (errors.getLastLine() != null && (errors.getLastLine().indexOf(//$NON-NLS-1$
                "No running window found") >= 0 || errors.getLastLine().indexOf(//$NON-NLS-1$
                "not running on display") >= 0)) {
                    return true;
                }
            } catch (InterruptedException ie) {
            }
            return false;
        }

        @Override
        public void run() {
            // if browser is opening, wait until it fully opens
            waitForBrowser();
            if (exitRequested)
                return;
            if (firstLaunch && Platform.OS_WIN32.equals(Platform.getOS())) {
                if (openBrowser(executable, WebBrowserUtil.createParameterArray(parameters, url)) == 0)
                    return;
                browserFullyOpenedAt = System.currentTimeMillis() + DELAY;
                return;
            }
            if (openBrowser(executable, WebBrowserUtil.createParameterArray(parameters + " -remote openURL(" + IBrowserDescriptor.URL_PARAMETER + ")", //$NON-NLS-1$ //$NON-NLS-2$
            url)) == 0)
                return;
            if (exitRequested)
                return;
            browserFullyOpenedAt = System.currentTimeMillis() + DELAY;
            openBrowser(executable, WebBrowserUtil.createParameterArray(parameters, url));
        }

        private void waitForBrowser() {
            while (System.currentTimeMillis() < browserFullyOpenedAt) try {
                if (exitRequested)
                    return;
                Thread.sleep(100);
            } catch (InterruptedException ie) {
            }
        }
    }
}
