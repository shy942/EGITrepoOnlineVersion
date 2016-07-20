/***/
package org.eclipse.core.internal.databinding.validation;

import org.eclipse.core.internal.databinding.conversion.StringToNumberParser;

/**
* Validates that a string is of the appropriate format and is in the range of a
* float.
*
* @since 1.0
*/
public class StringToFloatValidator extends AbstractStringToNumberValidator {

    private static final Float MIN = new Float(-Float.MAX_VALUE);

    private static final Float MAX = new Float(Float.MAX_VALUE);

    /**
* @param converter
*/
    public  StringToFloatValidator(NumberFormatConverter converter) {
        super(converter, MIN, MAX);
    }

    @Override
    protected boolean isInRange(Number number) {
        return StringToNumberParser.inFloatRange(number);
    }
}
