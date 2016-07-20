/***/
package org.eclipse.core.databinding.observable.value;

import org.eclipse.core.databinding.observable.Diffs;

/**
* An {@link IVetoableValue} decorator for an observable value.
*
* @param <T>
*            the type of value being observed
* @since 1.2
*/
public class DecoratingVetoableValue<T> extends DecoratingObservableValue<T> implements IVetoableValue<T> {

    /**
* @param decorated
* @param disposeDecoratedOnDispose
*/
    public  DecoratingVetoableValue(IObservableValue<T> decorated, boolean disposeDecoratedOnDispose) {
        super(decorated, disposeDecoratedOnDispose);
    }

    @Override
    public void setValue(T value) {
        checkRealm();
        T currentValue = getValue();
        ValueDiff<T> diff = Diffs.createValueDiff(currentValue, value);
        boolean okToProceed = fireValueChanging(diff);
        if (!okToProceed) {
            //$NON-NLS-1$
            throw new ChangeVetoException("Change not permitted");
        }
        super.setValue(value);
    }

    @Override
    public synchronized void addValueChangingListener(IValueChangingListener<T> listener) {
        addListener(ValueChangingEvent.TYPE, listener);
    }

    @Override
    public synchronized void removeValueChangingListener(IValueChangingListener<T> listener) {
        removeListener(ValueChangingEvent.TYPE, listener);
    }

    /**
* Notifies listeners about a pending change, and returns true if no
* listener vetoed the change.
*
* @param diff
* @return false if the change was vetoed, true otherwise
*/
    protected boolean fireValueChanging(ValueDiff<T> diff) {
        checkRealm();
        ValueChangingEvent<T> event = new ValueChangingEvent(this, diff);
        fireEvent(event);
        return !event.veto;
    }
}
