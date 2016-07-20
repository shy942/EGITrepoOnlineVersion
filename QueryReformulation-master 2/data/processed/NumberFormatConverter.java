/***/
package org.eclipse.core.internal.databinding.validation;

import org.eclipse.core.databinding.conversion.Converter;
import com.ibm.icu.text.NumberFormat;

/**
* Converter that uses a number format for conversion.
*
* @since 1.0
*/
public abstract class NumberFormatConverter extends Converter {

    private final NumberFormat numberFormat;

    /**
* @param fromType
* @param toType
* @param numberFormat
*/
    public  NumberFormatConverter(Object fromType, Object toType, NumberFormat numberFormat) {
        super(fromType, toType);
        this.numberFormat = numberFormat;
    }

    /**
* @return number format
*/
    /*package */
    NumberFormat getNumberFormat() {
        return numberFormat;
    }
}
