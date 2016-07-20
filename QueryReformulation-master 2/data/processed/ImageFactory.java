/***/
package org.eclipse.ui.internal.keys;

import java.util.HashMap;
import java.util.Map;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.internal.util.ImageSupport;

final class ImageFactory {

    private static ImageRegistry imageRegistry = new ImageRegistry();

    private static Map map = new HashMap();

    static {
        //$NON-NLS-1$//$NON-NLS-2$
        put("blank", "$nl$/icons/full/obj16/blank.png");
        //$NON-NLS-1$//$NON-NLS-2$
        put("change", "$nl$/icons/full/obj16/change_obj.png");
        /*
* TODO Remove these images from the registry if they are no longer
* needed.
*/
        //$NON-NLS-1$//$NON-NLS-2$
        put("minus", "$nl$/icons/full/obj16/delete_obj.png");
        //$NON-NLS-1$//$NON-NLS-2$
        put("plus", "$nl$/icons/full/obj16/add_obj.png");
    }

    static Image getImage(String key) {
        Image image = imageRegistry.get(key);
        if (image == null) {
            ImageDescriptor imageDescriptor = getImageDescriptor(key);
            if (imageDescriptor != null) {
                image = imageDescriptor.createImage(false);
                if (image == null) {
                    //$NON-NLS-1$
                    WorkbenchPlugin.log(ImageFactory.class + ": error creating image for " + key);
                }
                imageRegistry.put(key, image);
            }
        }
        return image;
    }

    static ImageDescriptor getImageDescriptor(String key) {
        ImageDescriptor imageDescriptor = (ImageDescriptor) map.get(key);
        if (imageDescriptor == null) {
            //$NON-NLS-1$
            WorkbenchPlugin.log(ImageFactory.class + ": no image descriptor for " + key);
        }
        return imageDescriptor;
    }

    private static void put(String key, String value) {
        map.put(key, ImageSupport.getImageDescriptor(value));
    }
}
