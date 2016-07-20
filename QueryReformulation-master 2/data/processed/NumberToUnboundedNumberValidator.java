/***/
package org.eclipse.core.internal.databinding.validation;

import org.eclipse.core.internal.databinding.conversion.NumberToNumberConverter;

/**
* Validates if a Number can fit in an unbounded number (e.g. BigInteger, BigDecimal, etc.).
* <p>
* Class is thread safe.
* </p>
*
* @since 1.0
*/
public class NumberToUnboundedNumberValidator extends NumberToNumberValidator {

    /**
* @param converter
*/
    public  NumberToUnboundedNumberValidator(NumberToNumberConverter converter) {
        super(converter, null, null);
    }

    @Override
    protected boolean inRange(Number number) {
        return true;
    }
}
