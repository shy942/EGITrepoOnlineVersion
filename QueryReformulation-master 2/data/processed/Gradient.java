/***/
package org.eclipse.e4.ui.css.core.dom.properties;

import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.css.CSSPrimitiveValue;

/**
* Generic class to store informations to manage Gradient color.
*
*/
public class Gradient {

    private final List<Object> rgbs = new ArrayList();

    private final List<Integer> percents = new ArrayList();

    //TODO see bug #278077
    private final List<CSSPrimitiveValue> values = new ArrayList();

    private boolean isLinear = true;

    private boolean vertical = true;

    /* TODO: enhance Gradient with focus points */
    public void setLinear(boolean linear) {
        isLinear = linear;
    }

    public boolean isLinear() {
        return isLinear;
    }

    public boolean isRadial() {
        return !isLinear;
    }

    //TODO see bug #278077
    public void addRGB(Object rgb, CSSPrimitiveValue value) {
        rgbs.add(rgb);
        values.add(value);
    }

    public void addPercent(Integer percent) {
        percents.add(percent);
    }

    public void setVertical(boolean vertical) {
        this.vertical = vertical;
    }

    public List<Object> getRGBs() {
        return rgbs;
    }

    public List<CSSPrimitiveValue> getValues() {
        return values;
    }

    public List<Integer> getPercents() {
        return percents;
    }

    public boolean getVerticalGradient() {
        return vertical;
    }
}
