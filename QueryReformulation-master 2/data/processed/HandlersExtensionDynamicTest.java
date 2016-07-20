/***/
package org.eclipse.ui.tests.dynamicplugins;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.internal.registry.IWorkbenchRegistryConstants;

/**
* Tests whether the "org.eclipse.ui.handlers" extension point can be added and
* removed dynamically.
*
* @since 3.1.1
*/
public final class HandlersExtensionDynamicTest extends DynamicTestCase {

    /**
* Constructs a new instance of <code>HandlersExtensionDynamicTest</code>.
*
* @param testName
*            The name of the test; may be <code>null</code>.
*/
    public  HandlersExtensionDynamicTest(final String testName) {
        super(testName);
    }

    /**
* Returns the full-qualified identifier of the extension to be tested.
*
* @return The extension identifier; never <code>null</code>.
*/
    @Override
    protected final String getExtensionId() {
        return "handlersExtensionDynamicTest.testDynamicHandlerAddition";
    }

    /**
* Returns the unqualified identifier of the extension point to be tested.
*
* @return The extension point identifier; never <code>null</code>.
*/
    @Override
    protected final String getExtensionPoint() {
        return IWorkbenchRegistryConstants.PL_HANDLERS;
    }

    /**
* Returns the relative location of the folder on disk containing the
* plugin.xml file.
*
* @return The relative install location; never <code>null</code>.
*/
    @Override
    protected final String getInstallLocation() {
        return "data/org.eclipse.handlersExtensionDynamicTest";
    }

    /**
* Tests whether the items defined in the extension point can be added and
* removed dynamically. It tests that the data doesn't exist, and then loads
* the extension. It tests that the data then exists, and unloads the
* extension. It tests that the data then doesn't exist.
*/
    public final void testHandlers() {
        final ICommandService commandService = getWorkbench().getAdapter(ICommandService.class);
        Command command;
        command = commandService.getCommand("monkey");
        try {
            command.execute(new ExecutionEvent());
            fail();
        } catch (final ExecutionException e) {
            fail();
        } catch (final NotHandledException e) {
            assertTrue(true);
        }
        getBundle();
        command = commandService.getCommand("monkey");
        try {
            command.execute(new ExecutionEvent());
        } catch (final ExecutionException e) {
            fail();
        } catch (final NotHandledException e) {
            fail();
        }
        removeBundle();
        command = commandService.getCommand("monkey");
        try {
            command.execute(new ExecutionEvent());
            fail();
        } catch (final ExecutionException e) {
            fail();
        } catch (final NotHandledException e) {
            assertTrue(true);
        }
    }
}
