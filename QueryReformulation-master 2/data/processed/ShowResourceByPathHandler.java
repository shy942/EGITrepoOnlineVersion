/***/
package org.eclipse.ui.internal.ide.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.ui.part.ISetSelectionTarget;

/**
* A command handler to show a resource in the Navigator view given the resource
* path.
*
* @since 3.2
*/
public class ShowResourceByPathHandler extends AbstractHandler {

    //$NON-NLS-1$
    private static final String PARAM_ID_RESOURCE_PATH = "resourcePath";

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        IResource resource = (IResource) event.getObjectParameterForExecution(PARAM_ID_RESOURCE_PATH);
        IWorkbenchWindow activeWindow = HandlerUtil.getActiveWorkbenchWindowChecked(event);
        IWorkbenchPage activePage = activeWindow.getActivePage();
        if (activePage == null) {
            //$NON-NLS-1$
            throw new ExecutionException("no active workbench page");
        }
        try {
            IViewPart view = activePage.showView(IPageLayout.ID_RES_NAV);
            if (view instanceof ISetSelectionTarget) {
                ISelection selection = new StructuredSelection(resource);
                ((ISetSelectionTarget) view).selectReveal(selection);
            }
        } catch (PartInitException e) {
            throw new ExecutionException("error showing resource in navigator");
        }
        return null;
    }
}
