/***/
package org.eclipse.core.databinding.observable.value;

import org.eclipse.core.databinding.observable.Diffs;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.internal.databinding.observable.Util;

/**
*
* <p>
* This class is thread safe. All state accessing methods must be invoked from
* the {@link Realm#isCurrent() current realm}. Methods for adding and removing
* listeners may be invoked from any thread.
* </p>
*
* @param <T>
*            the type of value being observed
* @since 1.0
*
*/
public abstract class AbstractVetoableValue<T> extends AbstractObservableValue<T> implements IVetoableValue<T> {

    /**
* Creates a new vetoable value.
*/
    public  AbstractVetoableValue() {
        this(Realm.getDefault());
    }

    /**
* @param realm
*/
    public  AbstractVetoableValue(Realm realm) {
        super(realm);
    }

    @Override
    protected final void doSetValue(T value) {
        T currentValue = doGetValue();
        ValueDiff<T> diff = Diffs.createValueDiff(currentValue, value);
        boolean okToProceed = fireValueChanging(diff);
        if (!okToProceed) {
            //$NON-NLS-1$
            throw new ChangeVetoException("Change not permitted");
        }
        doSetApprovedValue(value);
        if (!Util.equals(diff.getOldValue(), diff.getNewValue())) {
            fireValueChange(diff);
        }
    }

    /**
* Sets the value. Invoked after performing veto checks. Should not fire
* change events.
*
* @param value
*/
    protected abstract void doSetApprovedValue(T value);

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
