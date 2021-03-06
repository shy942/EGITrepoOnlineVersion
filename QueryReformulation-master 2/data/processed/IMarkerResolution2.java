/***/
package org.eclipse.ui;

import org.eclipse.swt.graphics.Image;

/**
* Extends <code>IMarkerResolution</code>.  This interface should be used
* in place of <code>IMarkerResolution</code> if a description and/or image
* are desired.
*
* @since 3.0
*/
public interface IMarkerResolution2 extends IMarkerResolution {

    /**
* Returns optional additional information about the resolution.
* The additional information will be presented to assist the user
* in deciding if the selected proposal is the desired choice.
*
* @return the additional information or <code>null</code>
*/
    public String getDescription();

    /**
* Returns the image to be displayed in the list of resolutions.
* The image would typically be shown to the left of the display string.
*
* @return the image to be shown or <code>null</code> if no image is desired
*/
    public Image getImage();
}
