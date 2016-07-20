/***/
package org.eclipse.jface.internal.databinding.viewers;

import org.eclipse.jface.viewers.AbstractListViewer;

/**
* NON-API - A {@link ViewerUpdater} that updates {@link AbstractListViewer}
* instances.
*
* @since 1.2
*/
class ListViewerUpdater extends ViewerUpdater {

    private AbstractListViewer viewer;

     ListViewerUpdater(AbstractListViewer viewer) {
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
    public void add(Object[] elements) {
        viewer.add(elements);
    }

    @Override
    public void remove(Object[] elements) {
        viewer.remove(elements);
    }
}
