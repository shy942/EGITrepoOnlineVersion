/***/
package org.eclipse.jface.contexts;

/**
* <p>
* A list of well-known context identifiers. The context identifiers use the
* prefix "org.eclipse.ui" for historical reasons. These contexts exist as part
* of JFace.
* </p>
* <p>
* This interface should not be implemented or extended by clients.
* </p>
*
* @since 3.1
*/
public interface IContextIds {

    /**
* The identifier for the context that is active when a shell registered as
* a dialog.
*/
    //$NON-NLS-1$
    public static final String CONTEXT_ID_DIALOG = "org.eclipse.ui.contexts.dialog";

    /**
* The identifier for the context that is active when a shell is registered
* as either a window or a dialog.
*/
    //$NON-NLS-1$
    public static final String CONTEXT_ID_DIALOG_AND_WINDOW = "org.eclipse.ui.contexts.dialogAndWindow";

    /**
* The identifier for the context that is active when a shell is registered
* as a window.
*/
    //$NON-NLS-1$
    public static final String CONTEXT_ID_WINDOW = "org.eclipse.ui.contexts.window";
}
