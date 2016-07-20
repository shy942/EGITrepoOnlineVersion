/***/
package org.eclipse.core.databinding.observable.list;

/**
* A single addition of an element to a list or removal of an element from a
* list.
*
* @param <E>
*            the type of the elements in this diff entry
*
* @since 1.0
*/
public abstract class ListDiffEntry<E> {

    /**
* @return the 0-based position of the addition or removal
*/
    public abstract int getPosition();

    /**
* @return true if this represents an addition, false if this represents a
*         removal
*/
    public abstract boolean isAddition();

    /**
* @return the element that was added or removed
*/
    public abstract E getElement();

    /**
* @see java.lang.Object#toString()
*/
    @Override
    public String toString() {
        StringBuffer buffer = new StringBuffer();
        buffer.append(this.getClass().getName()).append(//$NON-NLS-1$
        "{position [").append(getPosition()).append(//$NON-NLS-1$
        "], isAddition [").append(isAddition()).append(//$NON-NLS-1$
        "], element [").append(//$NON-NLS-1$
        getElement() != null ? getElement().toString() : "null").append(//$NON-NLS-1$
        "]}");
        return buffer.toString();
    }
}
