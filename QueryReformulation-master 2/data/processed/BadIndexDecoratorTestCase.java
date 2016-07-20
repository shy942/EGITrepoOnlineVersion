/***/
package org.eclipse.ui.tests.decorators;

import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.decorators.DecoratorDefinition;

/**
* @since 3.2
*
*/
public class BadIndexDecoratorTestCase extends DecoratorEnablementTestCase {

    /**
* @param testName
*/
    public  BadIndexDecoratorTestCase(String testName) {
        super(testName);
    }

    /**
* Sets up the hierarchy.
*/
    @Override
    protected void doSetUp() throws Exception {
        super.doSetUp();
        createTestFile();
        showNav();
        WorkbenchPlugin.getDefault().getDecoratorManager().addListener(this);
        DecoratorDefinition[] definitions = WorkbenchPlugin.getDefault().getDecoratorManager().getAllDecoratorDefinitions();
        for (DecoratorDefinition definition2 : definitions) {
            if (definition2.getId().equals("org.eclipse.ui.tests.decorators.badIndexDecorator")) {
                definition = definition2;
            }
        }
    }

    /**
* Turn off an on the bad index decorator without
* generating an exception.
*/
    public void testNoException() {
        updated = false;
        getDecoratorManager().clearCaches();
        definition.setEnabled(true);
        getDecoratorManager().updateForEnablementChange();
        definition.setEnabled(false);
        getDecoratorManager().updateForEnablementChange();
        updated = false;
    }
}
