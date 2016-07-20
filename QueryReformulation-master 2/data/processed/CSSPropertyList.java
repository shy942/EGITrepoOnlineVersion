/***/
package org.eclipse.e4.ui.css.core.dom;

/**
* CSS property list interface.
*
* @version 1.0.0
* @author <a href="mailto:angelo.zerr@gmail.com">Angelo ZERR</a>
*
*/
public interface CSSPropertyList {

    /**
* Return length of CSS property list.
*
* @return
*/
    public abstract int getLength();

    /**
* Return the CSS property {@link CSSProperty} at item <code>i</code>.
*
* @param i
* @return
*/
    public abstract CSSProperty item(int i);
}
