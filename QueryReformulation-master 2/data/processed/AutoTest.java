/***/
package org.eclipse.ui.tests.autotests;

/**
* @since 3.1
*/
public interface AutoTest {

    public abstract String getName();

    public abstract String performTest() throws Throwable;
}
