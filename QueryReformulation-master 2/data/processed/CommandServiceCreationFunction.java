/***/
package org.eclipse.e4.core.commands.internal;

import org.eclipse.core.commands.CommandManager;
import org.eclipse.e4.core.contexts.ContextFunction;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;

/**
*
*/
public class CommandServiceCreationFunction extends ContextFunction {

    /**
* A context key (value "rootContext") that identifies the root of this context chain. It does
* not have to be the global root, but signifies the topmost context for the purposes of
* function management and active context chains.
*/
    //$NON-NLS-1$
    public static final String ROOT_CONTEXT = "rootContext";

    private CommandManager manager = null;

    private CommandServiceImpl service = null;

    @Override
    public Object compute(IEclipseContext context, String contextKey) {
        if (service == null) {
            IEclipseContext root = getRootContext(context);
            manager = new CommandManager();
            root.set(CommandManager.class.getName(), manager);
            service = ContextInjectionFactory.make(CommandServiceImpl.class, root);
        }
        return service;
    }

    /**
* @param context
* @return the topmost "root" context
*/
    private IEclipseContext getRootContext(IEclipseContext context) {
        IEclipseContext current = (IEclipseContext) context.get(ROOT_CONTEXT);
        if (current != null) {
            return current;
        }
        current = context;
        IEclipseContext parent = current.getParent();
        while (parent != null) {
            current = parent;
            parent = current.getParent();
        }
        if (current != null) {
            current.set(ROOT_CONTEXT, current);
        }
        return current;
    }
}
