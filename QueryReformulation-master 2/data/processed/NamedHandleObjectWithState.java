/***/
package org.eclipse.core.commands;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.eclipse.core.commands.common.NamedHandleObject;
import org.eclipse.core.commands.common.NotDefinedException;

/**
* <p>
* A named handle object that can carry state with it. This state can be used to
* override the name or description.
* </p>
* <p>
* Clients may neither instantiate nor extend this class.
* </p>
*
* @since 3.2
*/
abstract class NamedHandleObjectWithState extends NamedHandleObject implements IObjectWithState {

    /**
* An empty string array, which can be returned from {@link #getStateIds()}
* if there is no state.
*/
    private static final String[] NO_STATE = new String[0];

    /**
* The map of states currently held by this command. If this command has no
* state, then this will be <code>null</code>.
*/
    private Map<String, State> states;

    /**
* Constructs a new instance of <code>NamedHandleObject<WithState/code>.
*
* @param id
*            The identifier for this handle; must not be <code>null</code>.
*/
    protected  NamedHandleObjectWithState(final String id) {
        super(id);
    }

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
    }

    @Override
    public final String getDescription() throws NotDefinedException {
        // Trigger a NDE.
        final String description = super.getDescription();
        final State descriptionState = getState(INamedHandleStateIds.DESCRIPTION);
        if (descriptionState != null) {
            final Object value = descriptionState.getValue();
            if (value != null) {
                return value.toString();
            }
        }
        return description;
    }

    @Override
    public final String getName() throws NotDefinedException {
        // Trigger a NDE, if necessary.
        final String name = super.getName();
        final State nameState = getState(INamedHandleStateIds.NAME);
        if (nameState != null) {
            final Object value = nameState.getValue();
            if (value != null) {
                return value.toString();
            }
        }
        return name;
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
            return NO_STATE;
        }
        final Set<String> stateIds = states.keySet();
        return stateIds.toArray(new String[stateIds.size()]);
    }

    @Override
    public void removeState(final String id) {
        if (id == null) {
            //$NON-NLS-1$
            throw new NullPointerException("Cannot remove a null id");
        }
        if (states != null) {
            states.remove(id);
            if (states.isEmpty()) {
                states = null;
            }
        }
    }
}
