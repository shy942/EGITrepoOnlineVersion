/***/
package org.eclipse.ui.internal.ide;

import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.filesystem.URIUtil;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdapterFactory;
import org.eclipse.core.runtime.IPath;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IURIEditorInput;
import org.eclipse.ui.ide.FileStoreEditorInput;

/**
* Adapter factory for <code>IURIEditorInput</code>.
*
* @since 3.3
*/
public class IURIEditorInputAdapterFactory implements IAdapterFactory {

    private static class PathEditorInputAdapter extends FileStoreEditorInput implements IPathEditorInput {

        /**
* Creates a new adapter for the given file store.
*
* @param fileStore the file store;
*/
        public  PathEditorInputAdapter(IFileStore fileStore) {
            super(fileStore);
        }

        @Override
        public IPath getPath() {
            return URIUtil.toPath(getURI());
        }
    }

    /** The list of provided adapters. */
    private static final Class<?>[] ADAPTER_LIST = new Class[] { IPathEditorInput.class };

    @Override
    public <T> T getAdapter(Object adaptableObject, Class<T> adapterType) {
        if (IPathEditorInput.class.equals(adapterType)) {
            if (adaptableObject instanceof IURIEditorInput) {
                IFileStore fileStore;
                try {
                    fileStore = EFS.getStore(((IURIEditorInput) adaptableObject).getURI());
                    if (fileStore.getFileSystem() == EFS.getLocalFileSystem()) {
                        return adapterType.cast(new PathEditorInputAdapter(fileStore));
                    }
                } catch (CoreException e) {
                    return null;
                }
            }
        }
        return null;
    }

    @Override
    public Class<?>[] getAdapterList() {
        return ADAPTER_LIST;
    }
}
