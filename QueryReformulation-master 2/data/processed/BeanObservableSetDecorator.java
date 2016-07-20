/***/
package org.eclipse.core.internal.databinding.beans;

import java.beans.PropertyDescriptor;
import org.eclipse.core.databinding.beans.IBeanObservable;
import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.IObserving;
import org.eclipse.core.databinding.observable.set.DecoratingObservableSet;
import org.eclipse.core.databinding.observable.set.IObservableSet;

/**
* {@link IBeanObservable} decorator for an {@link IObservableSet}.
*
* @since 3.3
*/
public class BeanObservableSetDecorator extends DecoratingObservableSet implements IBeanObservable {

    private PropertyDescriptor propertyDescriptor;

    /**
* @param decorated
* @param propertyDescriptor
*/
    public  BeanObservableSetDecorator(IObservableSet decorated, PropertyDescriptor propertyDescriptor) {
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
