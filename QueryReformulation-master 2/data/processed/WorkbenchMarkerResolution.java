/***/
package org.eclipse.ui.views.markers;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.IMarkerResolution2;
import org.eclipse.ui.views.markers.internal.Util;

/**
* WorkbenchMarkerResolution is the resolution that can be grouped
* with others that are similar to allow multi selection.
* @since 3.2
*
*/
public abstract class WorkbenchMarkerResolution implements IMarkerResolution2 {

    /**
* Iterate through the list of supplied markers. Return any that can also have
* the receiver applied to them.
* @param markers
* @return IMarker[]
*
* */
    public abstract IMarker[] findOtherMarkers(IMarker[] markers);

    /**
* Runs this resolution. Resolve all <code>markers</code>.
* <code>markers</code> must be a subset of the markers returned
* by <code>findOtherMarkers(IMarker[])</code>.
*
* @param markers The markers to resolve, not null
* @param monitor The monitor to report progress
*/
    public void run(IMarker[] markers, IProgressMonitor monitor) {
        for (int i = 0; i < markers.length; i++) {
            monitor.subTask(Util.getProperty(IMarker.MESSAGE, markers[i]));
            run(markers[i]);
        }
    }
}
