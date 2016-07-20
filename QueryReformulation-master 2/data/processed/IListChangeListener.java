/***/
package org.eclipse.core.databinding.observable.list;

import org.eclipse.core.databinding.observable.IObservablesListener;

/**
* Listener for changes to observable lists.
*
* @param <E>
*            the type of elements in this collection
*
* @since 1.0
*/
@FunctionalInterface
public interface IListChangeListener<E> extends IObservablesListener {

    /**
* Handle a change to an observable list. The change is described by the
* diff object. The given event object must only be used locally in this
* method because it may be reused for other change notifications. The diff
* object referenced by the event is immutable and may be used non-locally.
*
* @param event
*/
    void handleListChange(ListChangeEvent<? extends E> event);
}
