/***/
package org.eclipse.core.databinding.observable.set;

import org.eclipse.core.databinding.observable.DecoratingObservableCollection;
import org.eclipse.core.databinding.observable.Diffs;

/**
* An observable set which decorates another observable set.
*
* @param <E>
*            the type of the elements in this set
*
* @since 1.2
*/
public class DecoratingObservableSet<E> extends DecoratingObservableCollection<E> implements IObservableSet<E> {

    private IObservableSet<E> decorated;

    private ISetChangeListener<E> setChangeListener;

    /**
* Constructs a DecoratingObservableSet which decorates the given
* observable.
*
* @param decorated
*            the observable set being decorated
* @param disposeDecoratedOnDispose
*/
    public  DecoratingObservableSet(IObservableSet<E> decorated, boolean disposeDecoratedOnDispose) {
        super(decorated, disposeDecoratedOnDispose);
        this.decorated = decorated;
    }

    @Override
    public void clear() {
        getterCalled();
        decorated.clear();
    }

    @Override
    public synchronized void addSetChangeListener(ISetChangeListener<? super E> listener) {
        addListener(SetChangeEvent.TYPE, listener);
    }

    @Override
    public synchronized void removeSetChangeListener(ISetChangeListener<? super E> listener) {
        removeListener(SetChangeEvent.TYPE, listener);
    }

    protected void fireSetChange(SetDiff<E> diff) {
        // fire general change event first
        super.fireChange();
        fireEvent(new SetChangeEvent(this, diff));
    }

    @Override
    protected void fireChange() {
        throw new RuntimeException(//$NON-NLS-1$
        "fireChange should not be called, use fireSetChange() instead");
    }

    @Override
    protected void firstListenerAdded() {
        if (setChangeListener == null) {
            setChangeListener = new ISetChangeListener<E>() {

                @Override
                public void handleSetChange(SetChangeEvent<? extends E> event) {
                    DecoratingObservableSet.this.handleSetChange(event);
                }
            };
        }
        decorated.addSetChangeListener(setChangeListener);
        super.firstListenerAdded();
    }

    @Override
    protected void lastListenerRemoved() {
        super.lastListenerRemoved();
        if (setChangeListener != null) {
            decorated.removeSetChangeListener(setChangeListener);
            setChangeListener = null;
        }
    }

    /**
* Called whenever a SetChangeEvent is received from the decorated
* observable. By default, this method fires the set change event again,
* with the decorating observable as the event source. Subclasses may
* override to provide different behavior.
*
* @param event
*            the change event received from the decorated observable
*/
    protected void handleSetChange(final SetChangeEvent<? extends E> event) {
        fireSetChange(Diffs.unmodifiableDiff(event.diff));
    }

    @Override
    public synchronized void dispose() {
        if (decorated != null && setChangeListener != null) {
            decorated.removeSetChangeListener(setChangeListener);
        }
        decorated = null;
        setChangeListener = null;
        super.dispose();
    }
}
