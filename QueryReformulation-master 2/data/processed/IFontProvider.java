/***/
package org.eclipse.jface.viewers;

import org.eclipse.swt.graphics.Font;

/**
* Interface to provide font representation for a given element.
* @see org.eclipse.jface.viewers.IFontDecorator
*
* @since 3.0
*/
public interface IFontProvider {

    /**
* Provides a font for the given element.
*
* @param element the element
* @return the font for the element, or <code>null</code>
*   to use the default font
*/
    public Font getFont(Object element);
}
