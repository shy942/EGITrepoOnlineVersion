/**/
package org.eclipse.core.internal.databinding.conversion;

import org.eclipse.core.databinding.conversion.IConverter;

/**
* StringToCharacterConverter.
*/
public class StringToCharacterConverter implements IConverter {

    private final boolean primitiveTarget;

    /**
*
* @param primitiveTarget
*/
    public  StringToCharacterConverter(boolean primitiveTarget) {
        this.primitiveTarget = primitiveTarget;
    }

    @Override
    public Object convert(Object source) {
        if (source != null && !(source instanceof String))
            throw new IllegalArgumentException(//$NON-NLS-1$ //$NON-NLS-2$
            "String2Character: Expected type String, got type [" + source.getClass().getName() + "]");
        String s = (String) source;
        if (source == null || s.equals("")) {
            //$NON-NLS-1$
            if (primitiveTarget)
                throw new IllegalArgumentException(//$NON-NLS-1$
                "String2Character: cannot convert null/empty string to character primitive");
            return null;
        }
        Character result;
        if (s.length() > 1)
            throw new IllegalArgumentException(//$NON-NLS-1$
            "String2Character: string too long: " + s);
        try {
            result = new Character(s.charAt(0));
        } catch (Exception e) {
            throw new IllegalArgumentException("String2Character: " + e.getMessage() + ": " + s);
        }
        return result;
    }

    @Override
    public Object getFromType() {
        return String.class;
    }

    @Override
    public Object getToType() {
        return primitiveTarget ? Character.TYPE : Character.class;
    }

    /**
* @param primitive
* @return converter
*/
    public static StringToCharacterConverter toCharacter(boolean primitive) {
        return new StringToCharacterConverter(primitive);
    }
}
