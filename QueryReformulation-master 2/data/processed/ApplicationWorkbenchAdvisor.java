/***/
package org.eclipse.ui.examples.contributions.rcp;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IExecutionListenerWithChecks;
import org.eclipse.core.commands.NotEnabledException;
import org.eclipse.core.commands.NotHandledException;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchAdvisor;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;
import org.eclipse.ui.commands.ICommandService;
import org.eclipse.ui.examples.contributions.Activator;

/**
* This workbench advisor creates the window advisor, and specifies the
* perspective id for the initial window.
*/
public class ApplicationWorkbenchAdvisor extends WorkbenchAdvisor {

    //$NON-NLS-1$
    private static final String PERSPECTIVE_ID = "org.eclipse.ui.examples.contributions.perspective";

    @Override
    public WorkbenchWindowAdvisor createWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        return new ApplicationWorkbenchWindowAdvisor(configurer);
    }

    @Override
    public String getInitialWindowPerspectiveId() {
        return PERSPECTIVE_ID;
    }

    @Override
    public void postStartup() {
        if (!Activator.DEBUG_COMMANDS) {
            return;
        }
        ICommandService service = getWorkbenchConfigurer().getWorkbench().getService(ICommandService.class);
        service.addExecutionListener(new IExecutionListenerWithChecks() {

            @Override
            public void notHandled(String commandId, NotHandledException exception) {
                System.out.println(//$NON-NLS-1$
                "commandId = " + commandId + //$NON-NLS-1$
                " : not handled");
            }

            @Override
            public void postExecuteFailure(String commandId, ExecutionException exception) {
                //$NON-NLS-1$ //$NON-NLS-2$
                System.out.println("commandId = " + commandId + " : failed");
            }

            @Override
            public void postExecuteSuccess(String commandId, Object returnValue) {
                System.out.println(//$NON-NLS-1$ //$NON-NLS-2$
                "commandId = " + commandId + " : success " + returnValue);
            }

            @Override
            public void preExecute(String commandId, ExecutionEvent event) {
                System.out.println(//$NON-NLS-1$ //$NON-NLS-2$
                "commandId = " + commandId + " : parms " + event.getParameters().keySet());
            }

            @Override
            public void notDefined(String commandId, NotDefinedException exception) {
                System.out.println(//$NON-NLS-1$
                "commandId = " + commandId + //$NON-NLS-1$
                " : not defined");
            }

            @Override
            public void notEnabled(String commandId, NotEnabledException exception) {
                System.out.println(//$NON-NLS-1$
                "commandId = " + commandId + //$NON-NLS-1$
                " : not enabled");
            }
        });
    }
}
