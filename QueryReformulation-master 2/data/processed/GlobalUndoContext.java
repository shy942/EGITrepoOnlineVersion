/***/
package org.eclipse.core.internal.commands.operations;

import org.eclipse.core.commands.operations.IUndoContext;

/**
* <p>
* An operation context that matches to any context.  It can be used to
* get an unfiltered (global) history.
* </p>
*
* @since 3.1
*/
public class GlobalUndoContext implements IUndoContext {

    @Override
    public String getLabel() {
        //$NON-NLS-1$
        return "Global Undo Context";
    }

    @Override
    public boolean matches(IUndoContext context) {
        return true;
    }
}
