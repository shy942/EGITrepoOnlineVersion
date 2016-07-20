/***/
package org.eclipse.jface.viewers;

/**
* Interface to provide tool tip information for a given element.
*
* @see org.eclipse.jface.viewers.CellLabelProvider
*
* @since 3.10
*/
public interface IToolTipProvider {

    /**
* Get the text displayed in the tool tip for object.
*
* @param element
*            the element for which the tool tip is shown
* @return the {@link String} or <code>null</code> if there is not text to
*         display
*/
    public String getToolTipText(Object element);
}
