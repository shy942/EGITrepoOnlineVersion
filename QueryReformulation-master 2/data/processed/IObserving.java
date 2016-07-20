/***/
package org.eclipse.core.databinding.observable;

/**
*
* Mixin interface for IObservables that observe other objects.
*
* @since 1.0
*
*/
@FunctionalInterface
public interface IObserving {

    /**
* Returns the observed object, or <code>null</code> if this observing
* object does not currently observe an object.
*
* @return the observed object, or <code>null</code>
*/
    public Object getObserved();
}
