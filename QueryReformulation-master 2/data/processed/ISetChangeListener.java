/***/
package org.eclipse.core.databinding.observable.set;

import org.eclipse.core.databinding.observable.IObservablesListener;

/**
* Listener for changes to observable sets.
*
* @param <E>
*            the type of elements in the set being observed
*
* @since 1.0
*
*/
@FunctionalInterface
public interface ISetChangeListener<E> extends IObservablesListener {

    /**
* Handle a change to an observable set. The given event object must only be
* used locally in this method because it may be reused for other change
* notifications. The diff object referenced by the event is immutable and
* may be used non-locally.
*
* @param event
*            the event
*/
    void handleSetChange(SetChangeEvent<? extends E> event);
}
