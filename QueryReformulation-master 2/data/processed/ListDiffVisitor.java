/***/
package org.eclipse.core.databinding.observable.list;

import java.util.List;

/**
* A visitor for processing differences in a ListDiff.
*
* @param <E>
*            the type of the elements in the list
*
* @see ListDiff#accept(ListDiffVisitor)
* @since 1.1
*/
public abstract class ListDiffVisitor<E> {

    /**
* Notifies the visitor that <code>element</code> was added to the list at
* position <code>index</code>.
*
* @param index
*            the index where the element was added
* @param element
*            the element that was added
*/
    public abstract void handleAdd(int index, E element);

    /**
* Notifies the visitor that <code>element</code> was removed from the list
* at position <code>index</code>.
*
* @param index
*            the index where the element was removed
* @param element
*            the element that was removed
*/
    public abstract void handleRemove(int index, E element);

    /**
* Notifies the visitor that <code>element</code> was moved in the list from
* position <code>oldIndex</code> to position <code>newIndex</code>.
* <p>
* The default implementation of this method calls
* {@link #handleRemove(int, Object)} with the old position, then
* {@link #handleAdd(int, Object)} with the new position. Clients which are
* interested in recognizing "moves" in a list (i.e. calls to
* {@link IObservableList#move(int, int)}) should override this method.
*
* @param oldIndex
*            the index that the element was moved from.
* @param newIndex
*            the index that the element was moved to.
* @param element
*            the element that was moved
* @see IObservableList#move(int, int)
*/
    public void handleMove(int oldIndex, int newIndex, E element) {
        handleRemove(oldIndex, element);
        handleAdd(newIndex, element);
    }

    /**
* Notifies the visitor that <code>oldElement</code>, located at position
* <code>index</code> in the list, was replaced by <code>newElement</code>.
* <p>
* The default implementation of this method calls
* {@link #handleRemove(int, Object)} with the old element, then
* {@link #handleAdd(int, Object)} with the new element. Clients which are
* interested in recognizing "replaces" in a list (i.e. calls to
* {@link List#set(int, Object)}) should override this method.
*
* @param index
*            the index where the element was replaced.
* @param oldElement
*            the element being replaced.
* @param newElement
*            the element that replaced oldElement.
* @see List#set(int, Object)
*/
    public void handleReplace(int index, E oldElement, E newElement) {
        handleRemove(index, oldElement);
        handleAdd(index, newElement);
    }
}
