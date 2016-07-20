/***/
package org.eclipse.ui.views.markers.internal;

import org.eclipse.core.resources.IMarker;

/**
*
*/
public class ProblemMarker extends ConcreteMarker {

    private int severity;

    public  ProblemMarker(IMarker toCopy) {
        super(toCopy);
    }

    @Override
    public void refresh() {
        super.refresh();
        severity = getMarker().getAttribute(IMarker.SEVERITY, -1);
    }

    public int getSeverity() {
        return severity;
    }
}
