/***/
package org.eclipse.core.databinding.property;

import org.eclipse.core.databinding.observable.IDiff;

/**
* Listener for changes to properties on a particular source object
*
* @param <D>
*            type of the diff handled by this listener
* @param <S>
*            type of the source object
* @noextend This interface is not intended to be extended by clients.
* @noimplement This interface is not intended to be implemented by clients.
* @since 1.2
*/
@FunctionalInterface
public interface ISimplePropertyListener<S, D extends IDiff> {

    /**
* Handle the described property event.
*
* @param event
*            the event which occurred
*/
    public void handleEvent(SimplePropertyEvent<S, D> event);
}
