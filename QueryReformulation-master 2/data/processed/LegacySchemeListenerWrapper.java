/***/
package org.eclipse.ui.internal.keys;

import org.eclipse.jface.bindings.BindingManager;
import org.eclipse.jface.bindings.ISchemeListener;
import org.eclipse.jface.bindings.SchemeEvent;
import org.eclipse.ui.commands.IKeyConfiguration;
import org.eclipse.ui.commands.IKeyConfigurationListener;
import org.eclipse.ui.commands.KeyConfigurationEvent;

/**
* A wrapper for old-style listeners to be hooked on to new style schemes.
*
* @since 3.1
*/
final class LegacySchemeListenerWrapper implements ISchemeListener {

    /**
* The binding manager; never <code>null</code>.
*/
    private final BindingManager bindingManager;

    /**
* The listener that is being wrapped. This value is never <code>null</code>.
*/
    private final IKeyConfigurationListener listener;

    /**
* Constructs a new instance of <code>SchemeListenerWrapper</code> with
* the given listener.
*
* @param listener
*            The listener to be wrapped; must mot be <code>null</code>.
*/
     LegacySchemeListenerWrapper(final IKeyConfigurationListener listener, final BindingManager bindingManager) {
        if (listener == null) {
            //$NON-NLS-1$
            throw new NullPointerException("Cannot wrap a null listener");
        }
        if (bindingManager == null) {
            throw new NullPointerException(//$NON-NLS-1$
            "Cannot wrap a listener without a binding manager");
        }
        this.listener = listener;
        this.bindingManager = bindingManager;
    }

    @Override
    public final boolean equals(final Object object) {
        if (object instanceof LegacySchemeListenerWrapper) {
            final LegacySchemeListenerWrapper wrapper = (LegacySchemeListenerWrapper) object;
            return listener.equals(wrapper.listener);
        }
        if (object instanceof IKeyConfigurationListener) {
            final IKeyConfigurationListener other = (IKeyConfigurationListener) object;
            return listener.equals(other);
        }
        return false;
    }

    @Override
    public final int hashCode() {
        return listener.hashCode();
    }

    @Override
    public final void schemeChanged(final SchemeEvent schemeEvent) {
        final IKeyConfiguration keyConfiguration = new SchemeLegacyWrapper(schemeEvent.getScheme(), bindingManager);
        final boolean definedChanged = schemeEvent.isDefinedChanged();
        final boolean nameChanged = schemeEvent.isNameChanged();
        final boolean parentIdChanged = schemeEvent.isParentIdChanged();
        listener.keyConfigurationChanged(new KeyConfigurationEvent(keyConfiguration, false, definedChanged, nameChanged, parentIdChanged));
    }
}
