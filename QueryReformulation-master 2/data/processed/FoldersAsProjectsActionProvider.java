/***/
package org.eclipse.ui.internal.navigator.resources.actions;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.runtime.Adapters;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.CommonViewer;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonMenuConstants;

public class FoldersAsProjectsActionProvider extends CommonActionProvider {

    private CommonViewer viewer;

    @Override
    public void init(ICommonActionExtensionSite aSite) {
        viewer = (CommonViewer) aSite.getStructuredViewer();
    }

    @Override
    public void fillContextMenu(IMenuManager aMenu) {
        IStructuredSelection selection = (IStructuredSelection) getContext().getSelection();
        if (selection.size() != 1) {
            return;
        }
        Object object = selection.getFirstElement();
        IFolder folder = Adapters.adapt(object, IFolder.class);
        if (folder == null) {
            return;
        }
        if (folder.getFile(IProjectDescription.DESCRIPTION_FILE_NAME).exists()) {
            for (IProject project : folder.getWorkspace().getRoot().getProjects()) {
                if (project.getLocation().equals(folder.getLocation())) {
                    // project already in workspace
                    SelectProjectForFolderAction action = new SelectProjectForFolderAction(project, this.viewer);
                    aMenu.appendToGroup(ICommonMenuConstants.GROUP_OPEN, action);
                    return;
                }
            }
            OpenFolderAsProjectAction action = new OpenFolderAsProjectAction(folder, this.viewer);
            aMenu.prependToGroup(ICommonMenuConstants.GROUP_PORT, action);
        }
    }
}
