/***/
package org.eclipse.e4.ui.internal.services;

import org.eclipse.e4.core.contexts.ContextFunction;
import org.eclipse.e4.core.contexts.IEclipseContext;

public class ContextContextFunction extends ContextFunction {

    @Override
    public Object compute(IEclipseContext context, String contextKey) {
        return new ContextContextService(context);
    }
}
