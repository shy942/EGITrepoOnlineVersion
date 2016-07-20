/***/
package org.eclipse.e4.ui.css.core.dom;

import org.w3c.dom.css.CSSValue;

/**
* CSS property interface.
*
* @version 1.0.0
* @author <a href="mailto:angelo.zerr@gmail.com">Angelo ZERR</a>
*
*/
public interface CSSProperty {

    /**
* Return name of CSS property.
*
* @return
*/
    public String getName();

    /**
* Return {@link CSSValue} value of CSS property.
*
* @return
*/
    public CSSValue getValue();

    /**
* Set the {@link CSSValue} value of CSS property.
*
* @param value
*/
    public void setValue(CSSValue value);

    /**
* Return true if CSS property is important and false otherwise.
*
* @return
*/
    public boolean isImportant();

    public void setImportant(boolean important);
}
