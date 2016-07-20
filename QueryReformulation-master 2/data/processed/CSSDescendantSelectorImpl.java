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
public class CSSDescendantSelectorImpl extends AbstractDescendantSelector {

    /**
* Creates a new CSSDescendantSelector object.
*/
    public  CSSDescendantSelectorImpl(Selector ancestor, SimpleSelector simple) {
        super(ancestor, simple);
    }

    /**
* <b>SAC</b>: Implements {@link
* org.w3c.css.sac.Selector#getSelectorType()}.
*/
    @Override
    public short getSelectorType() {
        return SAC_DESCENDANT_SELECTOR;
    }

    /**
* Tests whether this selector matches the given element.
*/
    @Override
    public boolean match(Element e, String pseudoE) {
        ExtendedSelector p = (ExtendedSelector) getAncestorSelector();
        if (!((ExtendedSelector) getSimpleSelector()).match(e, pseudoE)) {
            return false;
        }
        for (Node n = e.getParentNode(); n != null; n = n.getParentNode()) {
            if (n.getNodeType() == Node.ELEMENT_NODE && p.match((Element) n, null)) {
                return true;
            }
        }
        return false;
    }

    /**
* Fills the given set with the attribute names found in this selector.
*/
    @Override
    public void fillAttributeSet(Set<String> attrSet) {
        ((ExtendedSelector) getSimpleSelector()).fillAttributeSet(attrSet);
    }

    /**
* Returns a representation of the selector.
*/
    @Override
    public String toString() {
        return getAncestorSelector() + " " + getSimpleSelector();
    }
}
