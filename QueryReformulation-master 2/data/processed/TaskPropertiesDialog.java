/***/
package org.eclipse.ui.views.tasklist;

import java.util.Map;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.dialogs.IDialogSettings;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;
import org.eclipse.ui.views.markers.internal.DialogTaskProperties;

/**
* Shows the properties of a new or existing task, or a problem.
*/
public class TaskPropertiesDialog extends DialogTaskProperties {

    //$NON-NLS-1$
    private static final String DIALOG_SETTINGS_SECTION = "TaskPropertiesDialogSettings";

    /**
* Creates the dialog. By default this dialog creates a new task. To set the
* resource and initial attributes for the new task, use
* <code>setResource</code> and <code>setInitialAttributes</code>. To
* show or modify an existing task, use <code>setMarker</code>.
*
* @param parentShell
*            the parent shell
*/
    public  TaskPropertiesDialog(Shell parentShell) {
        super(parentShell);
    }

    @Override
    protected IDialogSettings getDialogBoundsSettings() {
        IDialogSettings settings = IDEWorkbenchPlugin.getDefault().getDialogSettings();
        IDialogSettings section = settings.getSection(DIALOG_SETTINGS_SECTION);
        if (section == null) {
            section = settings.addNewSection(DIALOG_SETTINGS_SECTION);
        }
        return section;
    }

    /**
* Sets the marker to show or modify.
*
* @param marker the marker, or <code>null</code> to create a new marker
*/
    @Override
    public void setMarker(IMarker marker) {
        // Method is overridden because API is being inherited from an internal class.
        super.setMarker(marker);
    }

    /**
* Returns the marker being created or modified.
* For a new marker, this returns <code>null</code> until
* the dialog returns, but is non-null after.
*
* @return the marker
*/
    @Override
    public IMarker getMarker() {
        // Method is overridden because API is being inherited from an internal class.
        return super.getMarker();
    }

    /**
* Sets the resource to use when creating a new task.
* If not set, the new task is created on the workspace root.
*
* @param resource the resource
*/
    @Override
    public void setResource(IResource resource) {
        // Method is overridden because API is being inherited from an internal class.
        super.setResource(resource);
    }

    /**
* Returns the resource to use when creating a new task,
* or <code>null</code> if none has been set.
* If not set, the new task is created on the workspace root.
*
* @return the resource
*/
    @Override
    public IResource getResource() {
        // Method is overridden because API is being inherited from an internal class.
        return super.getResource();
    }

    /**
* Sets initial attributes to use when creating a new task.
* If not set, the new task is created with default attributes.
*
* @param initialAttributes the initial attributes
*/
    @Override
    public void setInitialAttributes(Map initialAttributes) {
        // Method is overridden because API is being inherited from an internal class.
        super.setInitialAttributes(initialAttributes);
    }

    /**
* Returns the initial attributes to use when creating a new task,
* or <code>null</code> if not set.
* If not set, the new task is created with default attributes.
*
* @return the initial attributes
*/
    @Override
    public Map getInitialAttributes() {
        // Method is overridden because API is being inherited from an internal class.
        return super.getInitialAttributes();
    }
}
