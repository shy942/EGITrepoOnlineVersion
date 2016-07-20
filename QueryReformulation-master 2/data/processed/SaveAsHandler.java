/***/
package org.eclipse.ui.internal.handlers;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.expressions.EvaluationResult;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.ui.ISaveablePart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.internal.InternalHandlerUtil;
import org.eclipse.ui.internal.WorkbenchPage;

/**
* <p>
* Replacement for SaveAsAction
* </p>
*
* @since 3.7
*
*/
public class SaveAsHandler extends AbstractSaveHandler {

    public  SaveAsHandler() {
        registerEnablement();
    }

    @Override
    public Object execute(ExecutionEvent event) {
        ISaveablePart saveablePart = getSaveablePart(event);
        if (saveablePart != null)
            saveablePart.doSaveAs();
        return null;
    }

    @Override
    protected EvaluationResult evaluate(IEvaluationContext context) {
        IWorkbenchWindow window = InternalHandlerUtil.getActiveWorkbenchWindow(context);
        // no window? not active
        if (window == null)
            return EvaluationResult.FALSE;
        WorkbenchPage page = (WorkbenchPage) window.getActivePage();
        // no page? not active
        if (page == null)
            return EvaluationResult.FALSE;
        // get saveable part
        ISaveablePart saveablePart = getSaveablePart(context);
        if (saveablePart == null)
            return EvaluationResult.FALSE;
        // if its availble, return whatever it says
        return saveablePart.isSaveAsAllowed() ? EvaluationResult.TRUE : EvaluationResult.FALSE;
    }
}
