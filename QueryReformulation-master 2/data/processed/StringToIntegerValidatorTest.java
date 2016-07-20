/***/
package org.eclipse.core.tests.internal.databinding.validation;

import org.eclipse.core.databinding.conversion.StringToNumberConverter;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.internal.databinding.validation.StringToIntegerValidator;
import com.ibm.icu.text.NumberFormat;

/**
* @since 1.1
*/
public class StringToIntegerValidatorTest extends StringToNumberValidatorTestHarness {

    @Override
    protected NumberFormat setupNumberFormat() {
        return NumberFormat.getIntegerInstance();
    }

    @Override
    protected IValidator setupValidator(NumberFormat numberFormat) {
        StringToNumberConverter converter = StringToNumberConverter.toInteger(numberFormat, false);
        return new StringToIntegerValidator(converter);
    }

    @Override
    protected Number getInRangeNumber() {
        return Integer.valueOf(1);
    }

    @Override
    protected String getInvalidString() {
        return "1.1";
    }

    @Override
    protected Number getOutOfRangeNumber() {
        return new Double(Double.MAX_VALUE);
    }
}
