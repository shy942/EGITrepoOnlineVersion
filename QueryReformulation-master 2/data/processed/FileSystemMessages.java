/***/
package org.eclipse.ui.internal.ide.filesystem;

import org.eclipse.osgi.util.NLS;

/**
* FileSystemMessages is the class that handles the messages for the
* filesystem support.
*
*/
public class FileSystemMessages extends NLS {

    //$NON-NLS-1$
    private static final String BUNDLE_NAME = "org.eclipse.ui.internal.ide.filesystem.messages";

    static {
        // load message values from bundle file
        NLS.initializeMessages(BUNDLE_NAME, FileSystemMessages.class);
    }

    /**
* The name of the default file system.
*/
    public static String DefaultFileSystem_name;

    /**
* The label for file system selection.
*/
    public static String FileSystemSelection_title;
}
