/***/
package org.eclipse.e4.ui.css.core.dom.properties;

import org.w3c.dom.css.CSSPrimitiveValue;

/**
* CSS Border properties interface.
*
* @version 1.0.0
* @author <a href="mailto:angelo.zerr@gmail.com">Angelo ZERR</a>
*
*/
public interface CSSBorderProperties {

    /**
* Return border-color value.
*
* @return
*/
    public CSSPrimitiveValue getColor();

    /**
* Set  border-color value.
*
* @return
*/
    public void setColor(CSSPrimitiveValue color);

    /**
* Return border-width value.
*
* @return
*/
    public int getWidth();

    /**
* Set border-width value.
*
* @return
*/
    public void setWidth(int width);

    /**
* Return border-style value.
*
* @return
*/
    public String getStyle();

    /**
* Set border-style value.
*
* @return
*/
    public void setStyle(String style);
}
