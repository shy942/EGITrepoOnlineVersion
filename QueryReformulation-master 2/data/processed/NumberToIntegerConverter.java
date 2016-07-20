/***/
package org.eclipse.core.internal.databinding.conversion;

import org.eclipse.core.databinding.conversion.IConverter;
import com.ibm.icu.text.NumberFormat;

/**
* Converts from a Number to a Integer.
* <p>
* Class is thread safe.
* </p>
* @since 1.0
*/
public class NumberToIntegerConverter extends NumberToNumberConverter implements IConverter {

    /**
* @param numberFormat
* @param fromType
* @param primitive
*/
    public  NumberToIntegerConverter(NumberFormat numberFormat, Class fromType, boolean primitive) {
        super(numberFormat, fromType, (primitive) ? Integer.TYPE : Integer.class);
    }

    @Override
    protected Number doConvert(Number number) {
        if (StringToNumberParser.inIntegerRange(number)) {
            return Integer.valueOf(number.intValue());
        }
        return null;
    }
}
