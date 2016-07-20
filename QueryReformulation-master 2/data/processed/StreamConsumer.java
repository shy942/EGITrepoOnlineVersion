/***/
package org.eclipse.ui.internal.browser.browsers;

import java.io.*;
import org.eclipse.ui.internal.browser.WebBrowserUIPlugin;

/**
* Used to receive output from processes
*/
public class StreamConsumer extends Thread {

    BufferedReader bReader;

    private String lastLine;

    public  StreamConsumer(InputStream inputStream) {
        super();
        setDaemon(true);
        bReader = new BufferedReader(new InputStreamReader(inputStream));
    }

    @Override
    public void run() {
        try {
            String line;
            while (null != (line = bReader.readLine())) {
                lastLine = line;
                BrowserLog.log(line);
            }
            bReader.close();
        } catch (IOException ioe) {
            WebBrowserUIPlugin.logError("Exception occurred reading from web browser.", ioe);
        }
    }

    /**
* @return last line obtained or null
*/
    public String getLastLine() {
        return lastLine;
    }
}
