/***/
package org.eclipse.ui.model;

import org.eclipse.jface.viewers.StyledString;

/**
* Extension interface for <code>IWorkbenchAdapter</code> that allows for
* StyledString support.
*
* @see IWorkbenchAdapter
* @see WorkbenchLabelProvider
* @see BaseWorkbenchContentProvider
* @see org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider
*
* @since 3.7
*/
public interface IWorkbenchAdapter3 {

    /**
* Returns the styled text label for the given element.
*
* @param element
*            the element to evaluate the styled string for.
*
* @return the styled string.
*/
    public StyledString getStyledText(Object element);
}
