/***/
package org.eclipse.ui.examples.jobs.views;

import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.model.BaseWorkbenchContentProvider;
import org.eclipse.ui.progress.DeferredTreeContentManager;

public class DeferredContentProvider extends BaseWorkbenchContentProvider {

    private DeferredTreeContentManager manager;

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        if (viewer instanceof AbstractTreeViewer) {
            manager = new DeferredTreeContentManager(this, (AbstractTreeViewer) viewer);
        }
        super.inputChanged(viewer, oldInput, newInput);
    }

    @Override
    public boolean hasChildren(Object element) {
        // if not needed after you first click on it.
        if (manager != null) {
            if (manager.isDeferredAdapter(element))
                return manager.mayHaveChildren(element);
        }
        return super.hasChildren(element);
    }

    @Override
    public Object[] getChildren(Object element) {
        if (manager != null) {
            Object[] children = manager.getChildren(element);
            if (children != null) {
                return children;
            }
        }
        return super.getChildren(element);
    }
}
