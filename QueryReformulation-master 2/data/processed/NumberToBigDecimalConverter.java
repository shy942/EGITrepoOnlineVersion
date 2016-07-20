/***/
package org.eclipse.core.internal.databinding.conversion;

import java.math.BigDecimal;
import java.math.BigInteger;
import com.ibm.icu.text.NumberFormat;

/**
* Converts from a Number to a BigDecimal.
* <p>
* Class is thread safe.
* </p>
*
* @since 1.0
*/
public class NumberToBigDecimalConverter extends NumberToNumberConverter {

    /**
* @param numberFormat
* @param fromType
*/
    public  NumberToBigDecimalConverter(NumberFormat numberFormat, Class fromType) {
        super(numberFormat, fromType, BigDecimal.class);
    }

    @Override
    protected Number doConvert(Number number) {
        if (number instanceof BigInteger) {
            return new BigDecimal((BigInteger) number);
        }
        return new BigDecimal(number.doubleValue());
    }
}
