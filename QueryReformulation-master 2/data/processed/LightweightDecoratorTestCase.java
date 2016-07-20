/***/
package org.eclipse.ui.tests.decorators;

import org.eclipse.jface.viewers.ILabelProviderListener;

/**
* @version 1.0
*/
public class LightweightDecoratorTestCase extends DecoratorEnablementTestCase implements ILabelProviderListener {

    /**
* Constructor for DecoratorTestCase.
*
* @param testName
*/
    public  LightweightDecoratorTestCase(String testName) {
        super(testName);
    }

    /**
* Refresh the test decorator.
*/
    public void testRefreshContributor() {
        updated = false;
        getDecoratorManager().clearCaches();
        definition.setEnabled(true);
        getDecoratorManager().updateForEnablementChange();
        assertTrue("Got an update", updated);
        updated = false;
    }
}
