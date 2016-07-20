/***/
package org.eclipse.core.tests.internal.databinding.conversion;

import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.internal.databinding.conversion.NumberToShortConverter;
import com.ibm.icu.text.NumberFormat;

/**
* @since 1.1
*/
public class NumberToShortConverterTest extends NumberToNumberTestHarness {

    private NumberFormat numberFormat;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        numberFormat = NumberFormat.getInstance();
    }

    @Override
    protected Number doGetOutOfRangeNumber() {
        return Integer.valueOf(Short.MAX_VALUE + 1);
    }

    @Override
    protected IConverter doGetToBoxedTypeValidator(Class fromType) {
        return new NumberToShortConverter(numberFormat, fromType, false);
    }

    @Override
    protected IConverter doGetToPrimitiveValidator(Class fromType) {
        return new NumberToShortConverter(numberFormat, fromType, true);
    }

    @Override
    protected Class doGetToType(boolean primitive) {
        return (primitive) ? Short.TYPE : Short.class;
    }
}
