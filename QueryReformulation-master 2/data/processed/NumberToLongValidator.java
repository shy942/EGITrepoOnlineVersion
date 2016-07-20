/***/
package org.eclipse.core.internal.databinding.validation;

import org.eclipse.core.internal.databinding.conversion.NumberToLongConverter;
import org.eclipse.core.internal.databinding.conversion.StringToNumberParser;

/**
* Validates if a Number can fit in a Long.
* <p>
* Class is thread safe.
* </p>
* @since 1.0
*/
public class NumberToLongValidator extends NumberToNumberValidator {

    private static final Long MIN = new Long(Long.MIN_VALUE);

    private static final Long MAX = new Long(Long.MAX_VALUE);

    /**
* @param converter
*/
    public  NumberToLongValidator(NumberToLongConverter converter) {
        super(converter, MIN, MAX);
    }

    @Override
    protected boolean inRange(Number number) {
        return StringToNumberParser.inLongRange(number);
    }
}
