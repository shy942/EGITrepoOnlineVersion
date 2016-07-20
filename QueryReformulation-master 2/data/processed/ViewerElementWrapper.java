/***/
package org.eclipse.jface.internal.databinding.viewers;

import org.eclipse.jface.viewers.IElementComparer;

/**
* A wrapper class for viewer elements, which uses an {@link IElementComparer}
* for computing {@link Object#equals(Object) equality} and
* {@link Object#hashCode() hashes}.
*
* @since 1.2
*/
public class ViewerElementWrapper {

    private final Object element;

    private final IElementComparer comparer;

    /**
* Constructs a ViewerElementWrapper wrapping the given element
*
* @param element
*            the element being wrapped
* @param comparer
*            the comparer to use for computing equality and hash codes.
*/
    public  ViewerElementWrapper(Object element, IElementComparer comparer) {
        if (comparer == null)
            throw new NullPointerException();
        this.element = element;
        this.comparer = comparer;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ViewerElementWrapper)) {
            return false;
        }
        return comparer.equals(element, ((ViewerElementWrapper) obj).element);
    }

    @Override
    public int hashCode() {
        return comparer.hashCode(element);
    }

    Object unwrap() {
        return element;
    }
}
