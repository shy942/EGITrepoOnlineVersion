/***/
package org.eclipse.e4.ui.internal.workbench;

import org.eclipse.e4.core.contexts.ContextFunction;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.IInjector;
import org.eclipse.e4.ui.model.application.MApplication;
import org.eclipse.e4.ui.model.application.ui.MContext;
import org.eclipse.e4.ui.model.application.ui.basic.MWindow;

/**
*
*/
public class PartServiceCreationFunction extends ContextFunction {

    @Override
    public Object compute(IEclipseContext context, String contextKey) {
        // look for the top-most MWindow in the context chain:
        // 1st: go up the tree to find topmost MWindow
        MWindow window = null;
        IEclipseContext current = context;
        do {
            MContext model = current.get(MContext.class);
            if (model instanceof MWindow)
                window = (MWindow) model;
            current = current.getParent();
        } while (current != null);
        if (window == null) {
            if (context.get(MApplication.class) != null) {
                // called from Application scope
                return ContextInjectionFactory.make(ApplicationPartServiceImpl.class, context);
            }
            return IInjector.NOT_A_VALUE;
        }
        IEclipseContext windowContext = window.getContext();
        PartServiceImpl service = windowContext.getLocal(PartServiceImpl.class);
        if (service == null) {
            service = ContextInjectionFactory.make(PartServiceImpl.class, windowContext);
            windowContext.set(PartServiceImpl.class, service);
        }
        return service;
    }
}
