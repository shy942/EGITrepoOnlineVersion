/***/
package org.eclipse.ui.tests.autotests;

/**
* @since 3.1
*/
public class TestResult {

    private String result;

    private Throwable thrownException;

    public  TestResult(String expectedResult) {
        this.result = expectedResult;
    }

    public  TestResult(Throwable t) {
        this.result = null;
        this.thrownException = t;
    }

    /**
* Return the expected result or null if the test threw an
* exception rather than terminate normally.
*
* @return the expected result or null if the test threw an
* exception rather than terminate normally.
*/
    public String getReturnValue() {
        return result;
    }

    public Throwable getException() {
        return thrownException;
    }
}
