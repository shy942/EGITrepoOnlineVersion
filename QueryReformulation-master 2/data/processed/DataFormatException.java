/***/
package org.eclipse.jface.resource;

/**
* An exception indicating that a string value could not be
* converted into the requested data type.
*
* @see StringConverter
*/
public class DataFormatException extends IllegalArgumentException {

    /**
* Generated serial version UID for this class.
* @since 3.1
*/
    private static final long serialVersionUID = 3544955467404031538L;

    /**
* Creates a new exception.
*/
    public  DataFormatException() {
        super();
    }

    /**
* Creates a new exception.
*
* @param message the message
*/
    public  DataFormatException(String message) {
        super(message);
    }
}
