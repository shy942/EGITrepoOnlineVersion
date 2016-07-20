/***/
package org.eclipse.e4.ui.css.core.engine;

import org.eclipse.e4.ui.css.core.dom.IElementProvider;
import org.w3c.dom.Element;

/**
* Context of the {@link Element} which wrap the native widget (SWT widget,
* Swing Component...).
*
* This context can be used to store any data.
*/
public interface CSSElementContext {

    /**
* Return the {@link Element} which wrap the native widget.
*
* @return
*/
    public Element getElement();

    /**
* Set the {@link Element} which wrap the native widget.
*
* @param newElement
*/
    public void setElement(Element newElement);

    /**
* Set data <code>value</code> into the context with <code>key</code>.
*
* @param key
* @param value
*/
    public void setData(Object key, Object value);

    /**
* Get data with <code>key</code>.
*
* @param key
* @return
*/
    public Object getData(Object key);

    /**
* Return true if element provider has changed and false otherwise.
*
* @param elementProvider
* @return
*/
    public boolean elementMustBeRefreshed(IElementProvider elementProvider);

    /**
* Set {@link IElementProvider} used to get the Element wich wrap the native
* widget.
*
* @param elementProvider
*/
    public void setElementProvider(IElementProvider elementProvider);
}
