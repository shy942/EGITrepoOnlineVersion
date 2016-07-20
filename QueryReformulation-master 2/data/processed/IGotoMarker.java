/***/
package org.eclipse.ui.ide;

import org.eclipse.core.resources.IMarker;

/**
* An adapter interface for editors, which allows the editor
* to reveal the position of a given marker.
*
* @since 3.0
*/
public interface IGotoMarker {

    /**
* Sets the cursor and selection state for an editor to
* reveal the position of the given marker.
*
* @param marker the marker
*/
    public void gotoMarker(IMarker marker);
}
