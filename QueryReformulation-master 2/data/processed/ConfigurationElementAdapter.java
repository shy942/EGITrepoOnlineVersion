/***/
package org.eclipse.e4.ui.internal.workbench;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.e4.ui.workbench.modeling.ModelHandlerBase;

public class ConfigurationElementAdapter extends ModelHandlerBase implements IAdapterFactory {

    //$NON-NLS-1$
    public static final String CLASS_IMPL = "classImpl";

    @Override
    public <T> T getAdapter(Object adaptableObject, Class<T> adapterType) {
        if (adapterType.isInstance(this)) {
            return adapterType.cast(this);
        }
        return null;
    }

    @Override
    public Class<?>[] getAdapterList() {
        return new Class[] { ModelHandlerBase.class };
    }

    @Override
    public Object[] getChildren(Object element, String id) {
        IConfigurationElement ice = (IConfigurationElement) element;
        IConfigurationElement[] kids = ice.getChildren(id);
        return kids;
    }

    @Override
    public Object getProperty(Object element, String id) {
        IConfigurationElement ice = (IConfigurationElement) element;
        // Construct a meaningful 'label'
        if ("label".equals(id)) {
            //$NON-NLS-1$
            //$NON-NLS-1$
            String idVal = ice.getAttribute("id");
            //$NON-NLS-1$
            String nameVal = ice.getAttribute("name");
            //$NON-NLS-1$
            String constructedName = "";
            if (nameVal != null) {
                constructedName = nameVal;
                if (idVal != null)
                    //$NON-NLS-1$ //$NON-NLS-2$
                    constructedName += " [" + idVal + "]";
            } else if (idVal != null) {
                constructedName = idVal;
            } else
                constructedName = ice.getName();
            return constructedName;
        } else if (CLASS_IMPL.equals(id)) {
            try {
                //$NON-NLS-1$
                return ice.createExecutableExtension("class");
            } catch (CoreException e) {
                e.printStackTrace();
            }
            return null;
        }
        return ice.getAttribute(id);
    }

    @Override
    public String[] getPropIds(Object element) {
        IConfigurationElement ice = (IConfigurationElement) element;
        return ice.getAttributeNames();
    }

    @Override
    public void setProperty(Object element, String id, Object value) {
        super.setProperty(element, id, value);
    }
}
