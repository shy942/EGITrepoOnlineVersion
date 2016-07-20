/**/
/* This class copied from org.apache.batik.css.engine.sac */
package org.eclipse.e4.ui.css.core.impl.sac;

import org.eclipse.e4.ui.css.core.dom.CSSStylableElement;
import org.w3c.dom.Element;

/**
* This class provides an implementation of the
* {@link org.w3c.css.sac.AttributeCondition} interface.
*/
public class CSSClassConditionImpl extends CSSAttributeConditionImpl {

    /**
* Creates a new CSSAttributeCondition object.
*/
    public  CSSClassConditionImpl(String localName, String namespaceURI, String value) {
        super(localName, namespaceURI, true, value);
    }

    @Override
    public boolean match(Element e, String pseudoE) {
        String attr = null;
        if ((e instanceof CSSStylableElement))
            attr = ((CSSStylableElement) e).getCSSClass();
        else
            attr = e.getAttribute("class");
        if (attr == null || attr.length() < 1)
            return false;
        String val = getValue();
        int attrLen = attr.length();
        int valLen = val.length();
        for (int i = attr.indexOf(val); i != -1; i = attr.indexOf(val, i + valLen)) if ((i == 0 || Character.isSpaceChar(attr.charAt(i - 1))) && (i + valLen == attrLen || Character.isSpaceChar(attr.charAt(i + valLen))))
            return true;
        return false;
    }
}
