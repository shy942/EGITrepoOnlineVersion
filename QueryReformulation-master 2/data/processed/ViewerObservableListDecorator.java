/***/
package org.eclipse.jface.internal.databinding.viewers;

import org.eclipse.core.databinding.observable.list.DecoratingObservableList;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.jface.databinding.viewers.IViewerObservableList;
import org.eclipse.jface.viewers.Viewer;

/**
* @since 3.3
*
*/
public class ViewerObservableListDecorator extends DecoratingObservableList implements IViewerObservableList {

    private final Viewer viewer;

    /**
* @param decorated
* @param viewer
*/
    public  ViewerObservableListDecorator(IObservableList decorated, Viewer viewer) {
        super(decorated, true);
        this.viewer = viewer;
    }

    @Override
    public Viewer getViewer() {
        return viewer;
    }
}
