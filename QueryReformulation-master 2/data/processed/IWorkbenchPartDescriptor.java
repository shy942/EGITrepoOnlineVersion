/***/
package org.eclipse.ui;

import org.eclipse.jface.resource.ImageDescriptor;

/**
* Description of a workbench part. The part descriptor contains
* the information needed to create part instances.
* <p>
* This interface is not intended to be implemented by clients.
* </p>
* @noimplement This interface is not intended to be implemented by clients.
*/
public interface IWorkbenchPartDescriptor {

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
* Returns the label to show for this part.
*
* @return the part label
*/
    public String getLabel();
}
