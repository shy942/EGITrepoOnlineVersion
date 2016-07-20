/***/
package org.eclipse.ui.internal.ide.model;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.ui.views.properties.FilePropertySource;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.ResourcePropertySource;

/**
* Dispenses an <code>IPropertySource</code> adapter for the core resource objects.
*/
public class StandardPropertiesAdapterFactory implements IAdapterFactory {

    @Override
    public <T> T getAdapter(Object o, Class<T> adapterType) {
        if (adapterType.isInstance(o)) {
            return adapterType.cast(o);
        }
        if (adapterType == IPropertySource.class) {
            if (o instanceof IResource) {
                IResource resource = (IResource) o;
                if (resource.getType() == IResource.FILE) {
                    return adapterType.cast(new FilePropertySource((IFile) o));
                }
                return adapterType.cast(new ResourcePropertySource((IResource) o));
            }
        }
        return null;
    }

    @Override
    public Class<?>[] getAdapterList() {
        // org.eclipe.ui.views is an optional dependency
        try {
            //$NON-NLS-1$
            Class.forName("org.eclipse.ui.views.properties.IPropertySource");
        } catch (ClassNotFoundException e) {
            return new Class[0];
        }
        return new Class[] { IPropertySource.class };
    }
}
