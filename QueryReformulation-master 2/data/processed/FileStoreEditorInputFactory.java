/***/
package org.eclipse.ui.ide;

import java.net.URI;
import java.net.URISyntaxException;
import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.ui.IElementFactory;
import org.eclipse.ui.IMemento;

/**
* Factory for saving and restoring a <code>FileStoreEditorInput</code>.
* The stored representation of a <code>FileStoreEditorInput</code> remembers
* the path of the editor input.
* <p>
* The workbench will automatically create instances of this class as required.
* It is not intended to be instantiated or subclassed by the client.</p>
*
* @since 3.3
*/
public class FileStoreEditorInputFactory implements IElementFactory {

    /**
* This factory's ID.
* <p>
* The editor plug-in registers a factory by this name with
* the <code>"org.eclipse.ui.elementFactories"<code> extension point.
*/
    //$NON-NLS-1$
    static final String ID = "org.eclipse.ui.ide.FileStoreEditorInputFactory";

    /**
* Saves the state of the given editor input into the given memento.
*
* @param memento the storage area for element state
* @param input the file editor input
*/
    static void saveState(IMemento memento, FileStoreEditorInput input) {
        URI uri = input.getURI();
        memento.putString(TAG_URI, uri.toString());
    }

    /**
* Tag for the URI string.
*/
    //$NON-NLS-1$
    private static final String TAG_URI = "uri";

    /*
* @see org.eclipse.ui.IElementFactory#createElement(org.eclipse.ui.IMemento)
*/
    @Override
    public IAdaptable createElement(IMemento memento) {
        // Get the file name.
        String uriString = memento.getString(TAG_URI);
        if (uriString == null)
            return null;
        URI uri;
        try {
            uri = new URI(uriString);
        } catch (URISyntaxException e) {
            return null;
        }
        try {
            return new FileStoreEditorInput(EFS.getStore(uri));
        } catch (CoreException e) {
            return null;
        }
    }
}
