/**/
package org.eclipse.core.internal.databinding.conversion;

import org.eclipse.core.databinding.conversion.IConverter;

/**
* Converts any object to a string by calling its toString() method.
*/
public class ObjectToStringConverter implements IConverter {

    private final Class fromClass;

    /**
*
*/
    public  ObjectToStringConverter() {
        this(Object.class);
    }

    /**
* @param fromClass
*/
    public  ObjectToStringConverter(Class fromClass) {
        this.fromClass = fromClass;
    }

    @Override
    public Object convert(Object source) {
        if (source == null) {
            //$NON-NLS-1$
            return "";
        }
        return source.toString();
    }

    @Override
    public Object getFromType() {
        return fromClass;
    }

    @Override
    public Object getToType() {
        return String.class;
    }
}
