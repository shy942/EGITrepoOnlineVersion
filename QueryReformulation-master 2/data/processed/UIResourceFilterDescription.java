/***/
package org.eclipse.ui.ide.dialogs;

import org.eclipse.core.resources.FileInfoMatcherDescription;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceFilterDescription;
import org.eclipse.core.runtime.IPath;

/**
* @since 3.6
*/
public abstract class UIResourceFilterDescription {

    /**
* @return the filter path
*/
    public abstract IPath getPath();

    /**
* @return the project
*/
    public abstract IProject getProject();

    /**
* @return the filter type
*/
    public abstract int getType();

    /**
* @return the description
*/
    public abstract FileInfoMatcherDescription getFileInfoMatcherDescription();

    /**
* @param iResourceFilterDescription
* @return a UIResourceFilterDescription
*/
    public static UIResourceFilterDescription wrap(final IResourceFilterDescription iResourceFilterDescription) {
        return new UIResourceFilterDescription() {

            @Override
            public FileInfoMatcherDescription getFileInfoMatcherDescription() {
                return iResourceFilterDescription.getFileInfoMatcherDescription();
            }

            @Override
            public IPath getPath() {
                return iResourceFilterDescription.getResource().getProjectRelativePath();
            }

            @Override
            public IProject getProject() {
                return iResourceFilterDescription.getResource().getProject();
            }

            @Override
            public int getType() {
                return iResourceFilterDescription.getType();
            }
        };
    }
}
