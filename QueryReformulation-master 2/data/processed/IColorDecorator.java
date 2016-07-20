/***/
package org.eclipse.jface.viewers;

import org.eclipse.swt.graphics.Color;

/**
* The IColorDecorator is an interface for objects that return a color to
* decorate either the foreground and background colors for displaying an
* an object.
*
* If an IColorDecorator decorates a foreground or background in an object
* that also has an IColorProvider the IColorDecorator will take precedence.
* @see IColorProvider
*
* @since 3.1
*/
public interface IColorDecorator {

    /**
* Return the foreground Color for element or <code>null</code> if there
* is not one.
* @param element
* @return Color or <code>null</code>
*/
    public Color decorateForeground(Object element);

    /**
* Return the background Color for element or <code>null</code> if there
* is not one.
* @param element
* @return Color or <code>null</code>
*/
    public Color decorateBackground(Object element);
}
