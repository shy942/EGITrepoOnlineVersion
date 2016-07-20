/***/
package org.eclipse.e4.ui.css.core.impl.dom;

import org.w3c.css.sac.LexicalUnit;
import org.w3c.dom.css.CSSPrimitiveValue;
import org.w3c.dom.css.CSSValue;

public class CSSValueFactory {

    public static CSSValue newValue(LexicalUnit value) {
        //if there are more lexical units then it's a list
        if (value.getNextLexicalUnit() != null)
            return new CSSValueListImpl(value);
        return newPrimitiveValue(value);
    }

    public static CSSPrimitiveValue newPrimitiveValue(LexicalUnit value) {
        if (value.getLexicalUnitType() == LexicalUnit.SAC_RGBCOLOR) {
            // RGBColor
            return new RGBColorImpl(value);
        }
        //TODO add cases for Rect, Counter
        return new Measure(value);
    }
}
