/***/
package org.eclipse.ui.tests.views.properties.tabbed.dynamic.model;

/**
* A color enumeration for the dynamic tests view. (Should use an enum when we
* can use Java 5).
*
* @author Anthony Hunter
*/
public class DynamicTestsColor {

    //$NON-NLS-1$
    public static final DynamicTestsColor BLACK = new DynamicTestsColor("black");

    //$NON-NLS-1$
    public static final DynamicTestsColor BLUE = new DynamicTestsColor("blue");

    //$NON-NLS-1$
    public static final DynamicTestsColor GREEN = new DynamicTestsColor("green");

    //$NON-NLS-1$
    public static final DynamicTestsColor RED = new DynamicTestsColor("red");

    /**
* @return the color
*/
    public static DynamicTestsColor getColor(String value) {
        if (RED.getColor().equals(value)) {
            return RED;
        } else if (GREEN.getColor().equals(value)) {
            return GREEN;
        } else if (BLUE.getColor().equals(value)) {
            return BLUE;
        } else if (BLACK.getColor().equals(value)) {
            return BLACK;
        }
        return null;
    }

    private String color;

    private  DynamicTestsColor(String aColor) {
        setColor(aColor);
    }

    /**
* @return the color
*/
    public String getColor() {
        return color;
    }

    /**
* @param color
*            the color to set
*/
    public void setColor(String aColor) {
        this.color = aColor;
    }

    @Override
    public String toString() {
        return getColor();
    }
}
