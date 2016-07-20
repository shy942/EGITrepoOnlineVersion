/***/
package org.eclipse.e4.ui.services;

/**
* @noimplement This interface is not intended to be implemented by clients.
* @since 1.0
*/
public interface IServiceConstants {

    /**
* The current selection
* <p>
* This value can be <code>null</code> if there is no selection
* </p>
*/
    //$NON-NLS-1$
    public static final String ACTIVE_SELECTION = "org.eclipse.ui.selection";

    /**
* Due to the possibly misleading nature of this field's name, it has been
* replaced with {@link #ACTIVE_SELECTION}. All clients of this API should
* change their references to <code>ACTIVE_SELECTION</code>.
*
* @noreference This field is not intended to be referenced by clients.
*/
    @Deprecated
    public static final String SELECTION = ACTIVE_SELECTION;

    /**
* The set of the contexts that are currently active.
*/
    //$NON-NLS-1$
    public static final String ACTIVE_CONTEXTS = "activeContexts";

    /**
* The part active in a given context.
* <p>
* This value can be <code>null</code> if there is no active part in a given
* context.
* </p>
*/
    //$NON-NLS-1$
    public static final String ACTIVE_PART = "e4ActivePart";

    /**
* The currently active Shell.
*/
    //$NON-NLS-1$
    public static final String ACTIVE_SHELL = "activeShell";
}
