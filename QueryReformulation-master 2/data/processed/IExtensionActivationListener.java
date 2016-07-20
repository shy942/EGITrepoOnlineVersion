/***/
package org.eclipse.ui.navigator;

/**
*
* An extension activation listener is notified whenever the activation state
* changes for one or more content extensions.
*
* @since 3.2
* @see INavigatorActivationService
* @see INavigatorActivationService#addExtensionActivationListener(IExtensionActivationListener)
* @see INavigatorActivationService#removeExtensionActivationListener(IExtensionActivationListener)
*/
public interface IExtensionActivationListener {

    /**
* @param aViewerId
*            The viewer id of the INavigatorContentService
* @param theNavigatorExtensionIds
*            A sorted array of updated extension ids
* @param isActive
*            The new activation state of the extensions
*/
    void onExtensionActivation(String aViewerId, String[] theNavigatorExtensionIds, boolean isActive);
}
