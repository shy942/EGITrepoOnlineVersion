/***/
package org.eclipse.ui.commands;

/**
* <p>
* An instance of <code>IKeyConfigurationListener</code> can be used by
* clients to receive notification of changes to one or more instances of
* <code>IKeyConfiguration</code>.
* </p>
* <p>
* This interface may be implemented by clients.
* </p>
*
* @since 3.0
* @see IKeyConfiguration#addKeyConfigurationListener(IKeyConfigurationListener)
* @see IKeyConfiguration#removeKeyConfigurationListener(IKeyConfigurationListener)
* @see org.eclipse.ui.commands.KeyConfigurationEvent
* @deprecated Please use the bindings support in the "org.eclipse.jface"
* plug-in instead.
* @see org.eclipse.jface.bindings.ISchemeListener
*/
@Deprecated
@SuppressWarnings("all")
public interface IKeyConfigurationListener {

    /**
* Notifies that one or more attributes of an instance of <code>IKeyConfiguration</code>
* have changed. Specific details are described in the <code>KeyConfigurationEvent</code>.
*
* @param keyConfigurationEvent
*            the keyConfiguration event. Guaranteed not to be <code>null</code>.
*/
    @Deprecated
    void keyConfigurationChanged(KeyConfigurationEvent keyConfigurationEvent);
}
