/***/
package org.eclipse.core.commands;

/**
* <p>
* A parameter for a command. A parameter identifies a type of information that
* the command might accept. For example, a "Show View" command might accept the
* id of a view for display. This parameter also identifies possible values, for
* display in the user interface.
* </p>
*
* @since 3.1
*/
public interface IParameter {

    /**
* Returns the identifier for this parameter.
*
* @return The identifier; never <code>null</code>.
*/
    public String getId();

    /**
* Returns the human-readable name for this parameter.
*
* @return The parameter name; never <code>null</code>.
*/
    public String getName();

    /**
* Returns the values associated with this parameter.
*
* @return The values associated with this parameter. This must not be
*         <code>null</code>.
* @throws ParameterValuesException
*             If the values can't be retrieved for some reason.
*/
    public IParameterValues getValues() throws ParameterValuesException;

    /**
* Returns whether parameter is optional. Otherwise, it is required.
*
* @return <code>true</code> if the parameter is optional;
*         <code>false</code> if it is required.
*/
    public boolean isOptional();
}
