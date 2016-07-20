/***/
package org.eclipse.core.internal.databinding.observable;

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import org.eclipse.core.databinding.observable.list.DecoratingObservableList;
import org.eclipse.core.databinding.observable.list.IObservableList;

/**
* ObservableList implementation that prevents modification by consumers. Events
* in the originating wrapped list are propagated and thrown from this instance
* when appropriate. All mutators throw an UnsupportedOperationException.
*
* @param <E>
*            the type of the elements in this list
*
* @since 1.0
*/
public class UnmodifiableObservableList<E> extends DecoratingObservableList<E> {

    private List<E> unmodifiableList;

    /**
* @param decorated
*/
    public  UnmodifiableObservableList(IObservableList<E> decorated) {
        super(decorated, false);
        this.unmodifiableList = Collections.unmodifiableList(decorated);
    }

    @Override
    public void add(int index, Object o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean add(E o) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<E> iterator() {
        getterCalled();
        return unmodifiableList.iterator();
    }

    @Override
    public ListIterator<E> listIterator() {
        return listIterator(0);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        getterCalled();
        return unmodifiableList.listIterator(index);
    }

    @Override
    public E move(int oldIndex, int newIndex) {
        throw new UnsupportedOperationException();
    }

    @Override
    public E remove(int index) {
        throw new UnsupportedOperationException();
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
    public E set(int index, E element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        getterCalled();
        return unmodifiableList.subList(fromIndex, toIndex);
    }

    @Override
    public synchronized void dispose() {
        unmodifiableList = null;
        super.dispose();
    }
}
