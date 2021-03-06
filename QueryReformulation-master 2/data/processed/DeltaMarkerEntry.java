/***/
package org.eclipse.ui.internal.views.markers;

import org.eclipse.core.resources.IMarkerDelta;
import org.eclipse.core.runtime.IPath;
import org.eclipse.ui.views.markers.MarkerViewUtil;
import org.eclipse.ui.views.markers.internal.MarkerTypesModel;
import com.ibm.icu.text.CollationKey;

/**
* The DeltaMarkerEntry is the class that wraps an {@link IMarkerDelta} for testing.
*
* @since 3.6
*
*/
class DeltaMarkerEntry extends MarkerEntry {

    private IMarkerDelta markerDelta;

    /**
* Create a new instance of the receiver.
* @param markerDelta
*
*/
    public  DeltaMarkerEntry(IMarkerDelta markerDelta) {
        super(markerDelta.getMarker());
        this.markerDelta = markerDelta;
    }

    @Override
    Object getAttributeValue(String attribute) {
        Object value = getCache().get(attribute);
        if (value == null) {
            value = markerDelta.getAttribute(attribute);
            if (value != null) {
                getCache().put(attribute, value);
            }
        }
        if (value instanceof CollationKey)
            return ((CollationKey) value).getSourceString();
        return value;
    }

    @Override
    long getCreationTime() {
        //return markerDelta.getCreationTime();
        return super.getCreationTime();
    }

    @Override
    long getID() {
        return markerDelta.getId();
    }

    @Override
    String getMarkerTypeName() {
        return MarkerTypesModel.getInstance().getType(markerDelta.getType()).getLabel();
    }

    @Override
    public String getPath() {
        String folder = getAttributeValue(MarkerViewUtil.PATH_ATTRIBUTE, null);
        if (folder != null) {
            return folder;
        }
        IPath path = markerDelta.getResource().getFullPath();
        // n is the number of segments
        int n = path.segmentCount() - 1;
        // in container, not path
        if (n <= 0) {
            return super.getPath();
        }
        folder = path.removeLastSegments(1).removeTrailingSeparator().toString();
        getCache().put(MarkerViewUtil.PATH_ATTRIBUTE, folder);
        return folder;
    }
}
