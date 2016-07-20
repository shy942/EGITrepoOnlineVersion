/***/
package org.eclipse.core.internal.databinding.validation;

import org.eclipse.core.internal.databinding.conversion.NumberToFloatConverter;
import org.eclipse.core.internal.databinding.conversion.StringToNumberParser;

/**
* Validates if a Number can fit in a Float.
* <p>
* Class is thread safe.
* </p>
* @since 1.0
*/
public class NumberToFloatValidator extends NumberToNumberValidator {

    private static final Float MIN = new Float(Float.MIN_VALUE);

    private static final Float MAX = new Float(Float.MAX_VALUE);

    /**
* @param converter
*/
    public  NumberToFloatValidator(NumberToFloatConverter converter) {
        super(converter, MIN, MAX);
    }

    @Override
    protected boolean inRange(Number number) {
        return StringToNumberParser.inFloatRange(number);
    }
}
