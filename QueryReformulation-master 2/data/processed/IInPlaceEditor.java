/***/
package org.eclipse.ui;

/**
* Interface for editor parts that represent an in-place style editor.
* <p>
* This interface is not intended to be implemented or extended by clients.
* </p>
*
* @see org.eclipse.ui.IEditorDescriptor#isOpenInPlace()
* @since 3.0
* @noimplement This interface is not intended to be implemented by clients.
*/
public interface IInPlaceEditor extends IEditorPart {

    /**
* Informs the in-place editor that the system file it is
* editing was deleted by the application.
*/
    public void sourceDeleted();

    /**
* Informs the in-place editor that the system file it is
* editing was moved or renamed by the application.
*
* @param input the new in-place editor input to use
*/
    public void sourceChanged(IInPlaceEditorInput input);
}
