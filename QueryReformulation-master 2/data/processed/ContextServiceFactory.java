/***/
package org.eclipse.ui.internal.contexts;

import org.eclipse.core.expressions.Expression;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.contexts.IContextService;
import org.eclipse.ui.internal.expressions.ActivePartExpression;
import org.eclipse.ui.internal.expressions.WorkbenchWindowExpression;
import org.eclipse.ui.internal.services.IWorkbenchLocationService;
import org.eclipse.ui.services.AbstractServiceFactory;
import org.eclipse.ui.services.IServiceLocator;

/**
* @since 3.4
*
*/
public class ContextServiceFactory extends AbstractServiceFactory {

    @Override
    public Object create(Class serviceInterface, IServiceLocator parentLocator, IServiceLocator locator) {
        if (!IContextService.class.equals(serviceInterface)) {
            return null;
        }
        IWorkbenchLocationService wls = locator.getService(IWorkbenchLocationService.class);
        final IWorkbench wb = wls.getWorkbench();
        if (wb == null) {
            return null;
        }
        Object parent = parentLocator.getService(serviceInterface);
        if (parent == null) {
            // we are registering the global services in the Workbench
            return null;
        }
        final IWorkbenchWindow window = wls.getWorkbenchWindow();
        final IWorkbenchPartSite site = wls.getPartSite();
        if (site == null) {
            Expression exp = new WorkbenchWindowExpression(window);
            return new SlaveContextService((IContextService) parent, exp);
        }
        if (parent instanceof SlaveContextService) {
            Expression exp = ((SlaveContextService) parent).fDefaultExpression;
            if (exp instanceof ActivePartExpression) {
                return new NestableContextService((IContextService) parent, exp);
            }
        }
        Expression exp = new ActivePartExpression(site.getPart());
        return new SlaveContextService((IContextService) parent, exp);
    }
}