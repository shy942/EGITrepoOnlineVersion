/***/
package org.eclipse.ui.internal.intro;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.intro.IIntroPart;

/**
* Describes an introduction extension.
*
* @since 3.0
*/
public interface IIntroDescriptor {

    /**
* Creates an instance of the intro part defined in the descriptor.
*/
    IIntroPart createIntro() throws CoreException;

    /**
* Returns the part id.
*
* @return the id of the part
*/
    public String getId();

    /**
* Returns the descriptor of the image for this part.
*
* @return the descriptor of the image to display next to this part
*/
    public ImageDescriptor getImageDescriptor();

    /**
* Return the label override string for this part.
*
* @return the label override string or <code>null</code> if one has not
*         been specified
* @since 3.2
*/
    public String getLabelOverride();
}
