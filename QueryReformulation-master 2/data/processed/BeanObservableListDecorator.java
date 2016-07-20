/***/
package org.eclipse.core.internal.databinding.beans;

import java.beans.PropertyDescriptor;
import org.eclipse.core.databinding.beans.IBeanObservable;
import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.IObserving;
import org.eclipse.core.databinding.observable.list.DecoratingObservableList;
import org.eclipse.core.databinding.observable.list.IObservableList;

/**
* {@link IBeanObservable} decorator for an {@link IObservableList}.
*
* @since 3.3
*/
public class BeanObservableListDecorator extends DecoratingObservableList implements IBeanObservable {

    private PropertyDescriptor propertyDescriptor;

    /**
* @param decorated
* @param propertyDescriptor
*/
    public  BeanObservableListDecorator(IObservableList decorated, PropertyDescriptor propertyDescriptor) {
        super(decorated, true);
        this.propertyDescriptor = propertyDescriptor;
    }

    @Override
    public synchronized void dispose() {
        this.propertyDescriptor = null;
        super.dispose();
    }

    @Override
    public Object getObserved() {
        IObservable decorated = getDecorated();
        if (decorated instanceof IObserving)
            return ((IObserving) decorated).getObserved();
        return null;
    }

    @Override
    public PropertyDescriptor getPropertyDescriptor() {
        return propertyDescriptor;
    }
}
