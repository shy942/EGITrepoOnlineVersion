/**/
/* This class copied from org.apache.batik.css.engine.sac */
package org.eclipse.e4.ui.css.core.impl.sac;

import org.w3c.css.sac.Selector;
import org.w3c.css.sac.SiblingSelector;
import org.w3c.css.sac.SimpleSelector;

/**
* This class provides an abstract implementation of the {@link
* org.w3c.css.sac.SiblingSelector} interface.
*/
public abstract class AbstractSiblingSelector implements SiblingSelector, ExtendedSelector {

    /**
* The node type.
*/
    protected short nodeType;

    /**
* The selector.
*/
    protected Selector selector;

    /**
* The simple selector.
*/
    protected SimpleSelector simpleSelector;

    /**
* Creates a new SiblingSelector object.
*/
    protected  AbstractSiblingSelector(short type, Selector sel, SimpleSelector simple) {
        nodeType = type;
        selector = sel;
        simpleSelector = simple;
    }

    /**
* Returns the node type.
*/
    @Override
    public short getNodeType() {
        return nodeType;
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
        AbstractSiblingSelector s = (AbstractSiblingSelector) obj;
        return s.simpleSelector.equals(simpleSelector);
    }

    /**
* Returns the specificity of this selector.
*/
    @Override
    public int getSpecificity() {
        return ((ExtendedSelector) selector).getSpecificity() + ((ExtendedSelector) simpleSelector).getSpecificity();
    }

    /**
* <b>SAC</b>: Implements {@link
* org.w3c.css.sac.SiblingSelector#getSelector()}.
*/
    @Override
    public Selector getSelector() {
        return selector;
    }

    /**
* <b>SAC</b>: Implements {@link
* org.w3c.css.sac.SiblingSelector#getSiblingSelector()}.
*/
    @Override
    public SimpleSelector getSiblingSelector() {
        return simpleSelector;
    }
}
