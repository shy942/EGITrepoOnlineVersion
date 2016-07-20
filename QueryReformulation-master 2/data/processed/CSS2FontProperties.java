/***/
package org.eclipse.e4.ui.css.core.dom.properties.css2;

import org.w3c.dom.css.CSSPrimitiveValue;
import org.w3c.dom.css.CSSValue;

public interface CSS2FontProperties extends CSSValue {

    public CSSPrimitiveValue getFamily();

    public void setFamily(CSSPrimitiveValue family);

    public CSSPrimitiveValue getSize();

    public void setSize(CSSPrimitiveValue size);

    public CSSPrimitiveValue getSizeAdjust();

    public void setSizeAdjust(CSSPrimitiveValue sizeAdjust);

    public CSSPrimitiveValue getWeight();

    public void setWeight(CSSPrimitiveValue weight);

    public CSSPrimitiveValue getStyle();

    public void setStyle(CSSPrimitiveValue style);

    public CSSPrimitiveValue getVariant();

    public void setVariant(CSSPrimitiveValue variant);

    public CSSPrimitiveValue getStretch();

    public void setStretch(CSSPrimitiveValue stretch);
}
