/**/
/* This class copied from org.apache.batik.css.engine.sac */
package org.eclipse.e4.ui.css.core.impl.sac;

import java.util.Set;
import org.w3c.css.sac.Condition;
import org.w3c.dom.Element;

/**
* This interface provides additional features to the
* {@link org.w3c.css.sac.Condition} interface.
*/
public interface ExtendedCondition extends Condition {

    /**
* Tests whether this condition matches the given element.
*/
    boolean match(Element e, String pseudoE);

    /**
* Returns the specificity of this condition.
*/
    int getSpecificity();

    /**
* Fills the given set with the attribute names found in this selector.
*/
    void fillAttributeSet(Set<String> attrSet);
}
