/***/
package org.eclipse.jface.databinding.viewers;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.core.databinding.property.set.SimpleSetProperty;
import org.eclipse.jface.databinding.swt.DisplayRealm;
import org.eclipse.jface.internal.databinding.viewers.ViewerObservableSetDecorator;
import org.eclipse.jface.viewers.Viewer;

/**
* Abstract set property implementation for {@link Viewer} properties. This
* class implements some basic behavior that viewer properties are generally
* expected to have, namely:
* <ul>
* <li>Calling {@link #observe(Object)} should create the observable on the
* display realm of the viewer's control, rather than the current default realm
* <li>All <code>observe()</code> methods should return an
* {@link IViewerObservableSet}
* </ul>
*
* @since 1.3
*/
public abstract class ViewerSetProperty extends SimpleSetProperty implements IViewerSetProperty {

    @Override
    public IObservableSet observe(Object source) {
        if (source instanceof Viewer) {
            return observe((Viewer) source);
        }
        return super.observe(source);
    }

    @Override
    public IObservableSet observe(Realm realm, Object source) {
        IObservableSet observable = super.observe(realm, source);
        if (source instanceof Viewer)
            return new ViewerObservableSetDecorator(observable, (Viewer) source);
        return observable;
    }

    @Override
    public IViewerObservableSet observe(Viewer viewer) {
        return (IViewerObservableSet) observe(DisplayRealm.getRealm(viewer.getControl().getDisplay()), viewer);
    }
}
