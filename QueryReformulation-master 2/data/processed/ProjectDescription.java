/***/
package org.eclipse.ui.internal.ide.undo;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;

/**
* ProjectDescription is a lightweight description that describes a project to
* be created.
*
* This class is not intended to be instantiated or used by clients.
*
* @since 3.3
*
*/
public class ProjectDescription extends ContainerDescription {

    private IProjectDescription projectDescription;

    private boolean openOnCreate = true;

    /**
* Create a project description from a specified project.
*
* @param project
*            The project to be described. The project must exist.
*/
    public  ProjectDescription(IProject project) {
        super(project);
        Assert.isLegal(project.exists());
        if (project.isOpen()) {
            try {
                this.projectDescription = project.getDescription();
            } catch (CoreException e) {
            }
        } else {
            openOnCreate = false;
        }
    }

    /**
* Create a project description from a specified IProjectDescription. Used
* when the project does not yet exist.
*
* @param projectDescription
*            the project description for the future project
*/
    public  ProjectDescription(IProjectDescription projectDescription) {
        super();
        this.projectDescription = projectDescription;
    }

    @Override
    public IResource createResourceHandle() {
        return ResourcesPlugin.getWorkspace().getRoot().getProject(getName());
    }

    @Override
    public void createExistentResourceFromHandle(IResource resource, IProgressMonitor monitor) throws CoreException {
        SubMonitor subMonitor = SubMonitor.convert(monitor, 200);
        Assert.isLegal(resource instanceof IProject);
        if (resource.exists()) {
            return;
        }
        IProject projectHandle = (IProject) resource;
        subMonitor.setTaskName(UndoMessages.FolderDescription_NewFolderProgress);
        if (projectDescription == null) {
            projectHandle.create(subMonitor.split(100));
        } else {
            projectHandle.create(projectDescription, subMonitor.split(100));
        }
        if (openOnCreate) {
            projectHandle.open(IResource.NONE, subMonitor.split(100));
        }
    }

    @Override
    public String getName() {
        if (projectDescription != null) {
            return projectDescription.getName();
        }
        return super.getName();
    }

    @Override
    public boolean verifyExistence(boolean checkMembers) {
        // We can only check members if the project is open.
        IProject projectHandle = (IProject) createResourceHandle();
        if (projectHandle.isAccessible()) {
            return super.verifyExistence(checkMembers);
        }
        return super.verifyExistence(false);
    }
}
