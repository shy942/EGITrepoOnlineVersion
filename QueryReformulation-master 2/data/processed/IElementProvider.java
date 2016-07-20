/***/
package org.eclipse.e4.ui.css.core.dom;

import org.eclipse.e4.ui.css.core.engine.CSSEngine;
import org.w3c.dom.Element;

/**
* Element provider to retrieve w3c {@link Element} which wrap the native widget
* (SWT Control, Swing JComponent...).
*/
public interface IElementProvider {

    /**
* Return the w3c Element which wrap the native widget <code>element</code>
* (SWT Control, Swing JComponent). The <code>element</code> can be the
* w3c Element.
*
* @param element
* @param engine
*
* @return
*/
    public Element getElement(Object element, CSSEngine engine);
}
