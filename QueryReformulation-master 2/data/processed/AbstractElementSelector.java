/**/
/* This class copied from org.apache.batik.css.engine.sac */
package org.eclipse.e4.ui.css.core.impl.sac;

import java.util.Set;
import org.w3c.css.sac.ElementSelector;

/**
* This class provides an abstract implementation of the ElementSelector
* interface.
*/
public abstract class AbstractElementSelector implements ElementSelector, ExtendedSelector {

    /**
* The namespace URI.
*/
    protected String namespaceURI;

    /**
* The local name.
*/
    protected String localName;

    /**
* Creates a new ElementSelector object.
*/
    protected  AbstractElementSelector(String uri, String name) {
        namespaceURI = uri;
        localName = name;
    }

    /**
* Indicates whether some other object is "equal to" this one.
* @param obj the reference object with which to compare.
*/
    @Override
    public boolean equals(Object obj) {
        if (obj == null || (obj.getClass() != getClass())) {
            return false;
        }
        AbstractElementSelector s = (AbstractElementSelector) obj;
        return (s.namespaceURI.equals(namespaceURI) && s.localName.equals(localName));
    }

    /**
* <b>SAC</b>: Implements {@link
* org.w3c.css.sac.ElementSelector#getNamespaceURI()}.
*/
    @Override
    public String getNamespaceURI() {
        return namespaceURI;
    }

    /**
* <b>SAC</b>: Implements {@link
* org.w3c.css.sac.ElementSelector#getLocalName()}.
*/
    @Override
    public String getLocalName() {
        return localName;
    }

    /**
* Fills the given set with the attribute names found in this selector.
*/
    @Override
    public void fillAttributeSet(Set<String> attrSet) {
    }
}
