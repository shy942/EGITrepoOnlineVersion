/***/
package org.eclipse.ui.tests.decorators;

/**
* Decorator used to test an unadaptaed contribution
*/
public class TestUnadaptableDecoratorContributor extends TestAdaptableDecoratorContributor {

    public static final String SUFFIX = "ICommon.2";

    public static final String ID = "org.eclipse.ui.tests.decorators.generalAdaptabilityOff";

    public  TestUnadaptableDecoratorContributor() {
        setSuffix(SUFFIX);
    }
}
