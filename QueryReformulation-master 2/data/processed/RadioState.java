/***/
package org.eclipse.ui.handlers;

import java.util.Hashtable;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExecutableExtension;
import org.eclipse.jface.commands.PersistentState;
import org.eclipse.jface.preference.IPreferenceStore;

/**
* This state supports a radio-button like command, where the value of the
* parameterized command is stored as state. The command must define a state
* using the {@link #STATE_ID} id and a string commandParameter using the
* {@link #PARAMETER_ID} id. Menu contributions supplied by
* <code>org.eclipse.ui.menus</code> can then set the {@link #PARAMETER_ID}.
* <p>
* When parsing from the registry, this state understands two parameters:
* <code>default</code>, which is the default value for this item; and
* <code>persisted</code>, which is whether the state should be persisted
* between sessions. The <code>default</code> parameter has no default value and
* must be specified in one of its forms, and the <code>persisted</code>
* parameter defaults to <code>true</code>. If only one parameter is passed
* (i.e., using the class name followed by a colon), then it is assumed to be
* the <code>default</code> parameter.
* </p>
*
* @see HandlerUtil#updateRadioState(org.eclipse.core.commands.Command, String)
* @see HandlerUtil#matchesRadioState(org.eclipse.core.commands.ExecutionEvent)
* @since 3.5
*/
public final class RadioState extends PersistentState implements IExecutableExtension {

    /**
* The state ID for a radio state understood by the system.
*/
    //$NON-NLS-1$
    public static final String STATE_ID = "org.eclipse.ui.commands.radioState";

    /**
* The parameter ID for a radio state understood by the system.
*/
    //$NON-NLS-1$
    public static final String PARAMETER_ID = "org.eclipse.ui.commands.radioStateParameter";

    public  RadioState() {
        setShouldPersist(true);
    }

    @Override
    public void setInitializationData(IConfigurationElement config, String propertyName, Object data) {
        // persist by default
        boolean shouldPersist = true;
        if (data instanceof String) {
            setValue(data);
        } else if (data instanceof Hashtable) {
            final Hashtable parameters = (Hashtable) data;
            //$NON-NLS-1$
            final Object defaultObject = parameters.get("default");
            if (defaultObject instanceof String) {
                setValue(defaultObject);
            }
            //$NON-NLS-1$
            final Object persistedObject = parameters.get("persisted");
            if (persistedObject instanceof String && //$NON-NLS-1$
            "false".equalsIgnoreCase(((String) persistedObject)))
                shouldPersist = false;
        }
        setShouldPersist(shouldPersist);
    }

    @Override
    public void load(IPreferenceStore store, String preferenceKey) {
        if (!shouldPersist())
            return;
        final String value = store.getString(preferenceKey);
        if (value.length() != 0)
            setValue(value);
    }

    @Override
    public void save(IPreferenceStore store, String preferenceKey) {
        if (!shouldPersist())
            return;
        final Object value = getValue();
        if (value instanceof String) {
            store.setValue(preferenceKey, (String) value);
        }
    }

    @Override
    public void setValue(Object value) {
        if (!(value instanceof String))
            // we set only String values
            return;
        super.setValue(value);
    }
}
