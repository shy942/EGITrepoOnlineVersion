/***/
package org.eclipse.core.internal.databinding.observable;

import org.eclipse.core.databinding.observable.value.DecoratingObservableValue;
import org.eclipse.core.databinding.observable.value.IObservableValue;

/**
* An unmodifiable wrapper class for IObservableValue instances.
*
* @param <T>
*            the type of the value being observed
*
* @since 1.1
*/
public class UnmodifiableObservableValue<T> extends DecoratingObservableValue<T> {

    /**
* Constructs an UnmodifiableObservableValue which wraps the given
* observable value
*
* @param wrappedValue
*            the observable value to wrap in an unmodifiable instance.
*/
    public  UnmodifiableObservableValue(IObservableValue<T> wrappedValue) {
        super(wrappedValue, false);
    }

    @Override
    public void setValue(T value) {
        throw new UnsupportedOperationException();
    }
}
