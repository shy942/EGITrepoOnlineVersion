/***/
package org.eclipse.ui.internal;

import org.eclipse.core.runtime.ListenerList;
import org.eclipse.ui.IPageListener;
import org.eclipse.ui.IPageService;
import org.eclipse.ui.IPerspectiveListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.services.IDisposable;

/**
* @since 3.4
*
*/
public class SlavePageService implements IPageService, IDisposable {

    private IPageService parent;

    private ListenerList pageListeners = new ListenerList(ListenerList.IDENTITY);

    private ListenerList perspectiveListeners = new ListenerList(ListenerList.IDENTITY);

    public  SlavePageService(IPageService parent) {
        if (parent == null) {
            throw new IllegalArgumentException(//$NON-NLS-1$
            "Parent IPageService cannot be null");
        }
        this.parent = parent;
    }

    @Override
    public void addPageListener(IPageListener listener) {
        pageListeners.add(listener);
        parent.addPageListener(listener);
    }

    @Override
    public void addPerspectiveListener(IPerspectiveListener listener) {
        perspectiveListeners.add(listener);
        parent.addPerspectiveListener(listener);
    }

    @Override
    public IWorkbenchPage getActivePage() {
        return parent.getActivePage();
    }

    @Override
    public void removePageListener(IPageListener listener) {
        pageListeners.remove(listener);
        parent.removePageListener(listener);
    }

    @Override
    public void removePerspectiveListener(IPerspectiveListener listener) {
        perspectiveListeners.remove(listener);
        parent.removePerspectiveListener(listener);
    }

    @Override
    public void dispose() {
        Object[] listeners = pageListeners.getListeners();
        for (int i = 0; i < listeners.length; i++) {
            parent.removePageListener((IPageListener) listeners[i]);
        }
        pageListeners.clear();
        listeners = perspectiveListeners.getListeners();
        for (int i = 0; i < listeners.length; i++) {
            parent.removePerspectiveListener((IPerspectiveListener) listeners[i]);
        }
        perspectiveListeners.clear();
    }
}