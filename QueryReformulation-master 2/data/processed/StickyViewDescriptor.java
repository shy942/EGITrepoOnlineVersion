/***/
package org.eclipse.ui.internal.registry;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPluginContribution;
import org.eclipse.ui.views.IStickyViewDescriptor;

/**
* @since 3.0
*/
public class StickyViewDescriptor implements IStickyViewDescriptor, IPluginContribution {

    private IConfigurationElement configurationElement;

    private String id;

    /**
* Folder constant for right sticky views.
*/
    //$NON-NLS-1$
    public static final String STICKY_FOLDER_RIGHT = "stickyFolderRight";

    /**
* Folder constant for left sticky views.
*/
    //$NON-NLS-1$
    public static final String STICKY_FOLDER_LEFT = "stickyFolderLeft";

    /**
* Folder constant for top sticky views.
*/
    //$NON-NLS-1$
    public static final String STICKY_FOLDER_TOP = "stickyFolderTop";

    /**
* Folder constant for bottom sticky views.
*/
    //$NON-NLS-1$
    public static final String STICKY_FOLDER_BOTTOM = "stickyFolderBottom";

    /**
* @param element
* @throws CoreException
*/
    public  StickyViewDescriptor(IConfigurationElement element) throws CoreException {
        this.configurationElement = element;
        id = configurationElement.getAttribute(IWorkbenchRegistryConstants.ATT_ID);
        if (id == null) {
            throw new CoreException(new Status(IStatus.ERROR, element.getNamespace(), 0, "Invalid extension (missing id) ", //$NON-NLS-1$
            null));
        }
    }

    /**
* Return the configuration element.
*
* @return the configuration element
*/
    public IConfigurationElement getConfigurationElement() {
        return configurationElement;
    }

    @Override
    public int getLocation() {
        int direction = IPageLayout.RIGHT;
        String location = configurationElement.getAttribute(IWorkbenchRegistryConstants.ATT_LOCATION);
        if (location != null) {
            if (location.equalsIgnoreCase("left")) {
                //$NON-NLS-1$
                direction = IPageLayout.LEFT;
            } else if (location.equalsIgnoreCase("top")) {
                //$NON-NLS-1$
                direction = IPageLayout.TOP;
            } else if (location.equalsIgnoreCase("bottom")) {
                //$NON-NLS-1$
                direction = IPageLayout.BOTTOM;
            //no else for right - it is the default value;
            }
        }
        return direction;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getLocalId() {
        return id;
    }

    @Override
    public String getPluginId() {
        return configurationElement.getContributor().getName();
    }

    @Override
    public boolean isCloseable() {
        boolean closeable = true;
        String closeableString = configurationElement.getAttribute(IWorkbenchRegistryConstants.ATT_CLOSEABLE);
        if (closeableString != null) {
            //$NON-NLS-1$
            closeable = !closeableString.equals("false");
        }
        return closeable;
    }

    @Override
    public boolean isMoveable() {
        boolean moveable = true;
        String moveableString = configurationElement.getAttribute(IWorkbenchRegistryConstants.ATT_MOVEABLE);
        if (moveableString != null) {
            //$NON-NLS-1$
            moveable = !moveableString.equals("false");
        }
        return moveable;
    }
}
