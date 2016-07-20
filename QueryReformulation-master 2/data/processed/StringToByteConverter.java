/**/
package org.eclipse.core.internal.databinding.conversion;

import org.eclipse.core.internal.databinding.conversion.StringToNumberParser.ParseResult;
import org.eclipse.core.internal.databinding.validation.NumberFormatConverter;
import com.ibm.icu.text.NumberFormat;

/**
* @since 1.0
*/
public class StringToByteConverter extends NumberFormatConverter {

    private String outOfRangeMessage;

    private NumberFormat numberFormat;

    private boolean primitive;

    /**
* @param numberFormat
* @param toType
*/
    private  StringToByteConverter(NumberFormat numberFormat, Class toType) {
        super(String.class, toType, numberFormat);
        primitive = toType.isPrimitive();
        this.numberFormat = numberFormat;
    }

    /**
* @param numberFormat
* @param primitive
* @return converter
*/
    public static StringToByteConverter toByte(NumberFormat numberFormat, boolean primitive) {
        return new StringToByteConverter(numberFormat, (primitive) ? Byte.TYPE : Byte.class);
    }

    /**
* @param primitive
* @return converter
*/
    public static StringToByteConverter toByte(boolean primitive) {
        return toByte(NumberFormat.getIntegerInstance(), primitive);
    }

    @Override
    public Object convert(Object fromObject) {
        ParseResult result = StringToNumberParser.parse(fromObject, numberFormat, primitive);
        if (result.getPosition() != null) {
            // formatted message in an exception
            throw new IllegalArgumentException(StringToNumberParser.createParseErrorMessage((String) fromObject, result.getPosition()));
        } else if (result.getNumber() == null) {
            // type and null should be returned
            return null;
        }
        if (StringToNumberParser.inByteRange(result.getNumber())) {
            return new Byte(result.getNumber().byteValue());
        }
        synchronized (this) {
            if (outOfRangeMessage == null) {
                outOfRangeMessage = StringToNumberParser.createOutOfRangeMessage(new Byte(Byte.MIN_VALUE), new Byte(Byte.MAX_VALUE), numberFormat);
            }
            throw new IllegalArgumentException(outOfRangeMessage);
        }
    }
}
