/***/
package org.eclipse.e4.ui.internal.workbench;

import org.eclipse.e4.core.contexts.ContextFunction;
import org.eclipse.e4.core.contexts.IEclipseContext;

/**
*
*/
public class ActiveChildLookupFunction extends ContextFunction {

    private String localVar;

    private String var;

    public  ActiveChildLookupFunction(String var, String localVar) {
        this.var = var;
        this.localVar = localVar;
    }

    @Override
    public Object compute(IEclipseContext context, String contextKey) {
        IEclipseContext childContext = context.getActiveChild();
        if (childContext != null) {
            return childContext.get(var);
        }
        return context.get(localVar);
    }
}
