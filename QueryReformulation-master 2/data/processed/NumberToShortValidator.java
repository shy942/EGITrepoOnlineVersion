/***/
package org.eclipse.core.internal.databinding.validation;

import org.eclipse.core.internal.databinding.conversion.NumberToShortConverter;
import org.eclipse.core.internal.databinding.conversion.StringToNumberParser;

/**
* Validates if a Number can fit in a Short.
* <p>
* Class is thread safe.
* </p>
*
* @since 1.0
*/
public class NumberToShortValidator extends NumberToNumberValidator {

    private static final Short MIN = new Short(Short.MIN_VALUE);

    private static final Short MAX = new Short(Short.MAX_VALUE);

    /**
* @param converter
*/
    public  NumberToShortValidator(NumberToShortConverter converter) {
        super(converter, MIN, MAX);
    }

    @Override
    protected boolean inRange(Number number) {
        return StringToNumberParser.inShortRange(number);
    }
}
