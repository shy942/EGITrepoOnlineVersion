/**/
package org.eclipse.core.internal.databinding.validation;

import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.internal.databinding.BindingMessages;
import org.eclipse.core.runtime.IStatus;

/**
* ReadOnlyValidator. A validator that can be used as a partial validator for read-only fields.
*/
public class ReadOnlyValidator implements IValidator {

    private static ReadOnlyValidator singleton = null;

    /**
* Returns the ReadOnlyValidator
*
* @return the ReadOnlyValidator
*/
    public static ReadOnlyValidator getDefault() {
        if (singleton == null) {
            singleton = new ReadOnlyValidator();
        }
        return singleton;
    }

    @Override
    public IStatus validate(Object value) {
        // No changes are allowed
        return ValidationStatus.error(BindingMessages.getString(BindingMessages.VALIDATE_NO_CHANGE_ALLOWED_HELP));
    }
}
