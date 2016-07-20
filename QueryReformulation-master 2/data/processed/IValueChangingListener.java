/***/
package org.eclipse.core.databinding.observable.value;

import org.eclipse.core.databinding.observable.IObservablesListener;

/**
* Listener for pre-change events for observable values.
*
* @param <T>
*            the type of value being observed
*
* @since 1.0
*
*/
@FunctionalInterface
public interface IValueChangingListener<T> extends IObservablesListener {

    /**
* This method is called when the value is about to change and provides an
* opportunity to veto the change. The given event object must only be used
* locally in this method because it may be reused for other change
* notifications. The diff object referenced by the event is immutable and
* may be used non-locally.
*
* @param event
*/
    public void handleValueChanging(ValueChangingEvent<T> event);
}
