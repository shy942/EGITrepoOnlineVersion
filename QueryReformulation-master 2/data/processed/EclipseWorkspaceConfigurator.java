/***/
package org.eclipse.ui.internal.wizards.datatransfer;

import java.io.File;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.ui.wizards.datatransfer.ProjectConfigurator;

/**
* Detects Eclipse workspace (folder with .metadata)
*/
public class EclipseWorkspaceConfigurator implements ProjectConfigurator {

    @Override
    public Set<File> findConfigurableLocations(File root, IProgressMonitor monitor) {
        return Collections.emptySet();
    }

    @Override
    public boolean shouldBeAnEclipseProject(IContainer container, IProgressMonitor monitor) {
        //$NON-NLS-1$
        return container.getFolder(new Path(".metadata")).exists();
    }

    @Override
    public Set<IFolder> getFoldersToIgnore(IProject project, IProgressMonitor monitor) {
        Set<IFolder> res = new HashSet();
        //$NON-NLS-1$
        res.add(project.getFolder(new Path(".metadata")));
        return res;
    }

    @Override
    public boolean canConfigure(IProject project, Set<IPath> ignoredPaths, IProgressMonitor monitor) {
        return false;
    }

    @Override
    public void configure(IProject project, Set<IPath> excludedDirectories, IProgressMonitor monitor) {
    // Nothing to do
    }
}
