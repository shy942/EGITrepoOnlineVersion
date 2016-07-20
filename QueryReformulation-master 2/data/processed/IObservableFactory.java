/***/
package org.eclipse.core.databinding.observable.masterdetail;

import org.eclipse.core.databinding.observable.IObservable;

/**
* Generates an {@link IObservable} when passed a target instance.
*
* @param <T>
*            type of the target
* @param <E>
*            type of the observable constructed by this factory; this type must
*            extend or implement IObservable
*
* @since 1.0
*/
@FunctionalInterface
public interface IObservableFactory<T, E extends IObservable> {

    /**
* Creates an observable for the given target object.
*
* @param target
* @return the new observable
*/
    public E createObservable(T target);
}
