/***/
package org.eclipse.ui.tests.statushandlers;

import org.eclipse.ui.internal.WorkbenchErrorHandlerProxy;
import org.eclipse.ui.statushandlers.AbstractStatusHandler;
import org.eclipse.ui.statushandlers.StatusAdapter;

/**
* The handler should be used during tests. It allows for checking the status
* and style used during last handling.
*
* @since 3.3
*/
public class TestStatusHandler extends AbstractStatusHandler {

    private static StatusAdapter lastHandledStatusAdapter;

    private static int lastHandledStyle;

    private static AbstractStatusHandler workbenchHandler;

    @Override
    public void handle(StatusAdapter statusAdapter, int style) {
        lastHandledStatusAdapter = statusAdapter;
        lastHandledStyle = style;
        if (workbenchHandler == null) {
            workbenchHandler = new WorkbenchErrorHandlerProxy();
        }
        // Forward to the workbench handler
        workbenchHandler.handle(statusAdapter, style);
    }

    /**
* Returns the status used during last handling
*
* @return the status
*/
    public static StatusAdapter getLastHandledStatusAdapter() {
        return lastHandledStatusAdapter;
    }

    /**
* Returns the style used during last handling
*
* @return the style
*/
    public static int getLastHandledStyle() {
        return lastHandledStyle;
    }
}
