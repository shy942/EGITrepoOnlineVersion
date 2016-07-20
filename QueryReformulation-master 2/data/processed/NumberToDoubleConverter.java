/***/
package org.eclipse.core.internal.databinding.conversion;

import com.ibm.icu.text.NumberFormat;

/**
* Converts from a Number to a Double.
* <p>
* Class is thread safe.
* </p>
*
* @since 1.0
*/
public class NumberToDoubleConverter extends NumberToNumberConverter {

    /**
* @param numberFormat
* @param fromType
* @param primitive
*/
    public  NumberToDoubleConverter(NumberFormat numberFormat, Class fromType, boolean primitive) {
        super(numberFormat, fromType, (primitive) ? Double.TYPE : Double.class);
    }

    @Override
    protected Number doConvert(Number number) {
        if (StringToNumberParser.inDoubleRange(number)) {
            return new Double(number.doubleValue());
        }
        return null;
    }
}
