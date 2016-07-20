/***/
package org.eclipse.ui.internal.browser;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import org.eclipse.jface.util.Util;
import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.browser.AbstractWebBrowser;
import org.eclipse.ui.internal.browser.browsers.StreamConsumer;

/**
* An instance of a running Web browser. rundll32.exe
* url.dll,FileProtocolHandler www.ibm.com
*/
public class ExternalBrowserInstance extends AbstractWebBrowser {

    protected IBrowserDescriptor browser;

    protected Process process;

    public  ExternalBrowserInstance(String id, IBrowserDescriptor browser) {
        super(id);
        this.browser = browser;
    }

    @Override
    public void openURL(URL url) throws PartInitException {
        final String urlText = url == null ? null : url.toExternalForm();
        ArrayList<String> cmdOptions = new ArrayList();
        String location = browser.getLocation();
        cmdOptions.add(location);
        String parameters = browser.getParameters();
        /**
* If true, then report non-zero exit values. Primarily useful when
* using a launcher, like OS X's open(1), as some browsers (like IE)
* routinely return non-zero values (bug 475775).
*/
        final boolean reportNonZeroExitValue[] = new boolean[] { false };
        Trace.trace(Trace.FINEST, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        "Launching external Web browser: " + location + " - " + parameters + " - " + urlText);
        // For MacOS X .app, we use open(1) to launch the app for the given URL
        // The order of the arguments is specific:
        //
        // open -a APP URL --args PARAMETERS
        //
        // As #createParameterArray() will append the URL to the end if %URL%
        // isn't found, we only include urlText if the parameters makes
        // reference to %URL%. This could mean that %URL% is specified
        // twice on the command line (e.g., "open -a XXX URL --args XXX URL
        // %%%") but presumably the user means to do that.
        boolean isMacBundle = Util.isMac() && isMacAppBundle(location);
        boolean includeUrlInParams = !isMacBundle || (parameters != null && parameters.contains(IBrowserDescriptor.URL_PARAMETER));
        String[] params = WebBrowserUtil.createParameterArray(parameters, includeUrlInParams ? urlText : null);
        try {
            if (isMacBundle) {
                //$NON-NLS-1$
                cmdOptions.add(0, "-a");
                //$NON-NLS-1$
                cmdOptions.add(0, "open");
                if (urlText != null) {
                    cmdOptions.add(urlText);
                }
                // --args supported in 10.6 and later
                if (params.length > 0) {
                    //$NON-NLS-1$
                    cmdOptions.add("--args");
                }
                reportNonZeroExitValue[0] = true;
            }
            for (String param : params) {
                cmdOptions.add(param);
            }
            String[] cmd = cmdOptions.toArray(new String[cmdOptions.size()]);
            //$NON-NLS-1$//$NON-NLS-2$
            Trace.trace(Trace.FINEST, "Launching " + join(" ", cmd));
            process = Runtime.getRuntime().exec(cmd);
            Thread outConsumer = new StreamConsumer(process.getInputStream());
            //$NON-NLS-1$
            outConsumer.setName("External browser output reader");
            outConsumer.start();
            Thread errConsumer = new StreamConsumer(process.getErrorStream());
            //$NON-NLS-1$
            errConsumer.setName("External browser  error reader");
            errConsumer.start();
            Thread thread = new Thread() {

                @Override
                public void run() {
                    try {
                        process.waitFor();
                        if (reportNonZeroExitValue[0] && process.exitValue() != 0) {
                            Trace.trace(Trace.SEVERE, //$NON-NLS-1$
                            "External browser returned non-zero status: " + process.exitValue());
                            WebBrowserUtil.openError(NLS.bind(Messages.errorCouldNotLaunchExternalWebBrowser, urlText));
                        }
                        DefaultBrowserSupport.getInstance().removeBrowser(ExternalBrowserInstance.this);
                    } catch (Exception e) {
                    }
                }
            };
            thread.setDaemon(true);
            thread.start();
        } catch (Exception e) {
            Trace.trace(Trace.SEVERE, "Could not launch external browser", e);
            WebBrowserUtil.openError(NLS.bind(Messages.errorCouldNotLaunchExternalWebBrowser, urlText));
        }
    }

    /**
* @return true if the location appears to be a Mac Application bundle
*         (.app)
*/
    public static boolean isMacAppBundle(String location) {
        return isMacAppBundle(new File(location));
    }

    /**
* @return true if the location appears to be a Mac Application bundle
*         (.app)
*/
    public static boolean isMacAppBundle(File location) {
        // A very quick heuristic based on Apple's Bundle Programming Guide
        // https://developer.apple.com/library/mac/documentation/CoreFoundation/Conceptual/CFBundles/BundleTypes/BundleTypes.html#//apple_ref/doc/uid/10000123i-CH101-SW19
        //$NON-NLS-1$ //$NON-NLS-2$
        File macosDir = new File(new File(location, "Contents"), "MacOS");
        //$NON-NLS-1$ //$NON-NLS-2$
        File plist = new File(new File(location, "Contents"), "Info.plist");
        return location.isDirectory() && macosDir.isDirectory() && plist.isFile();
    }

    private String join(String delim, String... data) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            sb.append(data[i]);
            if (i >= data.length - 1) {
                break;
            }
            sb.append(delim);
        }
        return sb.toString();
    }

    @Override
    public boolean close() {
        try {
            process.destroy();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
