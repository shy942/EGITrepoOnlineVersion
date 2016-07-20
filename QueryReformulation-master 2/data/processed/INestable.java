/***/
package org.eclipse.ui.internal.services;

/**
* </p>
* A service which can appear on a component which is wholly contained with
* another component. The component on which it appears can be active or
* inactive -- depending on the state of the application. For example, a
* workbench part is a component which appears within a workbench window. This
* workbench part can either be active or inactive, depending on what the user
* is doing.
* </p>
* <p>
* Services implement this interface, and are then notified by the component
* when the activation changes. It is the responsibility of the component to
* notify such services when the activation changes.
* </p>
* <p>
* This class is not intended for use outside of the
* <code>org.eclipse.ui.workbench</code> plug-in.
* </p>
* <p>
* <strong>PROVISIONAL</strong>. This class or interface has been added as part
* of a work in progress. There is a guarantee neither that this API will work
* nor that it will remain the same. Please do not use this API without
* consulting with the Platform/UI team.
* </p>
* <p>
* This class should eventually move to <code>org.eclipse.ui.services</code>.
* </p>
*
* @since 3.2
*/
public interface INestable {

    /**
* Notifies this service that the component within which it exists has
* become active. The service should modify its state as appropriate.
*
*/
    public void activate();

    /**
* Notifies this service that the component within which it exists has
* become inactive. The service should modify its state as appropriate.
*/
    public void deactivate();
}
