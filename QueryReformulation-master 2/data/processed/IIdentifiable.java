/***/
package org.eclipse.core.commands.common;

/**
* <p>
* An object that is unique identifiable based on the combination of its class
* and its identifier.
* </p>
*
* @see HandleObject
* @since 3.2
*/
public interface IIdentifiable {

    /**
* Returns the identifier for this object.
*
* @return The identifier; never <code>null</code>.
*/
    String getId();
}
