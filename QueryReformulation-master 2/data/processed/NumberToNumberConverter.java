/***/
package org.eclipse.core.internal.databinding.conversion;

import org.eclipse.core.databinding.conversion.Converter;
import com.ibm.icu.text.NumberFormat;

/**
* Base class for number to number converters.
* <p>
* This class is thread safe.
* </p>
*
* @since 1.0
*/
public abstract class NumberToNumberConverter extends Converter {

    private NumberFormat numberFormat;

    private boolean primitive;

    private String outOfRangeMessage;

    protected  NumberToNumberConverter(NumberFormat numberFormat, Class fromType, Class toType) {
        super(fromType, toType);
        this.numberFormat = numberFormat;
        this.primitive = toType.isPrimitive();
    }

    @Override
    public final Object convert(Object fromObject) {
        if (fromObject == null) {
            if (primitive) {
                throw new IllegalArgumentException(//$NON-NLS-1$
                "Parameter 'fromObject' cannot be null.");
            }
            return null;
        }
        if (!(fromObject instanceof Number)) {
            throw new IllegalArgumentException(//$NON-NLS-1$
            "Parameter 'fromObject' must be of type Number.");
        }
        Number number = (Number) fromObject;
        Number result = doConvert(number);
        if (result != null) {
            return result;
        }
        synchronized (this) {
            if (outOfRangeMessage == null) {
                outOfRangeMessage = StringToNumberParser.createOutOfRangeMessage(new Short(Short.MIN_VALUE), new Short(Short.MAX_VALUE), numberFormat);
            }
            throw new IllegalArgumentException(outOfRangeMessage);
        }
    }

    /**
* Invoked when the number should converted.
*
* @param number
* @return number if conversion was successfule, <code>null</code> if the
*         number was out of range
*/
    protected abstract Number doConvert(Number number);

    /**
* NumberFormat being used by the converter. Access to the format must be
* synchronized on the number format instance.
*
* @return number format
*/
    public NumberFormat getNumberFormat() {
        return numberFormat;
    }
}
