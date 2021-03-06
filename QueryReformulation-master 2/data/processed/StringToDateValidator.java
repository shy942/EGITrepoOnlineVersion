/***/
package org.eclipse.core.internal.databinding.validation;

import java.util.Date;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.databinding.validation.ValidationStatus;
import org.eclipse.core.internal.databinding.BindingMessages;
import org.eclipse.core.internal.databinding.conversion.DateConversionSupport;
import org.eclipse.core.internal.databinding.conversion.StringToDateConverter;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
* @since 1.0
*/
public class StringToDateValidator implements IValidator {

    private final StringToDateConverter converter;

    /**
* @param converter
*/
    public  StringToDateValidator(StringToDateConverter converter) {
        this.converter = converter;
    }

    @Override
    public IStatus validate(Object value) {
        if (value instanceof String && ((String) value).trim().length() == 0) {
            return Status.OK_STATUS;
        }
        Object convertedValue = converter.convert(value);
        //The StringToDateConverter returns null if it can't parse the date.
        if (convertedValue == null) {
            return ValidationStatus.error(getErrorMessage());
        }
        return Status.OK_STATUS;
    }

    protected String getErrorMessage() {
        Date sampleDate = new Date();
        // FIXME We need to use the information from the
        // converter, not use another instance of DateConversionSupport.
        FormatUtil util = new FormatUtil();
        StringBuffer samples = new StringBuffer();
        for (int formatterIdx = 1; formatterIdx < util.numFormatters() - 2; formatterIdx++) {
            samples.append('\'');
            samples.append(util.format(sampleDate, formatterIdx));
            //$NON-NLS-1$
            samples.append("', ");
        }
        samples.append('\'');
        samples.append(util.format(sampleDate, 0));
        samples.append('\'');
        //$NON-NLS-1$//$NON-NLS-2$
        return BindingMessages.getString(BindingMessages.EXAMPLES) + ": " + samples + ",...";
    }

    private static class FormatUtil extends DateConversionSupport {

        @Override
        protected int numFormatters() {
            return super.numFormatters();
        }

        @Override
        protected String format(Date date) {
            return super.format(date);
        }

        @Override
        protected String format(Date date, int formatterIdx) {
            return super.format(date, formatterIdx);
        }
    }
}
