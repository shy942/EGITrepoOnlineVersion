/***/
package org.eclipse.core.commands;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
* <p>
* An abstract implementation of {@link IObjectWithState}. This provides basic
* handling for adding and remove state. When state is added, the handler
* attaches itself as a listener and fire a handleStateChange event to notify
* this handler. When state is removed, the handler removes itself as a
* listener.
* </p>
* <p>
* Clients may extend this class.
* </p>
*
* @since 3.2
*/
public abstract class AbstractHandlerWithState extends AbstractHandler implements IObjectWithState, IStateListener {

    /**
* The map of states currently held by this handler. If this handler has no
* state (generally, when inactive), then this will be <code>null</code>.
*/
    private Map<String, State> states;

    private static final String[] EMPTY = new String[0];

    /**
* <p>
* Adds a state to this handler. This will add this handler as a listener to
* the state, and then fire a handleStateChange so that the handler can
* respond to the incoming state.
* </p>
* <p>
* Clients may extend this method, but they should call this super method
* first before doing anything else.
* </p>
*
* @param stateId
*            The identifier indicating the type of state being added; must
*            not be <code>null</code>.
* @param state
*            The state to add; must not be <code>null</code>.
*/
    @Override
    public void addState(final String stateId, final State state) {
        if (state == null) {
            //$NON-NLS-1$
            throw new NullPointerException("Cannot add a null state");
        }
        if (states == null) {
            states = new HashMap(3);
        }
        states.put(stateId, state);
        state.addListener(this);
        handleStateChange(state, null);
    }

    @Override
    public final State getState(final String stateId) {
        if ((states == null) || (states.isEmpty())) {
            return null;
        }
        return states.get(stateId);
    }

    @Override
    public final String[] getStateIds() {
        if ((states == null) || (states.isEmpty())) {
            return EMPTY;
        }
        final Set<String> stateIds = states.keySet();
        return stateIds.toArray(new String[stateIds.size()]);
    }

    /**
* <p>
* Removes a state from this handler. This will remove this handler as a
* listener to the state. No event is fired to notify the handler of this
* change.
* </p>
* <p>
* Clients may extend this method, but they should call this super method
* first before doing anything else.
* </p>
*
* @param stateId
*            The identifier of the state to remove; must not be
*            <code>null</code>.
*/
    @Override
    public void removeState(final String stateId) {
        if (stateId == null) {
            //$NON-NLS-1$
            throw new NullPointerException("Cannot remove a null state");
        }
        if (states == null) {
            return;
        }
        final State state = states.get(stateId);
        if (state != null) {
            state.removeListener(this);
            if (states != null) {
                states.remove(stateId);
                if (states.isEmpty()) {
                    states = null;
                }
            }
        }
    }
}
