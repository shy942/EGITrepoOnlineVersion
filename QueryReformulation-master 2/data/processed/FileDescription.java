/***/
package org.eclipse.ui.internal.ide.undo;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URI;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFileState;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceStatus;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubMonitor;

/**
* FileDescription is a lightweight description that describes a file to be
* created.
*
* This class is not intended to be instantiated or used by clients.
*
* @since 3.3
*
*/
public class FileDescription extends AbstractResourceDescription {

    String name;

    URI location;

    String charset;

    private IFileContentDescription fileContentDescription;

    /**
* Create a FileDescription that can be used to later restore the given
* file. The file typically already exists, but this constructor will not
* fail if the file does not exist.
*
* @param file
*            the file to be restored.
*/
    public  FileDescription(IFile file) {
        super(file);
        this.name = file.getName();
        try {
            this.charset = file.getCharset(false);
        } catch (CoreException e) {
        }
        if (file.isLinked()) {
            location = file.getLocationURI();
        }
    }

    /**
* Create a file description from the specified file handle. The handle does
* not exist, so no information should be derived from it. If a location
* path is specified, this file should represent a link to another location.
* The content description describes any state that should be used when the
* file resource is created.
*
* @param file
*            the file to be described
* @param linkLocation
*            the location of the file's link, or <code>null</code> if the
*            file is not linked
* @param fileContentDescription
*            the file content description that can be used to get
*            information about the file, such as its initial content
*/
    public  FileDescription(IFile file, URI linkLocation, IFileContentDescription fileContentDescription) {
        super(file);
        this.name = file.getName();
        this.location = linkLocation;
        this.charset = null;
        this.fileContentDescription = fileContentDescription;
    }

    @Override
    public void recordStateFromHistory(IResource resource, IProgressMonitor monitor) throws CoreException {
        Assert.isLegal(resource.getType() == IResource.FILE);
        if (location != null) {
            // file is linked, no need to record any history
            return;
        }
        IFileState[] states = ((IFile) resource).getHistory(monitor);
        if (states.length > 0) {
            final IFileState state = getMatchingFileState(states);
            this.fileContentDescription = new IFileContentDescription() {

                @Override
                public boolean exists() {
                    return state.exists();
                }

                @Override
                public InputStream getContents() throws CoreException {
                    return state.getContents();
                }

                @Override
                public String getCharset() throws CoreException {
                    return state.getCharset();
                }
            };
        }
    }

    @Override
    public IResource createResourceHandle() {
        IWorkspaceRoot workspaceRoot = parent.getWorkspace().getRoot();
        IPath fullPath = parent.getFullPath().append(name);
        return workspaceRoot.getFile(fullPath);
    }

    @Override
    public void createExistentResourceFromHandle(IResource resource, IProgressMonitor mon) throws CoreException {
        Assert.isLegal(resource instanceof IFile);
        if (resource.exists()) {
            return;
        }
        IFile fileHandle = (IFile) resource;
        SubMonitor subMonitor = SubMonitor.convert(mon, 200);
        subMonitor.setTaskName(UndoMessages.FileDescription_NewFileProgress);
        try {
            if (location != null) {
                fileHandle.createLink(location, IResource.ALLOW_MISSING_LOCAL, subMonitor.split(200));
            } else {
                InputStream contents = new ByteArrayInputStream(UndoMessages.FileDescription_ContentsCouldNotBeRestored.getBytes());
                // object and are restored in #restoreResourceAttributes
                if (fileContentDescription != null && fileContentDescription.exists()) {
                    contents = fileContentDescription.getContents();
                }
                fileHandle.create(contents, false, subMonitor.split(100));
                fileHandle.setCharset(charset, subMonitor.split(100));
            }
        } catch (CoreException e) {
            if (e.getStatus().getCode() == IResourceStatus.PATH_OCCUPIED) {
                fileHandle.refreshLocal(IResource.DEPTH_ZERO, null);
            } else {
                throw e;
            }
        }
    }

    @Override
    public boolean isValid() {
        if (location != null) {
            return super.isValid();
        }
        return super.isValid() && fileContentDescription != null && fileContentDescription.exists();
    }

    @Override
    public String getName() {
        return name;
    }

    /*
* Get the file state that matches this file description. The local time
* stamp is used to try to find a matching file state. If none can be found,
* the most recent copy of the file state is used.
*/
    private IFileState getMatchingFileState(IFileState[] states) {
        for (int i = 0; i < states.length; i++) {
            if (localTimeStamp == states[i].getModificationTime()) {
                return states[i];
            }
        }
        return states[0];
    }

    @Override
    protected void restoreResourceAttributes(IResource resource) throws CoreException {
        super.restoreResourceAttributes(resource);
        Assert.isLegal(resource instanceof IFile);
        IFile file = (IFile) resource;
        if (charset != null) {
            file.setCharset(charset, null);
        }
    }
}
