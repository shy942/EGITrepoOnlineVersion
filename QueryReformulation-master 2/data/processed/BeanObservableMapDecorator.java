/***/
package org.eclipse.core.internal.databinding.beans;

import java.beans.PropertyDescriptor;
import org.eclipse.core.databinding.beans.IBeanObservable;
import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.IObserving;
import org.eclipse.core.databinding.observable.map.DecoratingObservableMap;
import org.eclipse.core.databinding.observable.map.IObservableMap;

/**
* {@link IBeanObservable} decorator for an {@link IObservableMap}.
*
* @since 3.3
*/
public class BeanObservableMapDecorator extends DecoratingObservableMap implements IBeanObservable {

    private PropertyDescriptor propertyDescriptor;

    /**
* @param decorated
* @param propertyDescriptor
*/
    public  BeanObservableMapDecorator(IObservableMap decorated, PropertyDescriptor propertyDescriptor) {
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
