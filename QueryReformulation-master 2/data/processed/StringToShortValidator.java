/***/
package org.eclipse.core.internal.databinding.validation;

import org.eclipse.core.internal.databinding.conversion.StringToNumberParser;

/**
* @since 1.0
*/
public class StringToShortValidator extends AbstractStringToNumberValidator {

    private static final Short MIN = new Short(Short.MIN_VALUE);

    private static final Short MAX = new Short(Short.MAX_VALUE);

    /**
* @param converter
*/
    public  StringToShortValidator(NumberFormatConverter converter) {
        super(converter, MIN, MAX);
    }

    @Override
    protected boolean isInRange(Number number) {
        return StringToNumberParser.inShortRange(number);
    }
}
