/***/
package org.eclipse.e4.ui.css.core.dom.parsers;

/**
* CSS Parser factory to manage instance of {@link CSSParser}.
*/
public interface ICSSParserFactory {

    /**
* Return instance of {@link CSSParser}.
*
* @return
*/
    public CSSParser makeCSSParser();
}
