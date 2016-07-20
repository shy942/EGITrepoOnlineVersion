/***/
package org.eclipse.ui.internal.browser.macosx;

import java.util.ArrayList;
import java.util.StringTokenizer;
import org.eclipse.ui.internal.browser.browsers.DefaultBrowser;

public class SafariBrowser extends DefaultBrowser {

    public  SafariBrowser(String id, String location, String parameters) {
        super(id, location, parameters);
        this.location = location;
        this.parameters = parameters;
    }

    /**
* Creates the final command to launch.
*
* @param path
* @param url
* @return String[]
*/
    @Override
    protected String[] prepareCommand(String path, String url) {
        if (url != null && url.toLowerCase().startsWith("file:")) {
            //$NON-NLS-1$
            url = url.substring(5);
        }
        ArrayList<String> tokenList = new ArrayList();
        //Divide along quotation marks
        StringTokenizer qTokenizer = new StringTokenizer(path.trim(), "\"", //$NON-NLS-1$
        true);
        boolean withinQuotation = false;
        //$NON-NLS-1$
        String quotedString = "";
        while (qTokenizer.hasMoreTokens()) {
            String curToken = qTokenizer.nextToken();
            if (curToken.equals("\"")) {
                //$NON-NLS-1$
                if (withinQuotation) {
                    // quotes prevent launching on Unix 35673
                    tokenList.add(quotedString);
                } else {
                    //$NON-NLS-1$
                    quotedString = "";
                }
                withinQuotation = !withinQuotation;
                continue;
            } else if (withinQuotation) {
                quotedString = curToken;
                continue;
            } else {
                //divide unquoted strings along white space
                StringTokenizer parser = new StringTokenizer(curToken.trim());
                while (parser.hasMoreTokens()) {
                    tokenList.add(parser.nextToken());
                }
            }
        }
        // substitute %1 by url
        boolean substituted = false;
        for (int i = 0; i < tokenList.size(); i++) {
            String token = tokenList.get(i);
            String newToken = doSubstitutions(token, url);
            if (newToken != null) {
                tokenList.set(i, newToken);
                substituted = true;
            }
        }
        // add the url if not substituted already
        if (!substituted)
            tokenList.add(url);
        String[] command = new String[tokenList.size()];
        tokenList.toArray(command);
        return command;
    }
}
