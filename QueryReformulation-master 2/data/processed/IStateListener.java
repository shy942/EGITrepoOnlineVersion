/***/
package org.eclipse.core.commands;

/**
* <p>
* A listener to changes in some state.
* </p>
* <p>
* Clients may implement, but must not extend this interface.
* </p>
*
* @since 3.2
*/
public interface IStateListener {

    /**
* Handles a change to the value in some state.
*
* @param state
*            The state that has changed; never <code>null</code>. The
*            value for this state has been updated to the new value.
* @param oldValue
*            The old value; may be anything.
*/
    public void handleStateChange(State state, Object oldValue);
}
