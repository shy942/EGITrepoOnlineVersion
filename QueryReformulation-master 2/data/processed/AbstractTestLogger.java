/***/
package org.eclipse.ui.tests.autotests;

/**
* @since 3.1
*/
public abstract class AbstractTestLogger {

    public abstract void reportResult(String testName, TestResult result) throws Throwable;
}
