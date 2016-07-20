/**/
/* This class copied from org.apache.batik.css.engine.sac */
package org.eclipse.e4.ui.css.core.impl.sac;

import java.util.Set;
import org.w3c.css.sac.Selector;
import org.w3c.dom.Element;

/**
* This interface extends the {@link org.w3c.css.sac.Selector}.
*/
public interface ExtendedSelector extends Selector {

    /**
* Tests whether this selector matches the given element.
*/
    boolean match(Element e, String pseudoE);

    /**
* Returns the specificity of this selector.
*/
    int getSpecificity();

    /**
* Fills the given set with the attribute names found in this selector.
*/
    void fillAttributeSet(Set<String> attrSet);
}
