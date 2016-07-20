/**/
/* This class copied from org.apache.batik.css.engine.sac */
package org.eclipse.e4.ui.css.core.impl.sac;

import java.util.Set;
import org.w3c.css.sac.Selector;
import org.w3c.css.sac.SimpleSelector;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
* This class provides an implementation for the
* {@link org.w3c.css.sac.DescendantSelector} interface.
*/
public class CSSDirectAdjacentSelectorImpl extends AbstractSiblingSelector {

    /**
* Creates a new CSSDirectAdjacentSelector object.
*/
    public  CSSDirectAdjacentSelectorImpl(short type, Selector parent, SimpleSelector simple) {
        super(type, parent, simple);
    }

    /**
* <b>SAC</b>: Implements {@link
* org.w3c.css.sac.Selector#getSelectorType()}.
*/
    @Override
    public short getSelectorType() {
        return SAC_DIRECT_ADJACENT_SELECTOR;
    }

    /**
* Tests whether this selector matches the given element.
*/
    @Override
    public boolean match(Element e, String pseudoE) {
        Node n = e;
        if (!((ExtendedSelector) getSiblingSelector()).match(e, pseudoE)) {
            return false;
        }
        while ((n = n.getPreviousSibling()) != null && n.getNodeType() != Node.ELEMENT_NODE) {
            ;
        }
        if (n == null) {
            return false;
        }
        return ((ExtendedSelector) getSelector()).match((Element) n, null);
    }

    /**
* Fills the given set with the attribute names found in this selector.
*/
    @Override
    public void fillAttributeSet(Set<String> attrSet) {
        ((ExtendedSelector) getSelector()).fillAttributeSet(attrSet);
        ((ExtendedSelector) getSiblingSelector()).fillAttributeSet(attrSet);
    }

    /**
* Returns a representation of the selector.
*/
    @Override
    public String toString() {
        return getSelector() + " + " + getSiblingSelector();
    }
}
