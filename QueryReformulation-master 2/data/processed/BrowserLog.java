/***/
package org.eclipse.ui.internal.browser.browsers;

import java.io.*;
import com.ibm.icu.text.DateFormat;
import com.ibm.icu.text.SimpleDateFormat;
import java.util.Date;
import org.eclipse.ui.internal.browser.WebBrowserUIPlugin;

/**
* Log for messages output by external browser processes.
*/
public class BrowserLog {

    private static BrowserLog instance;

    private String logFileName;

    private boolean newSession;

    //$NON-NLS-1$
    DateFormat formatter = new SimpleDateFormat("MMM dd, yyyy kk:mm:ss.SS");

    //$NON-NLS-1$
    String LN = System.getProperty("line.separator");

    /**
* Constructor
*/
    private  BrowserLog() {
        try {
            newSession = true;
            //$NON-NLS-1$
            logFileName = WebBrowserUIPlugin.getInstance().getStateLocation().append("browser.log").toOSString();
        } catch (Exception e) {
        }
    }

    /**
* Obtains singleton
*/
    private static BrowserLog getInstance() {
        if (instance == null) {
            instance = new BrowserLog();
        }
        return instance;
    }

    /**
* Appends a line to the browser.log
*/
    public static synchronized void log(String message) {
        getInstance().append(message);
    }

    private void append(String message) {
        if (logFileName == null) {
            return;
        }
        Writer outWriter = null;
        try {
            outWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(logFileName, true), //$NON-NLS-1$
            "UTF-8"));
            if (newSession) {
                newSession = false;
                outWriter.write(LN + formatter.format(new Date()) + " NEW SESSION" + //$NON-NLS-1$
                LN);
            }
            //$NON-NLS-1$
            outWriter.write(formatter.format(new Date()) + " " + message + LN);
            outWriter.flush();
            outWriter.close();
        } catch (Exception e) {
            if (outWriter != null) {
                try {
                    outWriter.close();
                } catch (IOException ioe) {
                }
            }
        }
    }
}
