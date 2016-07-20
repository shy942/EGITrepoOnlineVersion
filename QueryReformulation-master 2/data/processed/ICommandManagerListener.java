/***/
package org.eclipse.ui.commands;

/**
* An instance of this interface can be used by clients to receive notification
* of changes to one or more instances of <code>ICommandManager</code>.
* <p>
* This interface may be implemented by clients.
* </p>
*
* @since 3.0
* @see ICommandManager#addCommandManagerListener(ICommandManagerListener)
* @see ICommandManager#removeCommandManagerListener(ICommandManagerListener)
* @deprecated Please use the "org.eclipse.core.commands" plug-in instead.
* @see org.eclipse.core.commands.ICommandManagerListener
*/
@Deprecated
@SuppressWarnings("all")
public interface ICommandManagerListener {

    /**
* Notifies that one or more properties of an instance of
* <code>ICommandManager</code> have changed. Specific details are
* described in the <code>CommandManagerEvent</code>.
*
* @param commandManagerEvent
*            the commandManager event. Guaranteed not to be
*            <code>null</code>.
*/
    @Deprecated
    void commandManagerChanged(CommandManagerEvent commandManagerEvent);
}