/***/
package org.eclipse.core.internal.databinding.conversion;

import java.math.BigDecimal;
import java.math.BigInteger;
import com.ibm.icu.text.NumberFormat;

/**
* Converts from a Number to a BigInteger.
* <p>
* Class is thread safe.
* </p>
*
* @since 1.0
*/
public class NumberToBigIntegerConverter extends NumberToNumberConverter {

    /**
* @param numberFormat
* @param fromType
*/
    public  NumberToBigIntegerConverter(NumberFormat numberFormat, Class fromType) {
        super(numberFormat, fromType, BigInteger.class);
    }

    @Override
    protected Number doConvert(Number number) {
        return toBigDecimal(number).toBigInteger();
    }

    private static BigDecimal toBigDecimal(Number number) {
        if (number instanceof BigDecimal) {
            return (BigDecimal) number;
        }
        return new BigDecimal(number.doubleValue());
    }
}
