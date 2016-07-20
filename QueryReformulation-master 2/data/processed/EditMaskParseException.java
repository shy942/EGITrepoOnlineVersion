/***/
package org.eclipse.jface.examples.databinding.mask;

/**
* Indicates a parse error while parsing an edit mask.
*
* @since 3.3
*/
public class EditMaskParseException extends RuntimeException {

    private static final long serialVersionUID = 8142999683999681500L;

    /**
* Construct a MaskParseException
*/
    public  EditMaskParseException() {
        super();
    }

    /**
* @param message
* @param cause
*/
    public  EditMaskParseException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
* @param message
*/
    public  EditMaskParseException(String message) {
        super(message);
    }

    /**
* @param cause
*/
    public  EditMaskParseException(Throwable cause) {
        super(cause);
    }
}
