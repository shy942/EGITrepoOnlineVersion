/***/
package org.eclipse.e4.ui.css.core.impl.dom;

import org.w3c.dom.css.CSSFontFaceRule;
import org.w3c.dom.css.CSSRule;
import org.w3c.dom.css.CSSStyleDeclaration;
import org.w3c.dom.css.CSSStyleSheet;

public class CSSFontFaceRuleImpl extends CSSRuleImpl implements CSSFontFaceRule {

    public  CSSFontFaceRuleImpl(CSSStyleSheet parentStyleSheet, CSSRule parentRule) {
        super(parentStyleSheet, parentRule);
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("NOT YET IMPLEMENTED");
    }

    /**
* @see org.w3c.dom.css.CSSRule.getType()
*/
    @Override
    public short getType() {
        return CSSRule.FONT_FACE_RULE;
    }

    // W3C CSSFontFaceRule API methods
    /**
* @see org.w3c.dom.css.CSSFontFaceRule.getStyle()
*/
    @Override
    public CSSStyleDeclaration getStyle() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("NOT YET IMPLEMENTED");
    }

    // Additional methods
    public void setStyle(CSSStyleDeclarationImpl decl) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("NOT YET IMPLEMENTED");
    }
}
