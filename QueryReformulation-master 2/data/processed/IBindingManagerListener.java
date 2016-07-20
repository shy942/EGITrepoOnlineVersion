/***/
package org.eclipse.jface.bindings;

/**
* <p>
* An instance of <code>BindingManagerListener</code> can be used by clients to
* receive notification of changes to an instance of
* <code>BindingManager</code>.
* </p>
* <p>
* This interface may be implemented by clients.
* </p>
*
* @since 3.1
* @see BindingManager#addBindingManagerListener(IBindingManagerListener)
* @see org.eclipse.jface.bindings.BindingManager#addBindingManagerListener(IBindingManagerListener)
* @see BindingManagerEvent
*/
public interface IBindingManagerListener {

    /**
* Notifies that attributes inside an instance of <code>BindingManager</code> have changed.
* Specific details are described in the <code>BindingManagerEvent</code>.  Changes in the
* binding manager can cause the set of defined or active schemes or bindings to change.
*
* @param event
*            the binding manager event. Guaranteed not to be <code>null</code>.
*/
    void bindingManagerChanged(BindingManagerEvent event);
}
