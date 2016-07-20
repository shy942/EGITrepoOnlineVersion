/***/
package org.eclipse.jface.resource;

import org.eclipse.swt.graphics.ImageData;

/**
* The image descriptor for a missing image.
* <p>
* Use <code>MissingImageDescriptor.getInstance</code> to
* access the singleton instance maintained in an
* internal state variable.
* </p>
*/
class MissingImageDescriptor extends ImageDescriptor {

    private static MissingImageDescriptor instance;

    /**
* Constructs a new missing image descriptor.
*/
    private  MissingImageDescriptor() {
        super();
    }

    @Override
    public ImageData getImageData() {
        return DEFAULT_IMAGE_DATA;
    }

    /**
* Returns the shared missing image descriptor instance.
*
* @return the image descriptor for a missing image
*/
    static MissingImageDescriptor getInstance() {
        if (instance == null) {
            instance = new MissingImageDescriptor();
        }
        return instance;
    }
}
