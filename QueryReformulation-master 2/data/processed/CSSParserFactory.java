/***/
package org.eclipse.e4.ui.css.core.dom.parsers;

import org.eclipse.e4.ui.css.core.impl.dom.parsers.CSSParserFactoryImpl;

/**
* CSS Parser factory to manage instance of {@link ICSSParserFactory}.
*/
public abstract class CSSParserFactory implements ICSSParserFactory {

    /**
* Obtain a new instance of a {@link ICSSParserFactory}.
*
* @return
*/
    public static ICSSParserFactory newInstance() {
        return new CSSParserFactoryImpl();
    }
}
