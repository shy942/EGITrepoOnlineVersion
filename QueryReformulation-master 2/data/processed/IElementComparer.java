/***/
package org.eclipse.jface.viewers;

/**
* This interface is used to compare elements in a viewer for equality,
* and to provide the hash code for an element.
* This allows the client of the viewer to specify different equality criteria
* and a different hash code implementation than the
* <code>equals</code> and <code>hashCode</code> implementations of the
* elements themselves.
*
* @see StructuredViewer#setComparer
*/
public interface IElementComparer {

    /**
* Compares two elements for equality
*
* @param a the first element
* @param b the second element
* @return whether a is equal to b
*/
    boolean equals(Object a, Object b);

    /**
* Returns the hash code for the given element.
* @param element the element the hash code is calculated for
*
* @return the hash code for the given element
*/
    int hashCode(Object element);
}
