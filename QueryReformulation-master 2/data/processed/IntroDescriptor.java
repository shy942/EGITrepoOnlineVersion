/***/
package org.eclipse.ui.internal.intro;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IPluginContribution;
import org.eclipse.ui.internal.registry.IWorkbenchRegistryConstants;
import org.eclipse.ui.intro.IIntroPart;
import org.eclipse.ui.intro.IntroContentDetector;
import org.eclipse.ui.plugin.AbstractUIPlugin;

/**
* Describes an introduction extension.
*
* @since 3.0
*/
public class IntroDescriptor implements IIntroDescriptor, IPluginContribution {

    private IConfigurationElement element;

    private ImageDescriptor imageDescriptor;

    /**
* Create a new IntroDescriptor for an extension.
*/
    public  IntroDescriptor(IConfigurationElement configElement) throws CoreException {
        element = configElement;
        if (configElement.getAttribute(IWorkbenchRegistryConstants.ATT_CLASS) == null) {
            throw new CoreException(new Status(IStatus.ERROR, configElement.getNamespace(), 0, //$NON-NLS-1$
            "Invalid extension (Missing class name): " + getId(), null));
        }
    }

    @Override
    public IIntroPart createIntro() throws CoreException {
        return (IIntroPart) element.createExecutableExtension(IWorkbenchRegistryConstants.ATT_CLASS);
    }

    public IntroContentDetector getIntroContentDetector() throws CoreException {
        if (element.getAttribute(IWorkbenchRegistryConstants.ATT_CONTENT_DETECTOR) == null) {
            return null;
        }
        return (IntroContentDetector) element.createExecutableExtension(IWorkbenchRegistryConstants.ATT_CONTENT_DETECTOR);
    }

    @Override
    public String getId() {
        return element.getAttribute(IWorkbenchRegistryConstants.ATT_ID);
    }

    @Override
    public ImageDescriptor getImageDescriptor() {
        if (imageDescriptor != null) {
            return imageDescriptor;
        }
        String iconName = element.getAttribute(IWorkbenchRegistryConstants.ATT_ICON);
        if (iconName == null) {
            return null;
        }
        imageDescriptor = AbstractUIPlugin.imageDescriptorFromPlugin(element.getNamespace(), iconName);
        return imageDescriptor;
    }

    @Override
    public String getLocalId() {
        return element.getAttribute(IWorkbenchRegistryConstants.ATT_ID);
    }

    @Override
    public String getPluginId() {
        return element.getNamespace();
    }

    /**
* Returns the configuration element.
*
* @return the configuration element
* @since 3.1
*/
    public IConfigurationElement getConfigurationElement() {
        return element;
    }

    @Override
    public String getLabelOverride() {
        return element.getAttribute(IWorkbenchRegistryConstants.ATT_LABEL);
    }
}
