/***/
package org.eclipse.core.commands;

import org.eclipse.core.commands.common.CommandException;

/**
* Signals that an attempt was made to execute a command that is not enabled.
* <p>
* This class is not intended to be extended by clients.
* </p>
*
* @since 3.2
*/
public final class NotEnabledException extends CommandException {

    /**
* Generated serial version UID for this class.
*
* @since 3.1
*/
    private static final long serialVersionUID = 3257572788998124596L;

    /**
* Creates a new instance of this class with the specified detail message.
*
* @param s
*            the detail message; may be <code>null</code>.
*/
    public  NotEnabledException(final String s) {
        super(s);
    }
}
