/***/
package org.eclipse.core.internal.databinding.conversion;

import org.eclipse.core.databinding.conversion.Converter;
import com.ibm.icu.text.NumberFormat;

/**
* Converts a value that is an integer, non decimal, to a String using a
* NumberFormat.
* <p>
* This class is a temporary as this ability exists in NumberToStringConverter
* except that short and byte are missing.
* </p>
*
* @since 1.0
*/
public class IntegerToStringConverter extends Converter {

    private final boolean primitive;

    private final NumberFormat numberFormat;

    private final Class boxedType;

    /**
* @param numberFormat
* @param fromType
* @param boxedType
*/
    private  IntegerToStringConverter(NumberFormat numberFormat, Class fromType, Class boxedType) {
        super(fromType, String.class);
        this.primitive = fromType.isPrimitive();
        this.numberFormat = numberFormat;
        this.boxedType = boxedType;
    }

    @Override
    public Object convert(Object fromObject) {
        // Null is allowed when the type is not primitve.
        if (fromObject == null && !primitive) {
            //$NON-NLS-1$
            return "";
        }
        if (!boxedType.isInstance(fromObject)) {
            throw new IllegalArgumentException(//$NON-NLS-1$//$NON-NLS-2$
            "'fromObject' is not of type [" + boxedType + "].");
        }
        return numberFormat.format(((Number) fromObject).longValue());
    }

    /**
* @param primitive
* @return converter
*/
    public static IntegerToStringConverter fromShort(boolean primitive) {
        return fromShort(NumberFormat.getIntegerInstance(), primitive);
    }

    /**
* @param numberFormat
* @param primitive
* @return converter
*/
    public static IntegerToStringConverter fromShort(NumberFormat numberFormat, boolean primitive) {
        return new IntegerToStringConverter(numberFormat, primitive ? Short.TYPE : Short.class, Short.class);
    }

    /**
* @param primitive
* @return converter
*/
    public static IntegerToStringConverter fromByte(boolean primitive) {
        return fromByte(NumberFormat.getIntegerInstance(), primitive);
    }

    /**
* @param numberFormat
* @param primitive
* @return converter
*/
    public static IntegerToStringConverter fromByte(NumberFormat numberFormat, boolean primitive) {
        return new IntegerToStringConverter(numberFormat, primitive ? Byte.TYPE : Byte.class, Byte.class);
    }
}
