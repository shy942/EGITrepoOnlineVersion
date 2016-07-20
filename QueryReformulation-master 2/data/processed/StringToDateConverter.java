/**/
package org.eclipse.core.internal.databinding.conversion;

import java.util.Date;
import org.eclipse.core.databinding.conversion.IConverter;

/**
* Convert a String to a java.util.Date, respecting the current locale
*
* @since 1.0
*/
public class StringToDateConverter extends DateConversionSupport implements IConverter {

    @Override
    public Object convert(Object source) {
        return parse(source.toString());
    }

    @Override
    public Object getFromType() {
        return String.class;
    }

    @Override
    public Object getToType() {
        return Date.class;
    }
}
