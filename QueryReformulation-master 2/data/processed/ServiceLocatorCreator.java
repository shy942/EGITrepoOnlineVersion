/***/
package org.eclipse.ui.internal.services;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.ui.services.AbstractServiceFactory;
import org.eclipse.ui.services.IDisposable;
import org.eclipse.ui.services.IServiceLocator;

/**
* A simple service locator creator.
*
* @since 3.4
*/
public class ServiceLocatorCreator implements IServiceLocatorCreator {

    @Override
    public IServiceLocator createServiceLocator(IServiceLocator parent, AbstractServiceFactory factory, IDisposable owner) {
        ServiceLocator serviceLocator = new ServiceLocator(parent, factory, owner);
        //System.err.println("parentLocator: " + parent); //$NON-NLS-1$
        if (parent != null) {
            IEclipseContext ctx = parent.getService(IEclipseContext.class);
            if (ctx != null) {
                serviceLocator.setContext(ctx.createChild());
            }
        }
        return serviceLocator;
    }

    @Override
    public IServiceLocator createServiceLocator(IServiceLocator parent, AbstractServiceFactory factory, IDisposable owner, IEclipseContext context) {
        ServiceLocator serviceLocator = new ServiceLocator(parent, factory, owner);
        serviceLocator.setContext(context);
        return serviceLocator;
    }
}
