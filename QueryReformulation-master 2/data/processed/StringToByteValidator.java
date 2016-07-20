/***/
package org.eclipse.core.internal.databinding.validation;

import org.eclipse.core.internal.databinding.conversion.StringToNumberParser;

/**
* @since 1.0
*/
public class StringToByteValidator extends AbstractStringToNumberValidator {

    private static final Byte MIN = new Byte(Byte.MIN_VALUE);

    private static final Byte MAX = new Byte(Byte.MAX_VALUE);

    /**
* @param converter
*/
    public  StringToByteValidator(NumberFormatConverter converter) {
        super(converter, MIN, MAX);
    }

    @Override
    protected boolean isInRange(Number number) {
        return StringToNumberParser.inByteRange(number);
    }
}
