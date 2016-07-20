/**/
/* This class copied from org.apache.batik.css.engine.sac */
package org.eclipse.e4.ui.css.core.impl.sac;

import org.w3c.css.sac.DescendantSelector;
import org.w3c.css.sac.Selector;
import org.w3c.css.sac.SimpleSelector;

/**
* This class provides an abstract implementation of the {@link
* org.w3c.css.sac.DescendantSelector} interface.
*/
public abstract class AbstractDescendantSelector implements DescendantSelector, ExtendedSelector {

    /**
* The ancestor selector.
*/
    protected Selector ancestorSelector;

    /**
* The simple selector.
*/
    protected SimpleSelector simpleSelector;

    /**
* Creates a new DescendantSelector object.
*/
    protected  AbstractDescendantSelector(Selector ancestor, SimpleSelector simple) {
        ancestorSelector = ancestor;
        simpleSelector = simple;
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
        AbstractDescendantSelector s = (AbstractDescendantSelector) obj;
        return s.simpleSelector.equals(simpleSelector);
    }

    /**
* Returns the specificity of this selector.
*/
    @Override
    public int getSpecificity() {
        return ((ExtendedSelector) ancestorSelector).getSpecificity() + ((ExtendedSelector) simpleSelector).getSpecificity();
    }

    /**
* <b>SAC</b>: Implements {@link
* org.w3c.css.sac.DescendantSelector#getAncestorSelector()}.
*/
    @Override
    public Selector getAncestorSelector() {
        return ancestorSelector;
    }

    /**
* <b>SAC</b>: Implements {@link
* org.w3c.css.sac.DescendantSelector#getSimpleSelector()}.
*/
    @Override
    public SimpleSelector getSimpleSelector() {
        return simpleSelector;
    }
}
