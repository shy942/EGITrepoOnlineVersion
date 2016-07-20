/***/
package org.eclipse.core.commands;

import org.eclipse.core.commands.common.CommandException;

/**
* Signals that an exception occured while serializing a
* {@link ParameterizedCommand} to a string or deserializing a String to a
* {@link ParameterizedCommand}.
* <p>
* This class is not intended to be extended by clients.
* </p>
*
* @since 3.2
*/
public final class SerializationException extends CommandException {

    /**
* Generated serial version UID for this class.
*/
    private static final long serialVersionUID = 2691599674561684949L;

    /**
* Creates a new instance of this class with the specified detail message.
*
* @param message
*            the detail message; may be <code>null</code>.
*/
    public  SerializationException(final String message) {
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
    public  SerializationException(final String message, final Throwable cause) {
        super(message, cause);
    }
}
