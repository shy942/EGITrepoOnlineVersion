/***/
package org.eclipse.ui.internal.ide.commands;

import org.eclipse.core.commands.AbstractParameterValueConverter;
import org.eclipse.core.commands.ParameterValueConversionException;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;

/**
* A command parameter value converter to convert between IResources and strings
* encoding the path of a resource.
*
* @since 3.2
*/
public final class ResourcePathConverter extends AbstractParameterValueConverter {

    @Override
    public final Object convertToObject(final String parameterValue) throws ParameterValueConversionException {
        final Path path = new Path(parameterValue);
        final IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
        final IResource resource = workspaceRoot.findMember(path);
        if ((resource == null) || (!resource.exists())) {
            throw new ParameterValueConversionException(//$NON-NLS-1$
            "parameterValue must be the path of an existing resource");
        }
        return resource;
    }

    @Override
    public final String convertToString(final Object parameterValue) throws ParameterValueConversionException {
        if (!(parameterValue instanceof IResource)) {
            throw new ParameterValueConversionException(//$NON-NLS-1$
            "parameterValue must be an IResource");
        }
        final IResource resource = (IResource) parameterValue;
        return resource.getFullPath().toString();
    }
}
