/***/
package org.eclipse.ui.internal;

import org.eclipse.ui.statushandlers.AbstractStatusHandler;
import org.eclipse.ui.statushandlers.StatusAdapter;

/**
* A proxy handler which passes all statuses to handler assigned to current
* application workbench advisor.
*
* <strong>EXPERIMENTAL</strong> This class or interface has been added as part
* of a work in progress. This API may change at any given time. Please do not
* use this API without consulting with the Platform/UI team.
*
* @since 3.3
*/
public class WorkbenchErrorHandlerProxy extends AbstractStatusHandler {

    @Override
    public void handle(final StatusAdapter statusAdapter, int style) {
        Workbench.getInstance().getAdvisor().getWorkbenchErrorHandler().handle(statusAdapter, style);
    }

    @Override
    public boolean supportsNotification(int type) {
        return Workbench.getInstance().getAdvisor().getWorkbenchErrorHandler().supportsNotification(type);
    }
}
