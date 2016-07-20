/**/
/* This class copied from org.apache.batik.css.engine.sac */
package org.eclipse.e4.ui.css.core.impl.sac;

import org.w3c.dom.Element;

/**
* This class provides an implementation of the
* {@link org.w3c.css.sac.AttributeCondition} interface.
*/
public class CSSBeginHyphenAttributeConditionImpl extends CSSAttributeConditionImpl {

    /**
* Creates a new CSSAttributeCondition object.
*/
    public  CSSBeginHyphenAttributeConditionImpl(String localName, String namespaceURI, boolean specified, String value) {
        super(localName, namespaceURI, specified, value);
    }

    /**
* <b>SAC</b>: Implements {@link
* org.w3c.css.sac.Condition#getConditionType()}.
*/
    @Override
    public short getConditionType() {
        return SAC_BEGIN_HYPHEN_ATTRIBUTE_CONDITION;
    }

    /**
* Tests whether this condition matches the given element.
*/
    @Override
    public boolean match(Element e, String pseudoE) {
        return e.getAttribute(getLocalName()).startsWith(getValue());
    }

    /**
* Returns a text representation of this object.
*/
    @Override
    public String toString() {
        return '[' + getLocalName() + "|=\"" + getValue() + "\"]";
    }
}
