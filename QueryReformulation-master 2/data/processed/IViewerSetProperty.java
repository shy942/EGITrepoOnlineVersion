/***/
package org.eclipse.jface.databinding.viewers;

import org.eclipse.core.databinding.property.set.ISetProperty;
import org.eclipse.jface.viewers.Viewer;

/**
* {@link ISetProperty} for observing a JFace viewer
*
* @since 1.3
* @noimplement This interface is not intended to be implemented by clients.
*/
public interface IViewerSetProperty extends ISetProperty {

    /**
* Returns an {@link IViewerObservableSet} observing this set property on
* the given viewer
*
* @param viewer
*            the source viewer
* @return an observable set observing this set property on the given viewer
*/
    public IViewerObservableSet observe(Viewer viewer);
}
