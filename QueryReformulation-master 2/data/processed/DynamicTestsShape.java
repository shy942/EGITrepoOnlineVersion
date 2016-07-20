/***/
package org.eclipse.ui.tests.views.properties.tabbed.dynamic.model;

/**
* A shape enumeration for the dynamic tests view. (Should use an enum when we
* can use Java 5).
*
* @author Anthony Hunter
*/
public class DynamicTestsShape {

    public static final DynamicTestsShape CIRCLE = new DynamicTestsShape(//$NON-NLS-1$
    "circle");

    public static final DynamicTestsShape SQUARE = new DynamicTestsShape(//$NON-NLS-1$
    "square");

    //$NON-NLS-1$
    public static final DynamicTestsShape STAR = new DynamicTestsShape("star");

    public static final DynamicTestsShape TRIANGLE = new DynamicTestsShape(//$NON-NLS-1$
    "triangle");

    /**
* @return the shape
*/
    public static DynamicTestsShape getShape(String value) {
        if (SQUARE.getShape().equals(value)) {
            return SQUARE;
        } else if (CIRCLE.getShape().equals(value)) {
            return CIRCLE;
        } else if (TRIANGLE.getShape().equals(value)) {
            return TRIANGLE;
        } else if (STAR.getShape().equals(value)) {
            return STAR;
        }
        return null;
    }

    private String shape;

    private  DynamicTestsShape(String aShape) {
        setShape(aShape);
    }

    /**
* @return the shape
*/
    public String getShape() {
        return shape;
    }

    /**
* @param shape
*            the shape to set
*/
    public void setShape(String aShape) {
        this.shape = aShape;
    }

    @Override
    public String toString() {
        return getShape();
    }
}
