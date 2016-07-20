/***/
package org.eclipse.core.internal.databinding.conversion;

import com.ibm.icu.text.NumberFormat;

/**
* Converts from a Number to a Float.
* <p>
* Class is thread safe.
* </p>
* @since 1.0
*/
public class NumberToFloatConverter extends NumberToNumberConverter {

    /**
* @param numberFormat
* @param fromType
* @param primitive
*/
    public  NumberToFloatConverter(NumberFormat numberFormat, Class fromType, boolean primitive) {
        super(numberFormat, fromType, (primitive) ? Float.TYPE : Float.class);
    }

    @Override
    protected Number doConvert(Number number) {
        if (StringToNumberParser.inFloatRange(number)) {
            return new Float(number.floatValue());
        }
        return null;
    }
}
