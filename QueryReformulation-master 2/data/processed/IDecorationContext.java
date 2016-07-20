/***/
package org.eclipse.jface.viewers;

/**
* A decoration context provides additional information to
* a label decorator.
* <p>
* This interface is not intended to be implemented by clients
*
* @see LabelDecorator
*
* @since 3.2
*/
public interface IDecorationContext {

    /**
* Get the value of the given property or <code>null</code>
* if the property does not exist in this context.
* @param property the property
* @return the value of the given property or <code>null</code>
*/
    Object getProperty(String property);

    /**
* Return the properties that exist in this context
* (i.e. the set of properties that have values associated
* with them.
* @return the properties that exist in this context
*/
    String[] getProperties();
}
