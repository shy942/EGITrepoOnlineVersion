/***/
package org.eclipse.jface.action;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IProgressMonitorWithBlocking;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;

/**
* A status line manager is a contribution manager which realizes itself and its items
* in a status line control.
* <p>
* This class may be instantiated; it may also be subclassed if a more
* sophisticated layout is required.
* </p>
*/
public class StatusLineManager extends ContributionManager implements IStatusLineManager {

    /**
* Identifier of group marker used to position contributions at the beginning
* of the status line.
*
* @since 3.0
*/
    //$NON-NLS-1$
    public static final String BEGIN_GROUP = "BEGIN_GROUP";

    /**
* Identifier of group marker used to position contributions in the middle
* of the status line.
*
* @since 3.0
*/
    //$NON-NLS-1$
    public static final String MIDDLE_GROUP = "MIDDLE_GROUP";

    /**
* Identifier of group marker used to position contributions at the end
* of the status line.
*
* @since 3.0
*/
    //$NON-NLS-1$
    public static final String END_GROUP = "END_GROUP";

    /**
* The status line control; <code>null</code> before
* creation and after disposal.
*/
    private Composite statusLine = null;

    /**
* Creates a new status line manager.
* Use the <code>createControl</code> method to create the
* status line control.
*/
    public  StatusLineManager() {
        add(new GroupMarker(BEGIN_GROUP));
        add(new GroupMarker(MIDDLE_GROUP));
        add(new GroupMarker(END_GROUP));
    }

    /**
* Creates and returns this manager's status line control.
* Does not create a new control if one already exists.
* <p>
* Note: Since 3.0 the return type is <code>Control</code>.  Before 3.0, the return type was
*   the package-private class <code>StatusLine</code>.
* </p>
*
* @param parent the parent control
* @return the status line control
*/
    public Control createControl(Composite parent) {
        return createControl(parent, SWT.NONE);
    }

    /**
* Creates and returns this manager's status line control.
* Does not create a new control if one already exists.
*
* @param parent the parent control
* @param style the style for the control
* @return the status line control
* @since 3.0
*/
    public Control createControl(Composite parent, int style) {
        if (!statusLineExist() && parent != null) {
            statusLine = new StatusLine(parent, style);
            update(false);
        }
        return statusLine;
    }

    /**
* Disposes of this status line manager and frees all allocated SWT resources.
* Notifies all contribution items of the dispose. Note that this method does
* not clean up references between this status line manager and its associated
* contribution items. Use <code>removeAll</code> for that purpose.
*/
    public void dispose() {
        if (statusLineExist()) {
            statusLine.dispose();
        }
        statusLine = null;
        IContributionItem items[] = getItems();
        for (int i = 0; i < items.length; i++) {
            items[i].dispose();
        }
    }

    /**
* Returns the control used by this StatusLineManager.
*
* @return the control used by this manager
*/
    public Control getControl() {
        return statusLine;
    }

    /**
* Returns the progress monitor delegate. Override this method
* to provide your own object used to handle progress.
*
* @return the IProgressMonitor delegate
* @since 3.0
*/
    protected IProgressMonitor getProgressMonitorDelegate() {
        return (IProgressMonitor) getControl();
    }

    @Override
    public IProgressMonitor getProgressMonitor() {
        final IProgressMonitor progressDelegate = statusLineExist() ? getProgressMonitorDelegate() : new NullProgressMonitor();
        return new IProgressMonitorWithBlocking() {

            @Override
            public void beginTask(String name, int totalWork) {
                progressDelegate.beginTask(name, totalWork);
            }

            @Override
            public void done() {
                progressDelegate.done();
            }

            @Override
            public void internalWorked(double work) {
                progressDelegate.internalWorked(work);
            }

            @Override
            public boolean isCanceled() {
                return progressDelegate.isCanceled();
            }

            @Override
            public void setCanceled(boolean value) {
                //Don't bother updating for disposed status
                if (statusLineExist()) {
                    progressDelegate.setCanceled(value);
                }
            }

            @Override
            public void setTaskName(String name) {
                progressDelegate.setTaskName(name);
            }

            @Override
            public void subTask(String name) {
                progressDelegate.subTask(name);
            }

            @Override
            public void worked(int work) {
                progressDelegate.worked(work);
            }

            @Override
            public void clearBlocked() {
            //Do nothing here as we let the modal context handle it
            }

            @Override
            public void setBlocked(IStatus reason) {
            //			Do nothing here as we let the modal context handle it
            }
        };
    }

    @Override
    public boolean isCancelEnabled() {
        return statusLineExist() && ((StatusLine) statusLine).isCancelEnabled();
    }

    @Override
    public void setCancelEnabled(boolean enabled) {
        if (statusLineExist()) {
            ((StatusLine) statusLine).setCancelEnabled(enabled);
        }
    }

    @Override
    public void setErrorMessage(String message) {
        if (statusLineExist()) {
            ((StatusLine) statusLine).setErrorMessage(message);
        }
    }

    @Override
    public void setErrorMessage(Image image, String message) {
        if (statusLineExist()) {
            ((StatusLine) statusLine).setErrorMessage(image, message);
        }
    }

    @Override
    public void setMessage(String message) {
        if (statusLineExist()) {
            ((StatusLine) statusLine).setMessage(message);
        }
    }

    @Override
    public void setMessage(Image image, String message) {
        if (statusLineExist()) {
            ((StatusLine) statusLine).setMessage(image, message);
        }
    }

    /**
* Returns whether the status line control is created
* and not disposed.
*
* @return <code>true</code> if the control is created
*	and not disposed, <code>false</code> otherwise
*/
    private boolean statusLineExist() {
        return statusLine != null && !statusLine.isDisposed();
    }

    @Override
    public void update(boolean force) {
        //boolean DEBUG= false;
        if (isDirty() || force) {
            if (statusLineExist()) {
                statusLine.setRedraw(false);
                // NOTE: the update algorithm is non-incremental.
                // An incremental algorithm requires that SWT items can be created in the middle of the list
                // but the ContributionItem.fill(Composite) method used here does not take an index, so this
                // is not possible.
                Control ws[] = statusLine.getChildren();
                for (int i = 0; i < ws.length; i++) {
                    Control w = ws[i];
                    Object data = w.getData();
                    if (data instanceof IContributionItem) {
                        w.dispose();
                    }
                }
                int oldChildCount = statusLine.getChildren().length;
                IContributionItem[] items = getItems();
                for (int i = 0; i < items.length; ++i) {
                    IContributionItem ci = items[i];
                    if (ci.isVisible()) {
                        ci.fill(statusLine);
                        // associate controls with contribution item
                        Control[] newChildren = statusLine.getChildren();
                        for (int j = oldChildCount; j < newChildren.length; j++) {
                            newChildren[j].setData(ci);
                        }
                        oldChildCount = newChildren.length;
                    }
                }
                setDirty(false);
                statusLine.layout();
                statusLine.setRedraw(true);
            }
        }
    }
}
