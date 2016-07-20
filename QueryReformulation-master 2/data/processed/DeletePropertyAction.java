/***/
package org.eclipse.ui.examples.navigator.actions;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionDelegate;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.examples.navigator.PropertiesTreeData;
import org.eclipse.ui.internal.examples.navigator.Activator;

/**
* A sample action that can delete a PropertiesTreeData item from a property file.
*
* @since 3.2
*/
public class DeletePropertyAction extends ActionDelegate {

    private IStructuredSelection selection = StructuredSelection.EMPTY;

    /* (non-Javadoc)
* @see org.eclipse.ui.actions.ActionDelegate#selectionChanged(org.eclipse.jface.action.IAction, org.eclipse.jface.viewers.ISelection)
*/
    public void selectionChanged(IAction action, ISelection sel) {
        if (sel instanceof IStructuredSelection)
            selection = (IStructuredSelection) sel;
        else
            selection = StructuredSelection.EMPTY;
    }

    /* (non-Javadoc)
* @see org.eclipse.ui.actions.ActionDelegate#run(org.eclipse.jface.action.IAction)
*/
    public void run(IAction action) {
        WorkspaceModifyOperation deletePropertyOperation = new WorkspaceModifyOperation() {

            /* (non-Javadoc)
* @see org.eclipse.ui.actions.WorkspaceModifyOperation#execute(org.eclipse.core.runtime.IProgressMonitor)
*/
            protected void execute(IProgressMonitor monitor) throws CoreException {
                // In production code, you should always externalize strings, but this is an example.
                //$NON-NLS-1$
                monitor.beginTask("Deleting property from selection", 5);
                try {
                    if (selection.size() == 1) {
                        Object firstElement = selection.getFirstElement();
                        if (firstElement instanceof PropertiesTreeData) {
                            PropertiesTreeData data = (PropertiesTreeData) firstElement;
                            IFile propertiesFile = data.getFile();
                            monitor.worked(1);
                            if (propertiesFile != null && propertiesFile.isAccessible()) {
                                try {
                                    // load the model
                                    Properties properties = new Properties();
                                    properties.load(propertiesFile.getContents());
                                    monitor.worked(1);
                                    // delete the property
                                    properties.remove(data.getName());
                                    monitor.worked(1);
                                    // persist the model to a temporary storage medium (byte[])
                                    ByteArrayOutputStream output = new ByteArrayOutputStream();
                                    properties.store(output, null);
                                    monitor.worked(1);
                                    // set the contents of the properties file
                                    propertiesFile.setContents(new ByteArrayInputStream(output.toByteArray()), IResource.FORCE | IResource.KEEP_HISTORY, monitor);
                                    monitor.worked(1);
                                } catch (IOException e) {
                                    Activator.logError(0, "Could not delete property!", e);
                                    MessageDialog.openError(Display.getDefault().getActiveShell(), "Error Deleting Property", "Could not delete property!");
                                }
                            } else
                                // shouldn't happen, but handle error condition
                                MessageDialog.openError(Display.getDefault().getActiveShell(), //$NON-NLS-1$
                                "Error Deleting Property", //$NON-NLS-1$
                                "The properties file was not accessible!");
                        } else
                            // shouldn't happen, but handle error condition
                            MessageDialog.openError(Display.getDefault().getActiveShell(), //$NON-NLS-1$
                            "Error Deleting Property", //$NON-NLS-1$
                            "The element that was selected was not of the right type.");
                    } else
                        // shouldn't happen, but handle error condition
                        MessageDialog.openError(Display.getDefault().getActiveShell(), //$NON-NLS-1$
                        "Error Deleting Property", //$NON-NLS-1$
                        "An invalid number of properties were selected.");
                } finally {
                    monitor.done();
                }
            }
        };
        try {
            PlatformUI.getWorkbench().getProgressService().run(true, false, deletePropertyOperation);
        } catch (InvocationTargetException e) {
            Activator.logError(0, "Could not delete property!", e);
            MessageDialog.openError(Display.getDefault().getActiveShell(), "Error Deleting Property", "Could not delete property!");
        } catch (InterruptedException e) {
            Activator.logError(0, "Could not delete property!", e);
            MessageDialog.openError(Display.getDefault().getActiveShell(), "Error Deleting Property", "Could not delete property!");
        }
    }
}
