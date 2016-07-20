/***/
package org.eclipse.core.internal.databinding.validation;

import org.eclipse.core.internal.databinding.conversion.StringToNumberParser;

/**
* Validates that a string is of the appropriate format and is in the range of
* an integer.
*
* @since 1.0
*/
public class StringToIntegerValidator extends AbstractStringToNumberValidator {

    private static final Integer MIN = Integer.valueOf(Integer.MIN_VALUE);

    private static final Integer MAX = Integer.valueOf(Integer.MAX_VALUE);

    /**
* @param converter
*/
    public  StringToIntegerValidator(NumberFormatConverter converter) {
        super(converter, MIN, MAX);
    }

    @Override
    protected boolean isInRange(Number number) {
        return StringToNumberParser.inIntegerRange(number);
    }
}
