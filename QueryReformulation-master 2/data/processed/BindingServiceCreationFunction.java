/***/
package org.eclipse.e4.ui.bindings.internal;

import org.eclipse.e4.core.contexts.ContextFunction;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;

/**
*
*/
public class BindingServiceCreationFunction extends ContextFunction {

    @Override
    public Object compute(IEclipseContext context, String contextKey) {
        return ContextInjectionFactory.make(BindingServiceImpl.class, context);
    }
}
