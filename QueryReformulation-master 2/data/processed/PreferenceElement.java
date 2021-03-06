/***/
package org.eclipse.ui.internal.quickaccess;

import org.eclipse.jface.preference.IPreferenceNode;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.dialogs.WorkbenchPreferenceDialog;

/**
* @since 3.3
*
*/
public class PreferenceElement extends QuickAccessElement {

    //$NON-NLS-1$
    private static final String separator = " - ";

    private IPreferenceNode preferenceNode;

    private String prefix;

    /* package */
     PreferenceElement(IPreferenceNode preferenceNode, String prefix, PreferenceProvider preferenceProvider) {
        super(preferenceProvider);
        this.preferenceNode = preferenceNode;
        this.prefix = prefix;
    }

    @Override
    public void execute() {
        IWorkbenchWindow window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        if (window != null) {
            WorkbenchPreferenceDialog dialog = WorkbenchPreferenceDialog.createDialogOn(window.getShell(), preferenceNode.getId());
            dialog.open();
        }
    }

    @Override
    public String getId() {
        return preferenceNode.getId();
    }

    @Override
    public ImageDescriptor getImageDescriptor() {
        Image image = preferenceNode.getLabelImage();
        if (image != null) {
            ImageDescriptor descriptor = ImageDescriptor.createFromImage(image);
            return descriptor;
        }
        return null;
    }

    @Override
    public String getLabel() {
        if (prefix != null && prefix.length() > 0) {
            return preferenceNode.getLabelText() + separator + prefix;
        }
        return preferenceNode.getLabelText();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((preferenceNode == null) ? 0 : preferenceNode.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final PreferenceElement other = (PreferenceElement) obj;
        if (preferenceNode == null) {
            if (other.preferenceNode != null)
                return false;
        } else if (!preferenceNode.equals(other.preferenceNode))
            return false;
        return true;
    }
}
