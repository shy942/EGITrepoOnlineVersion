/***/
package org.eclipse.e4.ui.internal.workbench.swt;

import org.eclipse.e4.core.contexts.ContextFunction;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.di.InjectionException;

public class MenuServiceCreationFunction extends ContextFunction {

    @Override
    public Object compute(IEclipseContext context, String contextKey) {
        try {
            return ContextInjectionFactory.make(MenuService.class, context);
        } catch (InjectionException ie) {
            System.err.println("MenuService: " + context + ": " + ie);
        }
        return null;
    }
}
