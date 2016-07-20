/***/
package org.eclipse.jface.internal.databinding.viewers;

import org.eclipse.core.databinding.observable.set.DecoratingObservableSet;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.jface.databinding.viewers.IViewerObservableSet;
import org.eclipse.jface.viewers.Viewer;

/**
* @since 3.3
*
*/
public class ViewerObservableSetDecorator extends DecoratingObservableSet implements IViewerObservableSet {

    private final Viewer viewer;

    /**
* @param decorated
* @param viewer
*/
    public  ViewerObservableSetDecorator(IObservableSet decorated, Viewer viewer) {
        super(decorated, true);
        this.viewer = viewer;
    }

    @Override
    public Viewer getViewer() {
        return viewer;
    }
}
