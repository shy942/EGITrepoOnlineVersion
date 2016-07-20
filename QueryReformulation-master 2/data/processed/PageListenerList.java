/***/
package org.eclipse.ui.internal;

import org.eclipse.core.commands.common.EventManager;
import org.eclipse.core.runtime.SafeRunner;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.ui.IPageListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.internal.misc.UIStats;

/**
* Part listener list.
*/
public class PageListenerList extends EventManager {

    /**
* PartNotifier constructor comment.
*/
    public  PageListenerList() {
        super();
    }

    /**
* Adds an IPartListener to the part service.
*/
    public void addPageListener(IPageListener l) {
        addListenerObject(l);
    }

    /**
* Calls a page listener with associated performance event instrumentation
*
* @param runnable
* @param listener
* @param page
* @param description
*/
    private void fireEvent(SafeRunnable runnable, IPageListener listener, IWorkbenchPage page, String description) {
        //for debugging
        String label = null;
        if (UIStats.isDebugging(UIStats.NOTIFY_PAGE_LISTENERS)) {
            label = description + page.getLabel();
            UIStats.start(UIStats.NOTIFY_PAGE_LISTENERS, label);
        }
        SafeRunner.run(runnable);
        if (UIStats.isDebugging(UIStats.NOTIFY_PAGE_LISTENERS)) {
            UIStats.end(UIStats.NOTIFY_PAGE_LISTENERS, listener, label);
        }
    }

    /**
* Notifies the listener that a part has been activated.
*/
    public void firePageActivated(final IWorkbenchPage page) {
        Object[] array = getListeners();
        for (int i = 0; i < array.length; i++) {
            final IPageListener l = (IPageListener) array[i];
            fireEvent(new SafeRunnable() {

                @Override
                public void run() {
                    l.pageActivated(page);
                }
            }, l, page, //$NON-NLS-1$
            "activated::");
        }
    }

    /**
* Notifies the listener that a part has been closed
*/
    public void firePageClosed(final IWorkbenchPage page) {
        Object[] array = getListeners();
        for (int i = 0; i < array.length; i++) {
            final IPageListener l = (IPageListener) array[i];
            fireEvent(new SafeRunnable() {

                @Override
                public void run() {
                    l.pageClosed(page);
                }
            }, l, page, //$NON-NLS-1$
            "closed::");
        }
    }

    /**
* Notifies the listener that a part has been opened.
*/
    public void firePageOpened(final IWorkbenchPage page) {
        Object[] listeners = getListeners();
        for (int i = 0; i < listeners.length; i++) {
            final IPageListener l = (IPageListener) listeners[i];
            fireEvent(new SafeRunnable() {

                @Override
                public void run() {
                    l.pageOpened(page);
                }
            }, l, page, //$NON-NLS-1$
            "opened::");
        }
    }

    /**
* Removes an IPartListener from the part service.
*/
    public void removePageListener(IPageListener l) {
        removeListenerObject(l);
    }
}
