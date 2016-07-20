/***/
package org.eclipse.e4.ui.internal.workbench;

import javax.inject.Inject;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.services.log.ILoggerProvider;
import org.eclipse.e4.core.services.log.Logger;
import org.osgi.framework.FrameworkUtil;

/**
*
*/
public class DefaultLoggerProvider implements ILoggerProvider {

    @Inject
    private IEclipseContext context;

    @Override
    public Logger getClassLogger(Class<?> clazz) {
        IEclipseContext childContext = context.createChild();
        //$NON-NLS-1$
        childContext.set("logger.bundlename", FrameworkUtil.getBundle(clazz).getSymbolicName());
        return ContextInjectionFactory.make(WorkbenchLogger.class, childContext);
    }
}
