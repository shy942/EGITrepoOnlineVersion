/***/
package org.eclipse.core.internal.databinding.observable;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import org.eclipse.core.databinding.observable.set.DecoratingObservableSet;
import org.eclipse.core.databinding.observable.set.IObservableSet;

/**
* ObservableList implementation that prevents modification by consumers. Events
* in the originating wrapped list are propagated and thrown from this instance
* when appropriate. All mutators throw an UnsupportedOperationException.
*
* @param <E>
*            the type of the elements in this set
*
* @since 1.1
*/
public class UnmodifiableObservableSet<E> extends DecoratingObservableSet<E> {

    private Set<E> unmodifiableSet;

    /**
* @param decorated
*/
    public  UnmodifiableObservableSet(IObservableSet<E> decorated) {
        super(decorated, false);
        this.unmodifiableSet = Collections.unmodifiableSet(decorated);
    }

    @Override
    public boolean add(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<E> iterator() {
        getterCalled();
        return unmodifiableSet.iterator();
    }

    @Override
    public boolean remove(Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public synchronized void dispose() {
        unmodifiableSet = null;
        super.dispose();
    }
}
