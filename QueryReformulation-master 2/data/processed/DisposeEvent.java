/***/
package org.eclipse.core.databinding.observable;

/**
* Event denoting that an {@link IObservable} object was disposed.
*
* @since 1.2
*/
public class DisposeEvent extends ObservableEvent {

    /**
*
*/
    private static final long serialVersionUID = -3241193109844979384L;

    static final Object TYPE = new Object();

    /**
* Creates a new dispose event object.
*
* @param source
*            the observable that was disposed
*/
    public  DisposeEvent(IObservable source) {
        super(source);
    }

    @Override
    protected void dispatch(IObservablesListener listener) {
        ((IDisposeListener) listener).handleDispose(this);
    }

    @Override
    protected Object getListenerType() {
        return TYPE;
    }
}
