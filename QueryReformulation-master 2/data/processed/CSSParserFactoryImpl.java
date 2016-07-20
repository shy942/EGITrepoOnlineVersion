/***/
package org.eclipse.e4.ui.css.core.impl.dom.parsers;

import org.eclipse.e4.ui.css.core.dom.parsers.CSSParser;
import org.eclipse.e4.ui.css.core.dom.parsers.CSSParserFactory;

/**
* {@link CSSParserFactory} implementation.
*
* @version 1.0.0
* @author <a href="mailto:angelo.zerr@gmail.com">Angelo ZERR</a>
*
*/
public class CSSParserFactoryImpl extends CSSParserFactory {

    @Override
    public CSSParser makeCSSParser() {
        return new CSSParserImpl();
    }
}
