/***/
package org.eclipse.jface.databinding.viewers;

import org.eclipse.core.databinding.property.list.IListProperty;
import org.eclipse.jface.viewers.Viewer;

/**
* {@link IListProperty} for observing a JFace viewer
*
* @since 1.3
* @noimplement This interface is not intended to be implemented by clients.
*/
public interface IViewerListProperty extends IListProperty {

    /**
* Returns an {@link IViewerObservableList} observing this list property on
* the given viewer
*
* @param viewer
*            the source viewer
* @return an observable list observing this list property on the given
*         viewer
*/
    public IViewerObservableList observe(Viewer viewer);
}
