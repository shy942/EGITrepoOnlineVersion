/***/
package org.eclipse.jface.viewers;

import org.eclipse.swt.graphics.Font;

/**
* The IFontDecorator is an interface for objects that return a font to
* decorate an object.
*
* If an IFontDecorator decorates a font in an object that also has
* an IFontProvider the IFontDecorator will take precedence.
* @see IFontProvider
*
* @since 3.1
*/
public interface IFontDecorator {

    /**
* Return the font for element or <code>null</code> if there
* is not one.
*
* @param element
* @return Font or <code>null</code>
*/
    public Font decorateFont(Object element);
}
