/***/
package org.eclipse.ui;

/**
* An editor matching strategy allows editor extensions to provide their own
* algorithm for matching the input of an open editor of that type to a
* given editor input.  This is used to find a matching editor during
* {@link org.eclipse.ui.IWorkbenchPage#openEditor(IEditorInput, String, boolean)} and
* {@link org.eclipse.ui.IWorkbenchPage#findEditor(IEditorInput)}.
*
* @since 3.1
*/
public interface IEditorMatchingStrategy {

    /**
* Returns whether the editor represented by the given editor reference
* matches the given editor input.
* <p>
* Implementations should inspect the given editor input first,
* and try to reject it early before calling <code>IEditorReference.getEditorInput()</code>,
* since that method may be expensive.
* </p>
*
* @param editorRef the editor reference to match against
* @param input the editor input to match
* @return <code>true</code> if the editor matches the given editor input,
*   <code>false</code> if it does not match
*/
    boolean matches(IEditorReference editorRef, IEditorInput input);
}
