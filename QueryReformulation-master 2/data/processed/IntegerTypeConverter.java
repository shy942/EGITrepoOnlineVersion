/***/
package org.eclipse.ui.examples.contributions.editor;

import org.eclipse.core.commands.AbstractParameterValueConverter;
import org.eclipse.core.commands.ParameterValueConversionException;

/**
* Convert between Integer and String for a command parameter type.
*
* @since 3.4
*/
public class IntegerTypeConverter extends AbstractParameterValueConverter {

    @Override
    public Object convertToObject(String parameterValue) throws ParameterValueConversionException {
        try {
            return Integer.decode(parameterValue);
        } catch (NumberFormatException e) {
            throw new ParameterValueConversionException("Failed to decode", e);
        }
    }

    @Override
    public String convertToString(Object parameterValue) throws ParameterValueConversionException {
        if (!(parameterValue instanceof Integer)) {
            //$NON-NLS-1$
            throw new ParameterValueConversionException("Failed to convert");
        }
        return parameterValue.toString();
    }
}
