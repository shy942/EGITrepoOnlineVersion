/***/
package org.eclipse.core.internal.databinding.validation;

import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.internal.databinding.BindingMessages;
import org.eclipse.core.internal.databinding.conversion.StringToCharacterConverter;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
* Validates a String to Character conversion.
*/
public class StringToCharacterValidator implements IValidator {

    private final StringToCharacterConverter converter;

    /**
* @param converter
*/
    public  StringToCharacterValidator(StringToCharacterConverter converter) {
        this.converter = converter;
    }

    @Override
    public IStatus validate(Object value) {
        try {
            converter.convert(value);
        } catch (IllegalArgumentException e) {
            return ValidationStatus.error(BindingMessages.getString(BindingMessages.VALIDATE_CHARACTER_HELP));
        }
        return Status.OK_STATUS;
    }
}
