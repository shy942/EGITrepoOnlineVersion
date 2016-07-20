/***/
package org.eclipse.ui.tests.services;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;

public class CheckHandler extends AbstractHandler {

    @Override
    public Object execute(ExecutionEvent event) {
        // It's OK do do nothing
        return null;
    }
}
