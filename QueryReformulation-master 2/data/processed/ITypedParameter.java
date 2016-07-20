/***/
package org.eclipse.core.commands;

/**
* A command parameter that has a declared type. This interface is intended to
* be implemented by implementors of {@link IParameter} that will support
* parameter types.
*
* @since 3.2
*/
public interface ITypedParameter {

    /**
* Returns the {@link ParameterType} associated with a command parameter or
* <code>null</code> if the parameter does not declare a type.
* <p>
* Note that the parameter type returned may be undefined.
* </p>
*
* @return the parameter type associated with a command parameter or
*         <code>null</code> if the parameter does not declare a type
*/
    public ParameterType getParameterType();
}
