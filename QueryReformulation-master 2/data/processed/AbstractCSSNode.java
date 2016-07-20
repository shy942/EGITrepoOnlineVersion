/***/
package org.eclipse.e4.ui.css.core.impl.dom;

import org.eclipse.e4.ui.css.core.dom.parsers.CSSParser;

/**
* Abstract CSS Node.
*/
public class AbstractCSSNode {

    public  AbstractCSSNode() {
        super();
    }

    public CSSParser getCSSParser() {
        //TODO not sure why this always returns null
        return null;
    }
}
