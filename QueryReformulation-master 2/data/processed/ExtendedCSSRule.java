/***/
package org.eclipse.e4.ui.css.core.dom;

import org.w3c.css.sac.Selector;
import org.w3c.css.sac.SelectorList;
import org.w3c.dom.css.CSSRule;

/**
* Extend {@link CSSRule} to get selector and property list.
*/
public interface ExtendedCSSRule extends CSSRule {

    /**
* Return the list of {@link CSSProperty} of this {@link CSSRule}.
*
* @return
*/
    public CSSPropertyList getCSSPropertyList();

    /**
* Return the list of {@link Selector} of this {@link CSSRule}.
*
* @return
*/
    public SelectorList getSelectorList();
}
