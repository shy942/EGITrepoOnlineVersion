/***/
package org.eclipse.core.tests.internal.databinding.conversion;

import org.eclipse.core.internal.databinding.conversion.StringToNumberParser;

/**
* @since 1.1
*/
public class StringToNumberParserIntegerTest extends StringToNumberParserTestHarness {

    @Override
    protected boolean assertValid(Number number) {
        return StringToNumberParser.inIntegerRange(number);
    }

    @Override
    protected Number getValidMax() {
        return Integer.valueOf(Integer.MAX_VALUE);
    }

    @Override
    protected Number getValidMin() {
        return Integer.valueOf(Integer.MIN_VALUE);
    }
}
