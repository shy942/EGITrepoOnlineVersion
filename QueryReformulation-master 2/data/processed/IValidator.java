/***/
package org.eclipse.core.databinding.validation;

import org.eclipse.core.runtime.IStatus;

/**
* A validator. This validator is responsible for determining if a given value
* is valid. Validators can be used on target or model values. For example, a
* String2IntValidator would only accept source Strings that can successfully be
* converted to an integer value, and a PositiveIntegerValidator would only
* accept positive integers.
*
* @since 1.0
*
*/
@FunctionalInterface
public interface IValidator {

    /**
* Determines if the given value is valid.
*
* @param value
*            the value to validate
* @return a status object indicating whether the validation succeeded
*         {@link IStatus#isOK()} or not. Never null.
*/
    public IStatus validate(Object value);
}
