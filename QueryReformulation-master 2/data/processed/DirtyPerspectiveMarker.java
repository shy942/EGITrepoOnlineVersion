/***/
package org.eclipse.ui.internal;

/**
* @since 3.1
*/
public class DirtyPerspectiveMarker {

    /**
* @param id
*/
    public  DirtyPerspectiveMarker(String id) {
        perspectiveId = id;
    }

    public String perspectiveId;

    @Override
    public int hashCode() {
        return perspectiveId.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof DirtyPerspectiveMarker) {
            return perspectiveId.equals(((DirtyPerspectiveMarker) o).perspectiveId);
        }
        return false;
    }
}
