/***/
package org.eclipse.core.databinding.observable;

import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.AssertionFailedException;

/**
* @since 1.0
*/
public abstract class AbstractObservable extends ChangeManager implements IObservable {

    private boolean disposed = false;

    /**
* @param realm
*/
    public  AbstractObservable(Realm realm) {
        super(realm);
        ObservableTracker.observableCreated(this);
    }

    @Override
    public synchronized void addChangeListener(IChangeListener listener) {
        addListener(ChangeEvent.TYPE, listener);
    }

    @Override
    public synchronized void removeChangeListener(IChangeListener listener) {
        removeListener(ChangeEvent.TYPE, listener);
    }

    @Override
    public synchronized void addStaleListener(IStaleListener listener) {
        addListener(StaleEvent.TYPE, listener);
    }

    @Override
    public synchronized void removeStaleListener(IStaleListener listener) {
        removeListener(StaleEvent.TYPE, listener);
    }

    /**
* @since 1.2
*/
    @Override
    public synchronized void addDisposeListener(IDisposeListener listener) {
        addListener(DisposeEvent.TYPE, listener);
    }

    /**
* @since 1.2
*/
    @Override
    public synchronized void removeDisposeListener(IDisposeListener listener) {
        removeListener(DisposeEvent.TYPE, listener);
    }

    protected void fireChange() {
        checkRealm();
        fireEvent(new ChangeEvent(this));
    }

    protected void fireStale() {
        checkRealm();
        fireEvent(new StaleEvent(this));
    }

    /**
* @since 1.2
*/
    @Override
    public synchronized boolean isDisposed() {
        return disposed;
    }

    /**
*
*/
    @Override
    public synchronized void dispose() {
        if (!disposed) {
            disposed = true;
            fireEvent(new DisposeEvent(this));
            super.dispose();
        }
    }

    /**
* Asserts that the realm is the current realm.
*
* @see Realm#isCurrent()
* @throws AssertionFailedException if the realm is not the current realm
*/
    protected void checkRealm() {
        Assert.isTrue(getRealm().isCurrent(), //$NON-NLS-1$
        "This operation must be run within the observable's realm");
    }
}
