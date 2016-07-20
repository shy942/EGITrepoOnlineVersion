/***/
package org.eclipse.e4.ui.internal.workbench.swt;

import org.eclipse.e4.core.contexts.ContextFunction;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IEclipseContext;

/**
*
*/
public class StatusReporterCreationFunction extends ContextFunction {

    @Override
    public Object compute(IEclipseContext context, String contextKey) {
        return ContextInjectionFactory.make(WorkbenchStatusReporter.class, context);
    }
}
