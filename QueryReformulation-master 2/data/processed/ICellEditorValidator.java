/***/
package org.eclipse.jface.viewers;

/**
* An interface for validating a cell editor's input.
* <p>
* This interface should be implemented by classes that wish to
* act as cell editor validators.
* </p>
*/
public interface ICellEditorValidator {

    /**
* Returns a string indicating whether the given value is valid;
* <code>null</code> means valid, and non-<code>null</code> means
* invalid, with the result being the error message to display
* to the end user.
* <p>
* It is the responsibility of the implementor to fully format the
* message before returning it.
* </p>
*
* @param value the value to be validated
* @return the error message, or <code>null</code> indicating
*	that the value is valid
*/
    public String isValid(Object value);
}
