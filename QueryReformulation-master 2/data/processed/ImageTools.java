/***/
package org.eclipse.e4.ui.progress.internal;

import java.net.URL;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.e4.ui.progress.IProgressConstants;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

public class ImageTools {

    //$NON-NLS-1$
    private static final String ICONS_LOCATION = "/icons/full/";

    protected static ImageTools instance;

    private ImageRegistry imageRegistry = JFaceResources.getImageRegistry();

    public static ImageTools getInstance() {
        if (instance == null) {
            instance = new ImageTools();
        }
        return instance;
    }

    public ImageDescriptor getImageDescriptor(String relativePath) {
        //TODO E4 - the only place that requires org.eclipse.core.runtime
        URL url = FileLocator.find(Platform.getBundle(IProgressConstants.PLUGIN_ID), new Path(ICONS_LOCATION + relativePath), null);
        return ImageDescriptor.createFromURL(url);
    }

    public Image getImage(String relativePath, Display display) {
        return getImageDescriptor(relativePath).createImage(display);
    }

    public void putIntoRegistry(String name, String relativePath) {
        imageRegistry.put(name, getImageDescriptor(relativePath));
    }
}
