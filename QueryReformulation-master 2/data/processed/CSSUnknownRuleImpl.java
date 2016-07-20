/***/
package org.eclipse.e4.ui.css.core.impl.dom;

import org.w3c.dom.css.CSSRule;
import org.w3c.dom.css.CSSStyleSheet;
import org.w3c.dom.css.CSSUnknownRule;

public class CSSUnknownRuleImpl extends CSSRuleImpl implements CSSUnknownRule {

    public  CSSUnknownRuleImpl(CSSStyleSheet parentStyleSheet, CSSRule parentRule, String atRule) {
        super(parentStyleSheet, parentRule);
    // TODO Auto-generated constructor stub
    }

    @Override
    public short getType() {
        return CSSRule.UNKNOWN_RULE;
    }
}
