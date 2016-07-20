/***/
package org.eclipse.ui.tests.dynamicplugins;

import org.eclipse.core.commands.common.NamedHandleObject;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.internal.registry.IWorkbenchRegistryConstants;

/**
* Tests whether the "org.eclipse.ui.actionDefinitions" extension point can be
* added and removed dynamically.
*
* @since 3.1.1
*/
public final class ActionDefinitionsExtensionDynamicTest extends DynamicTestCase {

    /**
* Constructs a new instance of
* <code>ActionDefinitionsExtensionDynamicTest</code>.
*
* @param testName
*            The name of the test; may be <code>null</code>.
*/
    public  ActionDefinitionsExtensionDynamicTest(final String testName) {
        super(testName);
    }

    /**
* Returns the full-qualified identifier of the extension to be tested.
*
* @return The extension identifier; never <code>null</code>.
*/
    @Override
    protected final String getExtensionId() {
        return "actionDefinitionsExtensionDynamicTest.testDynamicActionDefinitionAddition";
    }

    /**
* Returns the unqualified identifier of the extension point to be tested.
*
* @return The extension point identifier; never <code>null</code>.
*/
    @Override
    protected final String getExtensionPoint() {
        return IWorkbenchRegistryConstants.PL_ACTION_DEFINITIONS;
    }

    /**
* Returns the relative location of the folder on disk containing the
* plugin.xml file.
*
* @return The relative install location; never <code>null</code>.
*/
    @Override
    protected final String getInstallLocation() {
        return "data/org.eclipse.actionDefinitionsExtensionDynamicTest";
    }

    /**
* Tests whether the items defined in the extension point can be added and
* removed dynamically. It tests that the data doesn't exist, and then loads
* the extension. It tests that the data then exists, and unloads the
* extension. It tests that the data then doesn't exist.
*/
    public final void testActionDefinitions() {
        final ICommandService service = getWorkbench().getAdapter(ICommandService.class);
        NamedHandleObject namedHandleObject;
        namedHandleObject = service.getCommand("monkey");
        try {
            namedHandleObject.getName();
            fail();
        } catch (final NotDefinedException e) {
            assertTrue(true);
        }
        getBundle();
        namedHandleObject = service.getCommand("monkey");
        try {
            assertTrue("Monkey".equals(namedHandleObject.getName()));
        } catch (final NotDefinedException e) {
            fail();
        }
        removeBundle();
        namedHandleObject = service.getCommand("monkey");
        try {
            namedHandleObject.getName();
            fail();
        } catch (final NotDefinedException e) {
            assertTrue(true);
        }
    }
}
