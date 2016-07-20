/***/
package org.eclipse.ui.internal.wizards.datatransfer;

import java.io.File;

/**
* An exception to report error when importing a project.
*
* @since 3.12
*
*/
public class CouldNotImportProjectException extends Exception {

    private static final long serialVersionUID = 5207202862271299462L;

    private File location;

    /**
*
* @param location
* @param cause
*/
    public  CouldNotImportProjectException(File location, Exception cause) {
        //$NON-NLS-1$
        super("Could not import project located at " + location.getAbsolutePath(), cause);
        this.location = location;
    }

    /**
*
* @param location
* @param message
*/
    public  CouldNotImportProjectException(File location, String message) {
        super(message);
        this.location = location;
    }

    /**
*
* @return the file which failed at being imported
*/
    public File getLocation() {
        return this.location;
    }
}
