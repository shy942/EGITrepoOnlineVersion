/***/
package org.eclipse.e4.ui.internal.services;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import org.eclipse.e4.core.contexts.ContextFunction;
import org.eclipse.e4.core.contexts.IEclipseContext;

public class ActiveContextsFunction extends ContextFunction {

    @Override
    public Object compute(IEclipseContext context, String contextKey) {
        // 1) get active child
        IEclipseContext current = context.getActiveLeaf();
        //2 form an answer going up
        boolean inDialog = false;
        Set<Object> rc = new HashSet();
        while (current != null) {
            LinkedList<?> locals = (LinkedList<?>) current.getLocal(ContextContextService.LOCAL_CONTEXTS);
            if (locals != null) {
                if (!inDialog || !locals.contains("org.eclipse.ui.contexts.window")) {
                    rc.addAll(locals);
                }
                if (!inDialog && locals.contains("org.eclipse.ui.contexts.dialog")) {
                    inDialog = true;
                }
            }
            current = current.getParent();
        }
        return rc;
    }
}
