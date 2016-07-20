/***/
package org.eclipse.ui.tests.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;

/**
* Handler for the <code>org.eclipse.ui.tests.commands.subtractInteger</code>
* command.
*/
public class SubtractIntegerHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        Integer minuend = (Integer) event.getObjectParameterForExecution(CommandParameterTypeTest.MINUEND);
        Integer subtrahend = (Integer) event.getObjectParameterForExecution(CommandParameterTypeTest.SUBTRAHEND);
        return Integer.valueOf(minuend.intValue() - subtrahend.intValue());
    }
}
