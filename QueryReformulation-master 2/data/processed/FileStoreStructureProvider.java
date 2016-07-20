/***/
package org.eclipse.ui.wizards.datatransfer;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.ui.internal.ide.IDEWorkbenchPlugin;

/**
* FileStoreStructureProvider is the structure provider for {@link IFileStore}
* based file structures.
*
* @since 3.2
*
*/
public class FileStoreStructureProvider implements IImportStructureProvider {

    /**
* Holds a singleton instance of this class.
*/
    public static final FileStoreStructureProvider INSTANCE = new FileStoreStructureProvider();

    @Override
    public List getChildren(Object element) {
        try {
            return Arrays.asList(((IFileStore) element).childStores(EFS.NONE, new NullProgressMonitor()));
        } catch (CoreException exception) {
            logException(exception);
            return new ArrayList();
        }
    }

    /**
* Log the exception.
*
* @param exception
*/
    private void logException(CoreException exception) {
        IDEWorkbenchPlugin.log(exception.getLocalizedMessage(), exception);
    }

    @Override
    public InputStream getContents(Object element) {
        try {
            return ((IFileStore) element).openInputStream(EFS.NONE, new NullProgressMonitor());
        } catch (CoreException exception) {
            logException(exception);
            return null;
        }
    }

    @Override
    public String getFullPath(Object element) {
        return ((IFileStore) element).toURI().getSchemeSpecificPart();
    }

    @Override
    public String getLabel(Object element) {
        return ((IFileStore) element).getName();
    }

    @Override
    public boolean isFolder(Object element) {
        return ((IFileStore) element).fetchInfo().isDirectory();
    }
}
