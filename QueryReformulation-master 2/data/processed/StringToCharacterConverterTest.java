/***/
package org.eclipse.core.tests.internal.databinding.conversion;

import junit.framework.TestCase;
import org.eclipse.core.internal.databinding.conversion.StringToCharacterConverter;

/**
* @since 1.1
*/
public class StringToCharacterConverterTest extends TestCase {

    private StringToCharacterConverter converter;

    private StringToCharacterConverter primitiveConverter;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        converter = StringToCharacterConverter.toCharacter(false);
        primitiveConverter = StringToCharacterConverter.toCharacter(true);
    }

    public void testConvertsToCharacter() throws Exception {
        Character value = new Character('X');
        Character result = (Character) converter.convert(Character.toString(value.charValue()));
        assertEquals(value, result);
    }

    public void testConvertsToCharacterPrimitive() throws Exception {
        Character value = new Character('Y');
        Character result = (Character) primitiveConverter.convert(String.valueOf(value.charValue()));
        assertEquals(value, result);
    }

    public void testFromTypeIsString() throws Exception {
        assertEquals(String.class, converter.getFromType());
    }

    public void testToTypeIsCharacter() throws Exception {
        assertEquals(Character.class, converter.getToType());
    }

    public void testToTypeIsCharacterPrimitive() throws Exception {
        assertEquals(Character.TYPE, primitiveConverter.getToType());
    }

    public void testReturnsNullBoxedTypeForEmptyString() throws Exception {
        assertNull(converter.convert(""));
    }

    public void testNullCharacterIsOK() throws Exception {
        assertNull(converter.convert(null));
    }

    public void testNullCharacterIsNotOKForPrimitive() throws Exception {
        try {
            primitiveConverter.convert(null);
            fail("exception should have been thrown");
        } catch (IllegalArgumentException e) {
        }
    }

    public void testThrowsIllegalArgumentExceptionIfAskedToConvertNonString() throws Exception {
        try {
            converter.convert(Integer.valueOf(1));
            fail("exception should have been thrown");
        } catch (IllegalArgumentException e) {
        }
    }
}
