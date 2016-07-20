/***/
package org.eclipse.ui.tests.commands;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ParameterizedCommand;
import org.eclipse.core.commands.common.CommandException;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.tests.harness.util.UITestCase;

/**
* Tests a bug found in
* {@link ParameterizedCommand#generateCombinations(Command)} and its private
* helper method <code>expandParameters</code>. The bug is causing a
* <code>StackOverflowError</code> when dealing with commands that have
* several optional parameters for which there are no
* <code>IParameterValues</code>.
*
* @since 3.2
*/
public class Bug125792Test extends UITestCase {

    public  Bug125792Test(final String name) {
        super(name);
    }

    private static final String COMMAND_ID = "org.eclipse.ui.tests.commands.bug125792";

    public void testParameterizedCommand_generateCombinations() throws CommandException {
        Command command = getCommandService().getCommand(COMMAND_ID);
        ParameterizedCommand.generateCombinations(command);
    }

    private ICommandService getCommandService() {
        Object serviceObject = getWorkbench().getAdapter(ICommandService.class);
        if (serviceObject != null) {
            ICommandService service = (ICommandService) serviceObject;
            return service;
        }
        return null;
    }
}
