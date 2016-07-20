/***/
package org.eclipse.core.databinding.observable;

/**
* Listener for dispose events. An observable object is disposed if its
* {@link IObservable#dispose()} method has been called.
*
* @since 1.2
*/
@FunctionalInterface
public interface IDisposeListener extends IObservablesListener {

    /**
* Handle the event that the given observable object has been disposed. The
* given event object must only be used locally in this method because it
* may be reused for other dispose notifications.
*
* @param event
*/
    public void handleDispose(DisposeEvent event);
}
