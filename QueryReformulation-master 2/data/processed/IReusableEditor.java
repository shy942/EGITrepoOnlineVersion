/***/
package org.eclipse.ui;

/**
* Interface for reusable editors.
*
* An editors may support changing its input so that
* the workbench may change its contents instead of
* opening a new editor.
*/
public interface IReusableEditor extends IEditorPart {

    /**
* Sets the input to this editor.
*
* <p><b>Note:</b> Clients must fire the {@link IEditorPart#PROP_INPUT }
* property change within their implementation of
* <code>setInput()</code>.<p>
*
* @param input the editor input
*/
    public void setInput(IEditorInput input);
}
