/***/
package org.eclipse.ui.internal.ide.misc;

import org.eclipse.core.filesystem.IFileInfo;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.filtermatchers.CompoundFileInfoMatcher;
import org.eclipse.core.runtime.CoreException;

/**
* A Resource Filter Type Factory for supporting the AND logical preposition
*/
public class AndFileInfoMatcher extends CompoundFileInfoMatcher {

    @Override
    public boolean matches(IContainer parent, IFileInfo fileInfo) throws CoreException {
        for (int i = 0; i < matchers.length; i++) {
            if (!matchers[i].matches(parent, fileInfo))
                return false;
        }
        return true;
    }
}
