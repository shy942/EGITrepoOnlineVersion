/***/
package org.eclipse.ui.progress;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.internal.progress.ProgressMessages;
import org.eclipse.ui.model.IWorkbenchAdapter;

/**
* The PendingUpdateAdapter is a convenience object that can be used
* by a BaseWorkbenchContentProvider that wants to show a pending update.
*
* @since 3.2
*/
public class PendingUpdateAdapter implements IWorkbenchAdapter, IAdaptable {

    private boolean removed = false;

    /**
* Return whether or not this has been removed from the tree.
* @return boolean
*/
    protected boolean isRemoved() {
        return removed;
    }

    /**
* Set whether or not this has been removed from the tree.
* @param removedValue boolean
*/
    protected void setRemoved(boolean removedValue) {
        this.removed = removedValue;
    }

    /**
* Create a new instance of the receiver.
*/
    public  PendingUpdateAdapter() {
    //No initial behavior
    }

    @Override
    public Object getAdapter(Class adapter) {
        if (adapter == IWorkbenchAdapter.class) {
            return this;
        }
        return null;
    }

    @Override
    public Object[] getChildren(Object o) {
        return new Object[0];
    }

    @Override
    public ImageDescriptor getImageDescriptor(Object object) {
        return null;
    }

    @Override
    public String getLabel(Object o) {
        return ProgressMessages.PendingUpdateAdapter_PendingLabel;
    }

    @Override
    public Object getParent(Object o) {
        return null;
    }

    /**
* @since 3.4
*/
    @Override
    public String toString() {
        return getLabel(null);
    }
}
