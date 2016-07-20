/***/
package org.eclipse.ui.internal.ide.misc;

import org.eclipse.core.filesystem.IFileInfo;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.filtermatchers.AbstractFileInfoMatcher;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.ui.internal.ide.StringMatcher;

/**
* A file info filter that uses a simple string matcher to match on file name.
*/
public class StringFileInfoMatcher extends AbstractFileInfoMatcher {

    /**
*/
    //$NON-NLS-1$
    public static String ID = "org.eclipse.ui.ide.patternFilterMatcher";

    StringMatcher matcher = null;

    /**
* Creates a new factory for this filter type.
*/
    public  StringFileInfoMatcher() {
    }

    @Override
    public void initialize(IProject project, Object arguments) throws CoreException {
        if ((arguments instanceof String) && ((String) arguments).length() > 0)
            matcher = new StringMatcher((String) arguments, true, false);
    }

    @Override
    public boolean matches(IContainer parent, IFileInfo fileInfo) throws CoreException {
        if (matcher != null)
            return matcher.match(fileInfo.getName());
        return false;
    }
}
