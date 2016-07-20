/***/
package org.eclipse.core.internal.databinding.conversion;

import org.eclipse.core.internal.databinding.conversion.StringToNumberParser.ParseResult;
import org.eclipse.core.internal.databinding.validation.NumberFormatConverter;
import com.ibm.icu.text.NumberFormat;

/**
* @since 1.0
*/
public class StringToShortConverter extends NumberFormatConverter {

    private final NumberFormat numberFormat;

    private final boolean primitive;

    private String outOfRangeMessage;

    /**
* Constructs a new instance.
*/
    private  StringToShortConverter(NumberFormat numberFormat, Class toType) {
        super(String.class, toType, numberFormat);
        this.numberFormat = numberFormat;
        primitive = toType.isPrimitive();
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
        if (StringToNumberParser.inShortRange(result.getNumber())) {
            return new Short(result.getNumber().shortValue());
        }
        synchronized (this) {
            if (outOfRangeMessage == null) {
                outOfRangeMessage = StringToNumberParser.createOutOfRangeMessage(new Short(Short.MIN_VALUE), new Short(Short.MAX_VALUE), numberFormat);
            }
            throw new IllegalArgumentException(outOfRangeMessage);
        }
    }

    /**
* @param primitive
*            <code>true</code> if the convert to type is a short
* @return to Short converter for the default locale
*/
    public static StringToShortConverter toShort(boolean primitive) {
        return toShort(NumberFormat.getIntegerInstance(), primitive);
    }

    /**
* @param numberFormat
* @param primitive
* @return to Short converter with the provided numberFormat
*/
    public static StringToShortConverter toShort(NumberFormat numberFormat, boolean primitive) {
        return new StringToShortConverter(numberFormat, (primitive) ? Short.TYPE : Short.class);
    }
}
