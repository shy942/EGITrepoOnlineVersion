/***/
package org.eclipse.ui.tests.navigator.m12;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.ui.tests.navigator.m12.model.M1Project;

public class M1AdapterFactory implements IAdapterFactory {

    @Override
    public <T> T getAdapter(Object adaptableObject, Class<T> adapterType) {
        if (adaptableObject instanceof M1Project && IProject.class.isAssignableFrom(adapterType)) {
            IResource res = ((M1Project) adaptableObject).getResource();
            if (res instanceof IProject) {
                return adapterType.cast(res);
            }
        }
        return null;
    }

    @Override
    public Class<?>[] getAdapterList() {
        return new Class[] { IProject.class };
    }
}
