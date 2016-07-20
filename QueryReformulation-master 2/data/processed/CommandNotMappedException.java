/***/
package org.eclipse.ui.actions;

import org.eclipse.core.commands.common.CommandException;

/**
* Indicates that an action has no command mapping. The declaration can be
* updated to include a definitionId.
*
* @since 3.3
*/
public class CommandNotMappedException extends CommandException {

    private static final long serialVersionUID = 1L;

    /**
* @param message
*/
    public  CommandNotMappedException(String message) {
        super(message);
    }

    /**
* @param message
* @param cause
*/
    public  CommandNotMappedException(String message, Throwable cause) {
        super(message, cause);
    }
}
