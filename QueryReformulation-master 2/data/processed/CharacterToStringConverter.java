/***/
package org.eclipse.core.internal.databinding.conversion;

import org.eclipse.core.databinding.conversion.Converter;

/**
* Converts a character to a string.
*/
public class CharacterToStringConverter extends Converter {

    private final boolean primitive;

    /**
* @param primitive
*/
    private  CharacterToStringConverter(boolean primitive) {
        super(primitive ? Character.TYPE : Character.class, String.class);
        this.primitive = primitive;
    }

    @Override
    public Object convert(Object fromObject) {
        // Null is allowed when the type is not primitive.
        if (fromObject == null) {
            if (primitive)
                throw new IllegalArgumentException(//$NON-NLS-1$
                "'fromObject' is null. Cannot convert to primitive char.");
            //$NON-NLS-1$
            return "";
        }
        if (!(fromObject instanceof Character)) {
            throw new IllegalArgumentException(//$NON-NLS-1$
            "'fromObject' is not of type [Character].");
        }
        return String.valueOf(((Character) fromObject).charValue());
    }

    /**
* @param primitive
* @return converter
*/
    public static CharacterToStringConverter fromCharacter(boolean primitive) {
        return new CharacterToStringConverter(primitive);
    }
}
