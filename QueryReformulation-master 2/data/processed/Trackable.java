/***/
package org.eclipse.e4.ui.internal.workbench;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.core.contexts.RunAndTrack;

public abstract class Trackable extends RunAndTrack {

    public IEclipseContext trackingContext;

    public boolean participating = true;

    public  Trackable(IEclipseContext context) {
        this.trackingContext = context;
    }
}
