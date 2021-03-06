/***/
package org.eclipse.jface.viewers;

import org.eclipse.swt.graphics.Color;

/**
* Interface to provide color representation for a given cell within
* the row for an element in a table.
* @since 3.1
*/
public interface ITableColorProvider {

    /**
* Provides a foreground color for the given element.
*
* @param element the element
* @param columnIndex the zero-based index of the column in which
* 	the color appears
* @return the foreground color for the element, or <code>null</code> to
*         use the default foreground color
*/
    Color getForeground(Object element, int columnIndex);

    /**
* Provides a background color for the given element at the specified index
*
* @param element the element
* @param columnIndex the zero-based index of the column in which the color appears
* @return the background color for the element, or <code>null</code> to
*         use the default background color
*
*/
    Color getBackground(Object element, int columnIndex);
}
