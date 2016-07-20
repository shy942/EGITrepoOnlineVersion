/***/
package org.eclipse.e4.ui.css.core.impl.dom;

import org.w3c.dom.DOMException;
import org.w3c.dom.css.CSSPageRule;
import org.w3c.dom.css.CSSRule;
import org.w3c.dom.css.CSSStyleDeclaration;
import org.w3c.dom.css.CSSStyleSheet;

public class CSSPageRuleImpl extends CSSRuleImpl implements CSSPageRule {

    public  CSSPageRuleImpl(CSSStyleSheet parentStyleSheet, CSSRule parentRule, String name, String pseudo_page) {
        super(parentStyleSheet, parentRule);
    // TODO Auto-generated constructor stub
    }

    @Override
    public short getType() {
        return CSSRule.PAGE_RULE;
    }

    // W3C CSSPageRule API methods
    @Override
    public String getSelectorText() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("NOT YET IMPLEMENTED");
    }

    @Override
    public CSSStyleDeclaration getStyle() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("NOT YET IMPLEMENTED");
    }

    @Override
    public void setSelectorText(String arg0) throws DOMException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("NOT YET IMPLEMENTED");
    }

    // Additional methods
    public void setStyle(CSSStyleDeclarationImpl decl) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("NOT YET IMPLEMENTED");
    }
}