/***/
package org.eclipse.jface.viewers;

import org.eclipse.swt.graphics.Color;

/**
* Interface to provide color representation for a given element.
* @see org.eclipse.jface.viewers.IColorDecorator
*/
public interface IColorProvider {

    /**
* Provides a foreground color for the given element.
*
* @param element the element
* @return	the foreground color for the element, or <code>null</code>
*   to use the default foreground color
*/
    Color getForeground(Object element);

    /**
* Provides a background color for the given element.
*
* @param element the element
* @return	the background color for the element, or <code>null</code>
*   to use the default background color
*/
    Color getBackground(Object element);
}
