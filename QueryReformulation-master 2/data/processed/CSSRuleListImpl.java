/***/
package org.eclipse.e4.ui.css.core.impl.dom;

import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.css.CSSRule;
import org.w3c.dom.css.CSSRuleList;

public class CSSRuleListImpl implements CSSRuleList {

    private List<CSSRule> ruleList;

    public  CSSRuleListImpl() {
        super();
        this.ruleList = new ArrayList<CSSRule>();
    }

    // W3C CSSRuleList API methods
    @Override
    public int getLength() {
        return ruleList.size();
    }

    @Override
    public CSSRule item(int position) {
        return ruleList.get(position);
    }

    //Additional
    /**
* @throws IndexOutOfBoundsException
*/
    public void add(CSSRule rule) {
        ruleList.add(rule);
    }

    /**
* @throws IndexOutOfBoundsException
*/
    public void remove(int position) {
        ruleList.remove(position);
    }
}
