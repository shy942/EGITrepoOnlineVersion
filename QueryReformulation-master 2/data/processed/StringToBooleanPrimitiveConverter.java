/**/
package org.eclipse.core.internal.databinding.conversion;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.internal.databinding.BindingMessages;

/**
* StringToBooleanPrimitiveConverter.
*/
public class StringToBooleanPrimitiveConverter implements IConverter {

    private static final String[] trueValues;

    private static final String[] falseValues;

    static {
        String delimiter = BindingMessages.getString(BindingMessages.VALUE_DELIMITER);
        String values = BindingMessages.getString(BindingMessages.TRUE_STRING_VALUES);
        trueValues = valuesToSortedArray(delimiter, values);
        values = BindingMessages.getString(BindingMessages.FALSE_STRING_VALUES);
        falseValues = valuesToSortedArray(delimiter, values);
    }

    /**
* Returns a sorted array with all values converted to upper case.
*
* @param delimiter
* @param values
* @return sorted array of values
*/
    private static String[] valuesToSortedArray(String delimiter, String values) {
        List list = new LinkedList();
        StringTokenizer tokenizer = new StringTokenizer(values, delimiter);
        while (tokenizer.hasMoreTokens()) {
            list.add(tokenizer.nextToken().toUpperCase());
        }
        String[] array = (String[]) list.toArray(new String[list.size()]);
        Arrays.sort(array);
        return array;
    }

    @Override
    public Object convert(Object source) {
        String s = (String) source;
        s = s.toUpperCase();
        if (Arrays.binarySearch(trueValues, s) > -1) {
            return Boolean.TRUE;
        }
        if (Arrays.binarySearch(falseValues, s) > -1) {
            return Boolean.FALSE;
        }
        //$NON-NLS-1$
        throw new IllegalArgumentException(s + " is not a legal boolean value");
    }

    @Override
    public Object getFromType() {
        return String.class;
    }

    @Override
    public Object getToType() {
        return Boolean.TYPE;
    }
}
