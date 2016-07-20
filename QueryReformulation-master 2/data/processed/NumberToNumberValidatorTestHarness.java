/***/
package org.eclipse.core.tests.internal.databinding.validation;

import junit.framework.TestCase;
import org.eclipse.core.databinding.validation.IValidator;
import org.eclipse.core.internal.databinding.validation.NumberToNumberValidator;
import org.eclipse.core.runtime.IStatus;

/**
* @since 1.1
*/
public abstract class NumberToNumberValidatorTestHarness extends TestCase {

    protected abstract NumberToNumberValidator doGetToPrimitiveValidator(Class fromType);

    protected abstract NumberToNumberValidator doGetToBoxedTypeValidator(Class fromType);

    protected abstract Number doGetOutOfRangeNumber();

    public void testValidateNullForBoxedTypeIsOK() throws Exception {
        IStatus status = doGetToBoxedTypeValidator(Integer.class).validate(null);
        assertTrue(status.isOK());
    }

    public void testValidateNullForPrimitiveThrowsIllegalArgumentException() throws Exception {
        IValidator validator = doGetToPrimitiveValidator(Integer.class);
        if (validator == null) {
            //return if a primitive validator does not exist (e.g. BigInteger, BigDecimal, etc.)
            return;
        }
        try {
            doGetToPrimitiveValidator(Integer.class).validate(null);
            fail("exception should have been thrown");
        } catch (IllegalArgumentException e) {
        }
    }

    public void testValidReturnsOK() throws Exception {
        assertTrue(doGetToBoxedTypeValidator(Integer.class).validate(Integer.valueOf(1)).isOK());
    }

    public void testOutOfRangeReturnsError() throws Exception {
        Number number = doGetOutOfRangeNumber();
        if (number == null) {
            //return if there is no value out of range (e.g. BigInteger, BigDecimal, etc.)
            return;
        }
        IStatus status = doGetToBoxedTypeValidator(Integer.class).validate(number);
        assertEquals(IStatus.ERROR, status.getSeverity());
        assertTrue(status.getMessage() != null);
    }

    public void testValidateIncorrectTypeThrowsIllegalArgumentException() throws Exception {
        try {
            doGetToBoxedTypeValidator(Integer.class).validate("");
            fail("exception should have been thrown");
        } catch (IllegalArgumentException e) {
        }
    }
}
