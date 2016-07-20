/***/
package org.eclipse.core.commands;

/**
* <p>
* State identifiers that are understood by named handle objects that implement
* {@link IObjectWithState}.
* </p>
* <p>
* Clients may implement or extend this class.
* </p>
*
* @since 3.2
*/
public interface INamedHandleStateIds {

    /**
* The state id used for overriding the description of a named handle
* object. This state's value must return a {@link String}.
*/
    //$NON-NLS-1$
    public static String DESCRIPTION = "DESCRIPTION";

    /**
* The state id used for overriding the name of a named handle object. This
* state's value must return a {@link String}.
*/
    //$NON-NLS-1$
    public static String NAME = "NAME";
}
