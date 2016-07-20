/**/
/* This class copied from org.apache.batik.css.engine.sac */
package org.eclipse.e4.ui.css.core.impl.sac;

import java.util.Set;
import org.w3c.dom.Element;

/**
* This class provides an implementation of the
* {@link org.w3c.css.sac.AttributeCondition} interface.
*/
public class CSSAttributeConditionImpl extends AbstractAttributeCondition {

    /**
* The attribute's local name.
*/
    protected String localName;

    /**
* The attribute's namespace URI.
*/
    protected String namespaceURI;

    /**
* Whether this condition applies to specified attributes.
*/
    protected boolean specified;

    /**
* Creates a new CSSAttributeCondition object.
*/
    public  CSSAttributeConditionImpl(String localName, String namespaceURI, boolean specified, String value) {
        super(value);
        this.localName = localName;
        this.namespaceURI = namespaceURI;
        this.specified = specified;
    }

    /**
* Indicates whether some other object is "equal to" this one.
*
* @param obj
*            the reference object with which to compare.
*/
    @Override
    public boolean equals(Object obj) {
        if (!super.equals(obj)) {
            return false;
        }
        CSSAttributeConditionImpl c = (CSSAttributeConditionImpl) obj;
        return (c.namespaceURI.equals(namespaceURI) && c.localName.equals(localName) && c.specified == specified);
    }

    /**
* equal objects should have equal hashCodes.
*
* @return hashCode of this CSSAttributeCondition
*/
    @Override
    public int hashCode() {
        return namespaceURI.hashCode() ^ localName.hashCode() ^ (specified ? -1 : 0);
    }

    /**
* <b>SAC</b>: Implements {@link
* org.w3c.css.sac.Condition#getConditionType()}.
*/
    @Override
    public short getConditionType() {
        return SAC_ATTRIBUTE_CONDITION;
    }

    /**
* <b>SAC</b>: Implements {@link
* org.w3c.css.sac.AttributeCondition#getNamespaceURI()}.
*/
    @Override
    public String getNamespaceURI() {
        return namespaceURI;
    }

    /**
* <b>SAC</b>: Implements {@link
* org.w3c.css.sac.AttributeCondition#getLocalName()}.
*/
    @Override
    public String getLocalName() {
        return localName;
    }

    /**
* <b>SAC</b>: Implements {@link
* org.w3c.css.sac.AttributeCondition#getSpecified()}.
*/
    @Override
    public boolean getSpecified() {
        return specified;
    }

    /**
* Tests whether this condition matches the given element.
*/
    @Override
    public boolean match(Element e, String pseudoE) {
        if (!e.hasAttribute(getLocalName())) {
            return false;
        }
        String val = getValue();
        if (val == null) {
            return !e.getAttribute(getLocalName()).equals("");
        }
        return e.getAttribute(getLocalName()).equals(val);
    }

    /**
* Fills the given set with the attribute names found in this selector.
*/
    @Override
    public void fillAttributeSet(Set<String> attrSet) {
        attrSet.add(localName);
    }

    /**
* Returns a text representation of this object.
*/
    @Override
    public String toString() {
        if (value == null) {
            return '[' + localName + ']';
        }
        return '[' + localName + "=\"" + value + "\"]";
    }
}
