/***/
package org.eclipse.jface.internal.databinding.viewers;

import org.eclipse.jface.viewers.AbstractTableViewer;

/**
* NON-API - A {@link ViewerUpdater} that updates {@link AbstractTableViewer}
* instances.
*
* @since 1.2
*/
class TableViewerUpdater extends ViewerUpdater {

    private AbstractTableViewer viewer;

     TableViewerUpdater(AbstractTableViewer viewer) {
        super(viewer);
        this.viewer = viewer;
    }

    @Override
    public void insert(Object element, int position) {
        viewer.insert(element, position);
    }

    @Override
    public void remove(Object element, int position) {
        viewer.remove(element);
    }

    @Override
    public void replace(Object oldElement, Object newElement, int position) {
        if (isElementOrderPreserved())
            viewer.replace(newElement, position);
        else {
            super.replace(oldElement, newElement, position);
        }
    }

    @Override
    public void add(Object[] elements) {
        viewer.add(elements);
    }

    @Override
    public void remove(Object[] elements) {
        viewer.remove(elements);
    }
}