/***/
package org.eclipse.e4.ui.tests.workbench;

import java.util.ArrayList;

/**
* Class used to capture the SWT structure expected when rendering a partuclar
* UI model.
*/
public class SWTResult {

    public Class clazz;

    public String text;

    public ArrayList kids = new ArrayList();

    public  SWTResult(Class theClass, String theText, SWTResult[] children) {
        clazz = theClass;
        text = theText;
        if (children != null) {
            for (SWTResult result : children) {
                kids.add(result);
            }
        }
    }
}
