/***/
package org.eclipse.e4.ui.css.core.exceptions;

/**
* Exception used when CSS property handler is not retrieved.
*
* @version 1.0.0
* @author <a href="mailto:angelo.zerr@gmail.com">Angelo ZERR</a>
*
*/
public class UnsupportedPropertyException extends Exception {

    private static final long serialVersionUID = 1L;

    private String property;

    public  UnsupportedPropertyException(String property) {
        if (property == null)
            throw new IllegalArgumentException();
        this.property = property;
    }

    @Override
    public String getMessage() {
        return "CSS Property " + property + " is not supported.";
    }
}
