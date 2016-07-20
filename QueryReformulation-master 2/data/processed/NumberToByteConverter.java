/***/
package org.eclipse.core.internal.databinding.conversion;

import com.ibm.icu.text.NumberFormat;

/**
* Converts from a Number to a Byte.
* <p>
* Class is thread safe.
* </p>
*
* @since 1.0
*/
public class NumberToByteConverter extends NumberToNumberConverter {

    /**
* @param numberFormat
* @param fromType
* @param primitive
*/
    public  NumberToByteConverter(NumberFormat numberFormat, Class fromType, boolean primitive) {
        super(numberFormat, fromType, (primitive) ? Byte.TYPE : Byte.class);
    }

    @Override
    protected Number doConvert(Number number) {
        if (StringToNumberParser.inByteRange(number)) {
            return new Byte(number.byteValue());
        }
        return null;
    }
}
