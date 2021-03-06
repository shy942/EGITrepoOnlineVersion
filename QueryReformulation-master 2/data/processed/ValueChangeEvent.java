/***/
package org.eclipse.core.databinding.observable.value;

import org.eclipse.core.databinding.observable.IObservablesListener;
import org.eclipse.core.databinding.observable.ObservableEvent;

/**
* Value change event describing a change of an {@link IObservableValue}
* object's current value.
*
* @param <T>
*            the type of value being observed
*
* @since 1.0
*
*/
public class ValueChangeEvent<T> extends ObservableEvent {

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
* Creates a new value change event.
*
* @param source
*            the source observable value
* @param diff
*            the value change
*/
    public  ValueChangeEvent(IObservableValue<T> source, ValueDiff<T> diff) {
        super(source);
        this.diff = diff;
    }

    /**
* Returns the observable value from which this event originated.
*
* @return returns the observable value from which this event originated
*/
    @SuppressWarnings("unchecked")
    public IObservableValue<T> getObservableValue() {
        return (IObservableValue<T>) getSource();
    }

    @SuppressWarnings("unchecked")
    @Override
    protected void dispatch(IObservablesListener listener) {
        ((IValueChangeListener<T>) listener).handleValueChange(this);
    }

    @Override
    protected Object getListenerType() {
        return TYPE;
    }
}
