/***/
package org.eclipse.ui.tests.dynamicplugins;

import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.decorators.DecoratorDefinition;
import org.eclipse.ui.internal.decorators.DecoratorManager;
import org.eclipse.ui.internal.registry.IWorkbenchRegistryConstants;

/**
* @since 3.1
*/
public class DecoratorTests extends DynamicTestCase {

    public static final String FULL1 = "fullDecorator1";

    public static final String LIGHT1 = "lightDecorator1";

    public static final String LIGHT2 = "lightDecorator2";

    /**
* @param testName
*/
    public  DecoratorTests(String testName) {
        super(testName);
    }

    public void testDecorators() {
        assertFalse(hasDecorator(FULL1));
        assertFalse(hasDecorator(LIGHT1));
        assertFalse(hasDecorator(LIGHT2));
        getBundle();
        assertTrue(hasDecorator(FULL1));
        assertTrue(hasDecorator(LIGHT1));
        assertTrue(hasDecorator(LIGHT2));
        removeBundle();
        assertFalse(hasDecorator(FULL1));
        assertFalse(hasDecorator(LIGHT1));
        assertFalse(hasDecorator(LIGHT2));
    }

    public boolean hasDecorator(String id) {
        DecoratorManager manager = WorkbenchPlugin.getDefault().getDecoratorManager();
        DecoratorDefinition[] definitions = manager.getAllDecoratorDefinitions();
        for (DecoratorDefinition definition : definitions) {
            if (definition.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    @Override
    protected String getExtensionId() {
        return "newDecorator1.testDynamicDecoratorAddition";
    }

    @Override
    protected String getExtensionPoint() {
        return IWorkbenchRegistryConstants.PL_DECORATORS;
    }

    @Override
    protected String getInstallLocation() {
        return "data/org.eclipse.newDecorator1";
    }

    @Override
    protected String getMarkerClass() {
        return "org.eclipse.ui.dynamic.DynamicLabelDecorator";
    }
}
