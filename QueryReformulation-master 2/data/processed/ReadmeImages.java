/***/
package org.eclipse.ui.examples.readmetool;

import java.net.MalformedURLException;
import java.net.URL;
import org.eclipse.jface.resource.ImageDescriptor;

/**
* Convenience class for storing references to image descriptors
* used by the readme tool.
*/
public class ReadmeImages {

    //$NON-NLS-1$
    static final URL BASE_URL = ReadmePlugin.getDefault().getBundle().getEntry("/");

    static final ImageDescriptor EDITOR_ACTION1_IMAGE;

    static final ImageDescriptor EDITOR_ACTION2_IMAGE;

    static final ImageDescriptor EDITOR_ACTION3_IMAGE;

    static final ImageDescriptor EDITOR_ACTION1_IMAGE_DISABLE;

    static final ImageDescriptor EDITOR_ACTION2_IMAGE_DISABLE;

    static final ImageDescriptor EDITOR_ACTION3_IMAGE_DISABLE;

    static final ImageDescriptor EDITOR_ACTION1_IMAGE_ENABLE;

    static final ImageDescriptor EDITOR_ACTION2_IMAGE_ENABLE;

    static final ImageDescriptor EDITOR_ACTION3_IMAGE_ENABLE;

    static final ImageDescriptor README_WIZARD_BANNER;

    static {
        //$NON-NLS-1$
        String iconPath = "icons/";
        //$NON-NLS-1$
        String prefix = iconPath + "ctool16/";
        //$NON-NLS-1$
        EDITOR_ACTION1_IMAGE = createImageDescriptor(prefix + "action1.gif");
        //$NON-NLS-1$
        EDITOR_ACTION2_IMAGE = createImageDescriptor(prefix + "action2.gif");
        //$NON-NLS-1$
        EDITOR_ACTION3_IMAGE = createImageDescriptor(prefix + "action3.gif");
        //$NON-NLS-1$
        prefix = iconPath + "dtool16/";
        EDITOR_ACTION1_IMAGE_DISABLE = createImageDescriptor(prefix + //$NON-NLS-1$
        "action1.gif");
        EDITOR_ACTION2_IMAGE_DISABLE = createImageDescriptor(prefix + //$NON-NLS-1$
        "action2.gif");
        EDITOR_ACTION3_IMAGE_DISABLE = createImageDescriptor(prefix + //$NON-NLS-1$
        "action3.gif");
        //$NON-NLS-1$
        prefix = iconPath + "etool16/";
        EDITOR_ACTION1_IMAGE_ENABLE = createImageDescriptor(prefix + //$NON-NLS-1$
        "action1.gif");
        EDITOR_ACTION2_IMAGE_ENABLE = createImageDescriptor(prefix + //$NON-NLS-1$
        "action2.gif");
        EDITOR_ACTION3_IMAGE_ENABLE = createImageDescriptor(prefix + //$NON-NLS-1$
        "action3.gif");
        //$NON-NLS-1$
        prefix = iconPath + "wizban/";
        README_WIZARD_BANNER = createImageDescriptor(prefix + //$NON-NLS-1$
        "newreadme_wiz.gif");
    }

    /**
* Utility method to create an <code>ImageDescriptor</code>
* from a path to a file.
*/
    private static ImageDescriptor createImageDescriptor(String path) {
        try {
            URL url = new URL(BASE_URL, path);
            return ImageDescriptor.createFromURL(url);
        } catch (MalformedURLException e) {
        }
        return ImageDescriptor.getMissingImageDescriptor();
    }
}
