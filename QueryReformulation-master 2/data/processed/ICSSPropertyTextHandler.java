/***/
package org.eclipse.e4.ui.css.core.dom.properties.css2;

import org.eclipse.e4.ui.css.core.dom.properties.ICSSPropertyHandler;
import org.eclipse.e4.ui.css.core.engine.CSSEngine;
import org.w3c.dom.css.CSSValue;

/**
* CSS2 Text Property Handler.
*
* @see http://www.w3schools.com/css/css_reference.asp#text
*/
public interface ICSSPropertyTextHandler extends ICSSPropertyHandler {

    /**
* Sets the color of a text.
*
* @param element
* @param value
* @param pseudo
* @param engine
* @throws Exception
*/
    public void applyCSSPropertyColor(Object element, CSSValue value, String pseudo, CSSEngine engine) throws Exception;

    /**
* Controls the letters in an element. Available values are : none
* capitalize uppercase lowercase
*
* @param element
* @param value
* @param pseudo
* @param engine
* @throws Exception
*/
    public void applyCSSPropertyTextTransform(Object element, CSSValue value, String pseudo, CSSEngine engine) throws Exception;

    public String retrieveCSSPropertyColor(Object element, String pseudo, CSSEngine engine) throws Exception;

    public String retrieveCSSPropertyTextTransform(Object element, String pseudo, CSSEngine engine) throws Exception;
}