/***/
package org.eclipse.ui.ide;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorRegistry;

/**
* This interface represents a strategy for choosing an IEditorDescriptor for a
* given file name. It's used by the
* org.eclipse.ui.ide.unassociatedEditorStrategy extension point.
*
* @since 3.12
*/
public interface IUnassociatedEditorStrategy {

    /**
* @param fileName
*            Name of the file to open
* @param editorRegistry
*            the IDE editor registry
* @return an {@link IEditorDescriptor} for editor to use to open this file,
*         or null if no editor was resolved for that file name.
* @throws CoreException
*             in case descriptor lookup fails with an error
* @throws OperationCanceledException
*             in case descriptor lookup was cancelled by the user
*/
    IEditorDescriptor getEditorDescriptor(String fileName, IEditorRegistry editorRegistry) throws CoreException, OperationCanceledException;
}
