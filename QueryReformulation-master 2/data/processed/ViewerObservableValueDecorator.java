/***/
package org.eclipse.jface.internal.databinding.viewers;

import org.eclipse.core.databinding.observable.value.DecoratingObservableValue;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.viewers.IViewerObservableValue;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

/**
* @since 3.3
*
*/
public class ViewerObservableValueDecorator extends DecoratingObservableValue implements IViewerObservableValue, Listener {

    private Viewer viewer;

    /**
* @param decorated
* @param viewer
*/
    public  ViewerObservableValueDecorator(IObservableValue decorated, Viewer viewer) {
        super(decorated, true);
        this.viewer = viewer;
        viewer.getControl().addListener(SWT.Dispose, this);
    }

    @Override
    public void handleEvent(Event event) {
        if (event.type == SWT.Dispose)
            dispose();
    }

    @Override
    public Viewer getViewer() {
        return viewer;
    }

    @Override
    public synchronized void dispose() {
        if (viewer != null) {
            Control control = viewer.getControl();
            if (control != null && !control.isDisposed()) {
                control.removeListener(SWT.Dispose, this);
            }
            viewer = null;
        }
        super.dispose();
    }
}
