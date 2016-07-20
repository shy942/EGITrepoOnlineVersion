/***/
package org.eclipse.core.databinding.observable.value;

/**
* An observable value whose changes can be vetoed by listeners.
*
* @param <T>
*            the type of value being observed
*
* @noextend This interface is not intended to be extended by clients.
* @noimplement This interface is not intended to be implemented by clients.
*              Clients should instead subclass one of the classes that
*              implement this interface. Note that direct implementers of this
*              interface outside of the framework will be broken in future
*              releases when methods are added to this interface.
*
* @since 1.0
*
*/
public interface IVetoableValue<T> extends IObservableValue<T> {

    /**
* @param listener
*/
    public void addValueChangingListener(IValueChangingListener<T> listener);

    /**
* @param listener
*/
    public void removeValueChangingListener(IValueChangingListener<T> listener);
}
