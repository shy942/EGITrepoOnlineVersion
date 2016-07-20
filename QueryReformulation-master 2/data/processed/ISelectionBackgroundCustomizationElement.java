/***/
package org.eclipse.e4.ui.css.swt.dom;

import org.eclipse.swt.graphics.Color;

public interface ISelectionBackgroundCustomizationElement {

    /**
* Sets the selection foreground color.
*/
    public void setSelectionForegroundColor(Color color);

    /**
* Gets the selection foreground color.
*/
    public Color getSelectionForegroundColor();

    /**
* Sets the selection background color.
*/
    public void setSelectionBackgroundColor(Color color);

    /**
* @return the selection background color.
*/
    public Color getSelectionBackgroundColor();

    /**
* Sets the selection border color.
*/
    public void setSelectionBorderColor(Color color);

    /**
* @return the selection border color.
*/
    public Color getSelectionBorderColor();

    /**
* Sets the hot background color (i.e.: hotness is a 'selection preview on
* hover').
*/
    public void setHotBackgroundColor(Color color);

    /**
* @return the hot background color.
*/
    public Color getHotBackgroundColor();

    /**
* Sets the hot border color.
*/
    public void setHotBorderColor(Color color);

    /**
* Gets the hot border color.
*/
    public Color getHotBorderColor();
}
