/***/
package org.eclipse.core.databinding.observable.value;

import org.eclipse.core.databinding.observable.IObservablesListener;
import org.eclipse.core.databinding.observable.ObservableEvent;

/**
* Value changing event describing a pending change of an
* {@link IObservableValue} object's current value. Listeners can veto the
* pending change by setting {@link #veto} to <code>true</code>.
*
* @param <T>
*            the type of value being observed
*
* @since 1.0
*
*/
public class ValueChangingEvent<T> extends ObservableEvent {

    /**
*
*/
    private static final long serialVersionUID = 2305345286999701156L;

    static final Object TYPE = new Object();

    /**
* Description of the change to the source observable value. Listeners must
* not change this field.
*/
    public ValueDiff<T> diff;

    /**
* Flag for vetoing this change. Default value is <code>false</code>, can be
* set to <code>true</code> by listeners to veto this change.
*/
    public boolean veto = false;

    /**
* Creates a new value changing event.
*
* @param source
*            the source observable value
* @param diff
*            the value change
*/
    public  ValueChangingEvent(IObservableValue<T> source, ValueDiff<T> diff) {
        super(source);
        this.diff = diff;
    }

    /**
* @return the observable value from which this event originated
*/
    @SuppressWarnings("unchecked")
    public IObservableValue<T> getObservableValue() {
        return (IObservableValue<T>) source;
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void dispatch(IObservablesListener listener) {
        ((IValueChangingListener<T>) listener).handleValueChanging(this);
    }

    @Override
    protected Object getListenerType() {
        return TYPE;
    }
}
