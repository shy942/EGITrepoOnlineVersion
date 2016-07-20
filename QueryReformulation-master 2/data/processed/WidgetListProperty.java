/***/
package org.eclipse.jface.databinding.swt;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.property.list.SimpleListProperty;
import org.eclipse.jface.internal.databinding.swt.SWTObservableListDecorator;
import org.eclipse.swt.widgets.Widget;

/**
* Abstract list property implementation for {@link Widget} properties. This
* class implements some basic behavior that widget properties are generally
* expected to have, namely:
* <ul>
* <li>Calling {@link #observe(Object)} should create the observable on the
* display realm of the widget, rather than the current default realm
* <li>All <code>observe()</code> methods should return an
* {@link ISWTObservable}
* </ul>
*
* @since 1.3
*/
public abstract class WidgetListProperty extends SimpleListProperty implements IWidgetListProperty {

    @Override
    public IObservableList observe(Object source) {
        if (source instanceof Widget) {
            return observe((Widget) source);
        }
        return super.observe(source);
    }

    @Override
    public IObservableList observe(Realm realm, Object source) {
        return new SWTObservableListDecorator(super.observe(realm, source), (Widget) source);
    }

    @Override
    public ISWTObservableList observe(Widget widget) {
        return (ISWTObservableList) observe(DisplayRealm.getRealm(widget.getDisplay()), widget);
    }
}
