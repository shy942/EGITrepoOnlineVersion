/***/
package org.eclipse.core.databinding.observable;

/**
* @since 1.0
*
*/
public abstract class ChangeSupport extends ChangeManager {

    /**
* @param realm
*/
    public  ChangeSupport(Realm realm) {
        super(realm);
    }

    @Override
    public void addListener(Object listenerType, IObservablesListener listener) {
        super.addListener(listenerType, listener);
    }

    @Override
    public void removeListener(Object listenerType, IObservablesListener listener) {
        super.removeListener(listenerType, listener);
    }

    @Override
    public void fireEvent(ObservableEvent event) {
        super.fireEvent(event);
    }

    /**
*
*/
    @Override
    protected abstract void firstListenerAdded();

    /**
*
*/
    @Override
    protected abstract void lastListenerRemoved();

    /**
* @param listener
*/
    public void addChangeListener(IChangeListener listener) {
        addListener(ChangeEvent.TYPE, listener);
    }

    /**
* @param listener
*/
    public void removeChangeListener(IChangeListener listener) {
        removeListener(ChangeEvent.TYPE, listener);
    }

    /**
* @param listener
*/
    public void addStaleListener(IStaleListener listener) {
        addListener(StaleEvent.TYPE, listener);
    }

    /**
* @param listener
*/
    public void removeStaleListener(IStaleListener listener) {
        removeListener(StaleEvent.TYPE, listener);
    }

    /**
* @param listener
* @since 1.2
*/
    public void addDisposeListener(IDisposeListener listener) {
        addListener(DisposeEvent.TYPE, listener);
    }

    /**
* @param listener
* @since 1.2
*/
    public void removeDisposeListener(IDisposeListener listener) {
        removeListener(DisposeEvent.TYPE, listener);
    }
}
