/***/
package org.eclipse.ui.dynamic;

import org.eclipse.ui.IStartup;

/**
* @since 3.1
*/
public class DynamicStartup implements IStartup {

    public static Throwable history;

    /**
*
*/
    public  DynamicStartup() {
        super();
    }

    @Override
    public void earlyStartup() {
        history = new Throwable();
        history.fillInStackTrace();
    }
}
