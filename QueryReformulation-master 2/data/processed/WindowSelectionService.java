/***/
package org.eclipse.ui.internal;

import org.eclipse.ui.IWorkbenchWindow;

/* package */
class WindowSelectionService extends AbstractSelectionService {

    /**
* The window.
*/
    private IWorkbenchWindow window;

    /**
* Creates a new selection service for the given window.
*/
    public  WindowSelectionService(IWorkbenchWindow window) {
        setWindow(window);
    }

    /**
* Sets the window.
*/
    private void setWindow(IWorkbenchWindow window) {
        this.window = window;
    }

    /**
* Returns the window.
*/
    protected IWorkbenchWindow getWindow() {
        return window;
    }

    /*
* @see AbstractSelectionService#createPartTracker(String)
*/
    @Override
    protected AbstractPartSelectionTracker createPartTracker(String partId) {
        return new WindowPartSelectionTracker(getWindow(), partId);
    }
}
