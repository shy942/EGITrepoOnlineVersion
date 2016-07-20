/***/
package org.eclipse.ui.internal.ide.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.ISources;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.BuildAction;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.part.FileEditorInput;

/**
* Default Handler for 'Build Project' command
*
* @since 4.3
*
*/
public class BuildProjectHandler extends AbstractHandler {

    /**
* @throws ExecutionException
*/
    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        IWorkbenchWindow window = HandlerUtil.getActiveWorkbenchWindow(event);
        if (window != null) {
            ISelection currentSelection = HandlerUtil.getCurrentSelection(event);
            if (currentSelection instanceof IStructuredSelection) {
                runBuildAction(window, currentSelection);
            } else {
                currentSelection = extractSelectionFromEditorInput(HandlerUtil.getActiveEditorInput(event));
                runBuildAction(window, currentSelection);
            }
        }
        return null;
    }

    private ISelection extractSelectionFromEditorInput(IEditorInput activeEditorInput) {
        if (activeEditorInput instanceof FileEditorInput) {
            IProject project = ((FileEditorInput) activeEditorInput).getFile().getProject();
            return new StructuredSelection(project);
        }
        return null;
    }

    private void runBuildAction(IWorkbenchWindow window, ISelection currentSelection) {
        BuildAction buildAction = newBuildAction(window);
        buildAction.selectionChanged((IStructuredSelection) currentSelection);
        buildAction.run();
    }

    private BuildAction newBuildAction(IWorkbenchWindow window) {
        return new BuildAction(window, IncrementalProjectBuilder.INCREMENTAL_BUILD);
    }

    @Override
    public void setEnabled(Object evaluationContext) {
        boolean enabled = false;
        if ((evaluationContext instanceof IEvaluationContext)) {
            IEvaluationContext context = (IEvaluationContext) evaluationContext;
            Object object = context.getVariable(ISources.ACTIVE_WORKBENCH_WINDOW_NAME);
            if (object instanceof IWorkbenchWindow) {
                BuildAction buildAction = newBuildAction((IWorkbenchWindow) object);
                enabled = buildAction.isEnabled();
            }
        }
        setBaseEnabled(enabled);
    }
}
