/***/
package org.eclipse.e4.ui.workbench.swt.util;

import org.eclipse.e4.ui.workbench.IResourceUtilities;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;

public interface ISWTResourceUtilities extends IResourceUtilities<ImageDescriptor> {

    /**
* Low-level utility to stamp an adornment onto a given Image.
*
* @param toAdorn
*            The image to be adorned (must not be null)
* @param adornment
*            The image to adorn with.
*
* @return The adorned image or 'toAdorn' if 'adornment' is null
*/
    public Image adornImage(Image toAdorn, Image adornment);
}
