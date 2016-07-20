/***/
package org.eclipse.jface.resource;

import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Image;

/**
* Manages SWT resources for a particular device.
*
* <p>
* IMPORTANT: in most cases clients should use a <code>LocalResourceManager</code> instead of a
* <code>DeviceResourceManager</code>. To create a resource manager on a particular display,
* use <code>new LocalResourceManager(JFaceResources.getResources(myDisplay))</code>.
* <code>DeviceResourceManager</code> should only be used directly when managing
* resources for a device other than a Display (such as a printer).
* </p>
*
* @see LocalResourceManager
*
* @since 3.1
*/
public final class DeviceResourceManager extends AbstractResourceManager {

    private Device device;

    private Image missingImage;

    @Override
    public Device getDevice() {
        return device;
    }

    /**
* Creates a new registry for the given device.
*
* @param device device to manage
*/
    public  DeviceResourceManager(Device device) {
        this.device = device;
    }

    @Override
    protected Object allocate(DeviceResourceDescriptor descriptor) throws DeviceResourceException {
        return descriptor.createResource(device);
    }

    @Override
    protected void deallocate(Object resource, DeviceResourceDescriptor descriptor) {
        descriptor.destroyResource(resource);
    }

    @Override
    protected Image getDefaultImage() {
        if (missingImage == null) {
            missingImage = ImageDescriptor.getMissingImageDescriptor().createImage();
        }
        return missingImage;
    }

    @Override
    public void dispose() {
        super.dispose();
        if (missingImage != null) {
            missingImage.dispose();
            missingImage = null;
        }
    }
}
