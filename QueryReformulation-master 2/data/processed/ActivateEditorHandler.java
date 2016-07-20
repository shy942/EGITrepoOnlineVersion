/***/
package org.eclipse.ui.examples.contributions.editor;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.examples.contributions.model.PersonInput;
import org.eclipse.ui.handlers.HandlerUtil;

/**
* Activate an already open editor (although technically this would open a new
* one as well)
*
* @since 3.4
*/
public class ActivateEditorHandler extends AbstractHandler {

    //$NON-NLS-1$
    public static final String ID = "org.eclipse.ui.examples.contributions.editor.activate";

    //$NON-NLS-1$
    public static final String PARM_EDITOR = "org.eclipse.ui.examples.contributions.editor.activate.index";

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindowChecked(event);
        Object index = event.getObjectParameterForExecution(PARM_EDITOR);
        if (!(index instanceof Integer)) {
            //$NON-NLS-1$
            throw new ExecutionException("Invalid index: " + index);
        }
        PersonInput input = new PersonInput(((Integer) index).intValue());
        try {
            window.getActivePage().openEditor(input, InfoEditor.ID, true);
        } catch (PartInitException e) {
            throw new ExecutionException("Failed to activate editor", e);
        }
        return null;
    }
}
