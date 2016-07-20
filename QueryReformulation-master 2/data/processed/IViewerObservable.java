/***/
package org.eclipse.jface.databinding.viewers;

import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.jface.viewers.Viewer;

/**
* {@link IObservable} observing a JFace Viewer.
*
* @since 1.2
*
*/
public interface IViewerObservable extends IObservable {

    /**
* Returns the underlying viewer for this observable.
*
* @return the viewer.
*/
    public Viewer getViewer();
}
