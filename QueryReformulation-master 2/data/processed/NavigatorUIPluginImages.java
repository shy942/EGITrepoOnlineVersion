/***/
package org.eclipse.ui.internal.navigator.resources.plugin;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Collections;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.internal.navigator.NavigatorPlugin;

/**
* Handles all images and icons for the ui.
*
* <p>
* <strong>EXPERIMENTAL</strong>. This class or interface has been added as part
* of a work in progress. There is a guarantee neither that this API will work
* nor that it will remain the same. Please do not use this API without
* consulting with the Platform/UI team.
* </p>
*
* @since 3.2
*/
public class NavigatorUIPluginImages {

    private static URL fgIconLocation;

    // Create image registry
    private static final ImageRegistry NAVIGATORUIPLUGIN_REGISTRY = NavigatorPlugin.getDefault().getImageRegistry();

    // Create the icon location
    static {
        //$NON-NLS-1$
        String pathSuffix = "icons/full/";
        fgIconLocation = FileLocator.find(NavigatorPlugin.getDefault().getBundle(), new Path(pathSuffix), Collections.<String, String>emptyMap());
    }

    /**
* Gets the current image.
*
* @param key
*            - Name of the icon.
* @return Image
*/
    public static Image get(String key) {
        return NAVIGATORUIPLUGIN_REGISTRY.get(key);
    }

    /**
* Create and returns a image descriptor.
*
* @param prefix
*            - Icon dir structure.
* @param name
*            - The name of the icon.
* @return ImageDescriptor
*/
    private static ImageDescriptor create(String prefix, String name) {
        return ImageDescriptor.createFromURL(makeIconFileURL(prefix, name));
    }

    /**
* Creates the icon url
*
* @param prefix
*            - Icon dir structure.
* @param name
*            - The name of the icon.
* @return URL
*/
    private static URL makeIconFileURL(String prefix, String name) {
        StringBuffer buffer = new StringBuffer(prefix);
        buffer.append(name);
        try {
            return new URL(fgIconLocation, buffer.toString());
        } catch (MalformedURLException ex) {
            return null;
        }
    }

    /**
* Sets the three image descriptors for enabled, disabled, and hovered to an
* action. The actions are retrieved from the *lcl16 folders.
*
* @param action
*            the action
* @param iconName
*            the icon name
*/
    public static void setLocalImageDescriptors(IAction action, String iconName) {
        //$NON-NLS-1$
        setImageDescriptors(action, "lcl16/", iconName);
    }

    /**
* Sets all available image descriptors for the given action.
*
* @param action
*            - The action associated with the icon.
* @param type
*            - The type of icon.
* @param relPath
*            - The relative path of the icon.
*/
    public static void setImageDescriptors(IAction action, String type, String relPath) {
        // /*relPath= relPath.substring(NAVIGATORUI_NAME_PREFIX_LENGTH);*/
        // action.setDisabledImageDescriptor(create("d" + type, relPath));
        // //$NON-NLS-1$
        // action.setHoverImageDescriptor(create("c" + type, relPath));
        // //$NON-NLS-1$
        //$NON-NLS-1$
        action.setImageDescriptor(create("e" + type, relPath));
    }
}
