/**/
package org.eclipse.core.internal.databinding.conversion;

import java.util.Date;
import org.eclipse.core.databinding.conversion.IConverter;

/**
* Converts a Java.util.Date to a String using the current locale.  Null date
* values are converted to an empty string.
*
* @since 1.0
*/
public class DateToStringConverter extends DateConversionSupport implements IConverter {

    @Override
    public Object convert(Object source) {
        if (source != null)
            return format((Date) source);
        //$NON-NLS-1$
        return "";
    }

    @Override
    public Object getFromType() {
        return Date.class;
    }

    @Override
    public Object getToType() {
        return String.class;
    }
}
