/***/
package org.eclipse.core.internal.databinding.conversion;

import com.ibm.icu.text.NumberFormat;

/**
* Converts from a Number to a Long.
* <p>
* Class is thread safe.
* </p>
* @since 1.0
*/
public class NumberToLongConverter extends NumberToNumberConverter {

    /**
* @param numberFormat
* @param fromType
* @param primitive
*/
    public  NumberToLongConverter(NumberFormat numberFormat, Class fromType, boolean primitive) {
        super(numberFormat, fromType, (primitive) ? Long.TYPE : Long.class);
    }

    @Override
    protected Number doConvert(Number number) {
        if (StringToNumberParser.inLongRange(number)) {
            return new Long(number.longValue());
        }
        return null;
    }
}
