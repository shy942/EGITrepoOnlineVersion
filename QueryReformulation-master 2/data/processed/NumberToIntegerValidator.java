/***/
package org.eclipse.core.internal.databinding.validation;

import org.eclipse.core.internal.databinding.conversion.NumberToIntegerConverter;
import org.eclipse.core.internal.databinding.conversion.StringToNumberParser;

/**
* Validates if a Number can fit in a Integer.
* <p>
* Class is thread safe.
* </p>
* @since 1.0
*/
public class NumberToIntegerValidator extends NumberToNumberValidator {

    private static final Integer MIN = Integer.valueOf(Integer.MIN_VALUE);

    private static final Integer MAX = Integer.valueOf(Integer.MAX_VALUE);

    /**
* @param converter
*/
    public  NumberToIntegerValidator(NumberToIntegerConverter converter) {
        super(converter, MIN, MAX);
    }

    @Override
    protected boolean inRange(Number number) {
        return StringToNumberParser.inIntegerRange(number);
    }
}
