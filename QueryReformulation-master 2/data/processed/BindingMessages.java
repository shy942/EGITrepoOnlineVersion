/***/
package org.eclipse.core.internal.databinding;

import java.util.MissingResourceException;
import java.util.ResourceBundle;
import com.ibm.icu.text.MessageFormat;

/**
* @since 1.0
*
*/
public class BindingMessages {

    /**
* The Binding resource bundle; eagerly initialized.
*/
    private static final ResourceBundle bundle = ResourceBundle.getBundle(//$NON-NLS-1$
    "org.eclipse.core.internal.databinding.messages");

    /**
* Key to be used for an index out of range message.
*/
    //$NON-NLS-1$
    public static final String INDEX_OUT_OF_RANGE = "IndexOutOfRange";

    /**
* Key to be used for a "Multiple Problems." message.
*/
    //$NON-NLS-1$
    public static final String MULTIPLE_PROBLEMS = "MultipleProblems";

    /**
* Key to be used for a "ValueBinding_ErrorWhileSettingValue" message
*/
    //$NON-NLS-1$
    public static final String VALUEBINDING_ERROR_WHILE_SETTING_VALUE = "ValueBinding_ErrorWhileSettingValue";

    /**
* Key to be used for a "DateFormat_DateTime" message
*/
    //$NON-NLS-1$
    public static final String DATE_FORMAT_DATE_TIME = "DateFormat_DateTime";

    /**
* Key to be used for a "DateFormat_Time" message
*/
    //$NON-NLS-1$
    public static final String DATEFORMAT_TIME = "DateFormat_Time";

    /**
* Key to be used for a "ValueDelimiter" message
*/
    //$NON-NLS-1$
    public static final String VALUE_DELIMITER = "ValueDelimiter";

    /**
* Key to be used for a "TrueStringValues" message
*/
    //$NON-NLS-1$
    public static final String TRUE_STRING_VALUES = "TrueStringValues";

    /**
* Key to be used for a "FalseStringValues" message
*/
    //$NON-NLS-1$
    public static final String FALSE_STRING_VALUES = "FalseStringValues";

    /**
* Key to be used for a "Validate_NumberOutOfRangeError" message
*/
    //$NON-NLS-1$
    public static final String VALIDATE_NUMBER_OUT_OF_RANGE_ERROR = "Validate_NumberOutOfRangeError";

    /**
* Key to be used for a "Validate_NumberParseError" message
*/
    //$NON-NLS-1$
    public static final String VALIDATE_NUMBER_PARSE_ERROR = "Validate_NumberParseError";

    /**
* Key to be used for a "Validate_ConversionToPrimitive" message
*/
    //$NON-NLS-1$
    public static final String VALIDATE_CONVERSION_TO_PRIMITIVE = "Validate_ConversionToPrimitive";

    /**
* Key to be used for a "Validate_ConversionFromClassToPrimitive" message
*/
    //$NON-NLS-1$
    public static final String VALIDATE_CONVERSION_FROM_CLASS_TO_PRIMITIVE = "Validate_ConversionFromClassToPrimitive";

    /**
* Key to be used for a "Validate_NoChangeAllowedHelp" message
*/
    //$NON-NLS-1$
    public static final String VALIDATE_NO_CHANGE_ALLOWED_HELP = "Validate_NoChangeAllowedHelp";

    /**
* Key to be used for a "Validate_CharacterHelp" message
*/
    //$NON-NLS-1$
    public static final String VALIDATE_CHARACTER_HELP = "Validate_CharacterHelp";

    /**
* Key to be used for a "Examples" message
*/
    //$NON-NLS-1$
    public static final String EXAMPLES = "Examples";

    /**
* Key to be used for a "Validate_NumberParseErrorNoCharacter" message
*/
    //$NON-NLS-1$
    public static final String VALIDATE_NUMBER_PARSE_ERROR_NO_CHARACTER = "Validate_NumberParseErrorNoCharacter";

    /**
* Returns the resource object with the given key in the resource bundle for
* JFace Data Binding. If there isn't any value under the given key, the key
* is returned.
*
* @param key
*            the resource name
* @return the string
*/
    public static String getString(String key) {
        try {
            return bundle.getString(key);
        } catch (MissingResourceException e) {
            return key;
        }
    }

    /**
* Returns a formatted string with the given key in the resource bundle for
* JFace Data Binding.
*
* @param key
* @param arguments
* @return formatted string, the key if the key is invalid
*/
    public static String formatString(String key, Object[] arguments) {
        try {
            return MessageFormat.format(bundle.getString(key), arguments);
        } catch (MissingResourceException e) {
            return key;
        }
    }
}
