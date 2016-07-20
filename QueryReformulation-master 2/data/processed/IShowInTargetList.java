/***/
package org.eclipse.ui.part;

/**
* Show In sources which need to provide additional entries to the Show In list of targets
* can provide this interface.
* The part can either directly implement this interface, or provide it
* via <code>IAdaptable.getAdapter(IShowInTargetList)</code>.
*
* @see IShowInSource
* @see IShowInTarget
*
* @since 2.1
*/
public interface IShowInTargetList {

    /**
* Returns the identifiers for the target parts to show.
* The target parts must be Show In targets.
*
* @return the identifiers for the target parts to show
*
* @see IShowInTarget
*/
    public String[] getShowInTargetIds();
}
