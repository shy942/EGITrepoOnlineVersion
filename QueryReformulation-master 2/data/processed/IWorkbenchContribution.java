/***/
package org.eclipse.ui.menus;

import org.eclipse.ui.services.IServiceLocator;

/**
* Allow a menu contribution to be initialized with the appropriate service
* locator.
*
* @since 3.4
*/
public interface IWorkbenchContribution {

    /**
* The service locator for this contribution. It will potentially exist
* longer than the lifecycle of this specific contribution, so
* ContributionItems should remove themselves from any listeners or services
* in their dispose() calls.
*
* @param serviceLocator
*            the locator which services can be retrieved. Will not be
*            <code>null</code>
*/
    public void initialize(IServiceLocator serviceLocator);
}
