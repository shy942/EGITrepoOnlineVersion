/***/
package org.eclipse.ui.internal.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.action.ContributionManager;
import org.eclipse.jface.action.IMenuListener2;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.QuickMenuCreator;
import org.eclipse.ui.menus.IMenuService;
import org.eclipse.ui.progress.UIJob;

/**
* Support for a command based QuickMenuAction that can pop up a
* menu-contribution based context menu.
* <p>
* This is experimental and should not be moved.
* </p>
*
* @since 3.4
*/
public class QuickMenuHandler extends AbstractHandler implements IMenuListener2 {

    private QuickMenuCreator creator = new QuickMenuCreator() {

        @Override
        protected void fillMenu(IMenuManager menu) {
            if (!(menu instanceof ContributionManager)) {
                return;
            }
            IMenuService service = PlatformUI.getWorkbench().getService(IMenuService.class);
            service.populateContributionManager((ContributionManager) menu, locationURI);
            menu.addMenuListener(QuickMenuHandler.this);
        }
    };

    private String locationURI;

    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        //$NON-NLS-1$
        locationURI = event.getParameter("org.eclipse.ui.window.quickMenu.uri");
        if (locationURI == null) {
            //$NON-NLS-1$
            throw new ExecutionException("locatorURI must not be null");
        }
        creator.createMenu();
        return null;
    }

    @Override
    public void dispose() {
        if (creator != null) {
            creator.dispose();
            creator = null;
        }
    }

    @Override
    public void menuAboutToHide(final IMenuManager managerM) {
        new //$NON-NLS-1$
        UIJob(//$NON-NLS-1$
        "quickMenuCleanup") {

            @Override
            public IStatus runInUIThread(IProgressMonitor monitor) {
                IMenuService service = PlatformUI.getWorkbench().getService(IMenuService.class);
                service.releaseContributions((ContributionManager) managerM);
                return Status.OK_STATUS;
            }
        }.schedule();
    }

    @Override
    public void menuAboutToShow(IMenuManager manager) {
    // no-op
    }
}
