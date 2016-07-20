/***/
package org.eclipse.ui.dialogs;

/**
*	Implementors of this interface answer one of the prescribed return codes
*	when asked whether to overwrite a certain path string (which could
*	represent a resource path, a file system path, etc).
*/
public interface IOverwriteQuery {

    /**
* Return code indicating the operation should be canceled.
*/
    //$NON-NLS-1$
    public static final String CANCEL = "CANCEL";

    /**
* Return code indicating the entity should not be overwritten,
* but operation should not be canceled.
*/
    //$NON-NLS-1$
    public static final String NO = "NO";

    /**
* Return code indicating the entity should be overwritten.
*/
    //$NON-NLS-1$
    public static final String YES = "YES";

    /**
* Return code indicating the entity should be overwritten,
* and all subsequent entities should be overwritten without prompting.
*/
    //$NON-NLS-1$
    public static final String ALL = "ALL";

    /**
* Return code indicating the entity should not be overwritten,
* and all subsequent entities should not be overwritten without prompting.
*/
    //$NON-NLS-1$
    public static final String NO_ALL = "NOALL";

    /**
* Returns one of the return code constants declared on this interface,
* indicating whether the entity represented by the passed String should be overwritten.
* <p>
* This method may be called from a non-UI thread, in which case this method must run the query
* in a sync exec in the UI thread, if it needs to query the user.
* </p>
* @param pathString the path representing the entity to be overwritten
* @return one of the return code constants declared on this interface
*/
    String queryOverwrite(String pathString);
}
