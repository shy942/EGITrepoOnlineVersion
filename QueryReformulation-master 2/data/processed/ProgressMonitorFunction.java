/***/
package org.eclipse.e4.ui.internal.workbench;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.e4.core.contexts.ContextFunction;
import org.eclipse.e4.core.contexts.IEclipseContext;

/**
* This class provides a context function that returns a default progress monitor. This is generally
* used near the root of a context tree to provide a reasonable default monitor for cases where more
* specific contexts have not provided one.
*/
public class ProgressMonitorFunction extends ContextFunction {

    @Override
    public Object compute(IEclipseContext context, String contextKey) {
        return new NullProgressMonitor();
    }
}
