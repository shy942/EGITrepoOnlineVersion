/***/
package org.eclipse.jface.viewers;

/**
* Adds efficient element indexing support to ILazyContentProvider.
*
* @since 3.5
*/
public interface IIndexableLazyContentProvider extends ILazyContentProvider {

    /**
* Find the row index of the parameter element in the set of contents provided
* by this object.  Under normal usage, this method will only be used to
* implement <code>StructuredViewer#setSelection(ISelection)</code> more
* efficiently.
*
* @param element the element to find within the contents served here
* @return the zero-based index of the element, or -1 if the element is not found
*/
    public int findElement(Object element);
}
