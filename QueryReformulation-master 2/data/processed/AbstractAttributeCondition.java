/**/
/* This class copied from org.apache.batik.css.engine.sac */
package org.eclipse.e4.ui.css.core.impl.sac;

import org.w3c.css.sac.AttributeCondition;

/**
* This class provides an abstract implementation of the {@link
* org.w3c.css.sac.AttributeCondition} interface.
*/
public abstract class AbstractAttributeCondition implements AttributeCondition, ExtendedCondition {

    /**
* The attribute value.
*/
    protected String value;

    /**
* Creates a new AbstractAttributeCondition object.
*/
    protected  AbstractAttributeCondition(String value) {
        this.value = value;
    }

    /**
* Indicates whether some other object is "equal to" this one.
*
* @param obj
*            the reference object with which to compare.
*/
    @Override
    public boolean equals(Object obj) {
        if (obj == null || (obj.getClass() != getClass())) {
            return false;
        }
        AbstractAttributeCondition c = (AbstractAttributeCondition) obj;
        return c.value.equals(value);
    }

    /**
* equal objects should have equal hashCodes.
*
* @return hashCode of this AbstractAttributeCondition
*/
    @Override
    public int hashCode() {
        return value == null ? -1 : value.hashCode();
    }

    /**
* Returns the specificity of this condition.
*/
    @Override
    public int getSpecificity() {
        return 1 << 8;
    }

    /**
* <b>SAC</b>: Implements {@link
* org.w3c.css.sac.AttributeCondition#getValue()}.
*/
    @Override
    public String getValue() {
        return value;
    }
}
