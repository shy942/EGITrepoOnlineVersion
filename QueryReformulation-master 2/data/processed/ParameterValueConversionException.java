/***/
package org.eclipse.core.commands;

import org.eclipse.core.commands.common.CommandException;

/**
* Signals that a problem occurred while converting a command parameter value
* from string to object, or object to string.
*
* @see AbstractParameterValueConverter
* @since 3.2
*/
public class ParameterValueConversionException extends CommandException {

    /**
* Generated serial version UID for this class.
*/
    private static final long serialVersionUID = 4703077729505066104L;

    /**
* Creates a new instance of this class with the specified detail message.
*
* @param message
*            the detail message; may be <code>null</code>.
*/
    public  ParameterValueConversionException(final String message) {
        super(message);
    }

    /**
* Creates a new instance of this class with the specified detail message
* and cause.
*
* @param message
*            the detail message; may be <code>null</code>.
* @param cause
*            the cause; may be <code>null</code>.
*/
    public  ParameterValueConversionException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
