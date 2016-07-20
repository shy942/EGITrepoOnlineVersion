/***/
package org.eclipse.ui.tests.commands;

import org.eclipse.core.commands.Category;
import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.CommandManager;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IExecutionListener;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.ui.tests.harness.util.UITestCase;

/**
* Tests some of the API on command manager.
*
* @since 3.1
*/
public final class CommandManagerTest extends UITestCase {

    /**
* An execution listener that can be attached to the command manager. It
* will track which events it last heard.
*
* @since 3.1
*/
    private final class ExecutionListener implements IExecutionListener {

        /**
* The last command identifier to be not handled, or <code>null</code>
* if none.
*/
        private String notHandledId = null;

        /**
* The last command identifier to be executed, or <code>null</code> if
* none.
*/
        private String preExecuteId = null;

        /**
* The last not handled exception throw, or <code>null</code> if none.
*/
        private NotHandledException notHandledException = null;

        /**
* The last execution event, or <code>null</code> if none.
*/
        private ExecutionEvent preExecuteEvent = null;

        @Override
        public final void notHandled(final String commandId, final NotHandledException exception) {
            notHandledId = commandId;
            notHandledException = exception;
        }

        @Override
        public final void postExecuteFailure(final String commandId, final ExecutionException exception) {
        // Do nothing.
        }

        @Override
        public final void postExecuteSuccess(final String commandId, final Object returnValue) {
        // Do nothing
        }

        @Override
        public final void preExecute(final String commandId, final ExecutionEvent event) {
            preExecuteId = commandId;
            preExecuteEvent = event;
        }
    }

    /**
* Constructs a new instance of <code>CommandManagerTest</code>.
*
* @param name
*            The name of the test
*/
    public  CommandManagerTest(String testName) {
        super(testName);
    }

    public final void testExecutionListener() {
        final String commandId = "myCommand";
        final CommandManager commandManager = new CommandManager();
        final Category category = commandManager.getCategory(commandId);
        category.define("name", null);
        final Command command = commandManager.getCommand(commandId);
        command.define("name", null, category, null);
        final ExecutionListener listener = new ExecutionListener();
        commandManager.addExecutionListener(listener);
        Exception exception = null;
        final ExecutionEvent event = new ExecutionEvent();
        try {
            command.execute(event);
        } catch (final ExecutionException e) {
            exception = e;
        } catch (final NotHandledException e) {
            exception = e;
        }
        assertSame("Should have received a pre-execute event for the correct command", commandId, listener.preExecuteId);
        assertSame("Should have received a pre-execute event with the correct event", event, listener.preExecuteEvent);
        assertSame("Should have received a not-handled event for the correct command", commandId, listener.notHandledId);
        assertSame("Should have received a not-handled event with the correct exception", exception, listener.notHandledException);
    }
}
