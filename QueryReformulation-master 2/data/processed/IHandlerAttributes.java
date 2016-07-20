/***/
package org.eclipse.core.commands;

/**
* <p>
* Attribute constants that have special meanings within this package.  These
* attributes can be used to communicate extra information from the handler to
* either the command or the command manager.
* </p>
*
* @since 3.1
*/
public interface IHandlerAttributes {

    /**
* <p>
* The name of the attribute indicating whether the handler is handled.
* This is intended largely for backward compatibility with the workbench
* <code>RetargetAction</code> class.  It is used to indicate that while
* the handler is handling a command, it should not be treated as such.
* The command should act and behave as if it has no handler.
* </p>
*/
    //$NON-NLS-1$
    public static final String ATTRIBUTE_HANDLED = "handled";
}
