/***/
package org.eclipse.ui.internal.themes;

import java.util.HashSet;
import java.util.Set;
import org.eclipse.jface.resource.ColorRegistry;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

/**
* @since 3.0
*/
public class CascadingColorRegistry extends ColorRegistry {

    private ColorRegistry parent;

    private IPropertyChangeListener listener = new IPropertyChangeListener() {

        @Override
        public void propertyChange(PropertyChangeEvent event) {
            // value is NOT our current value.
            if (!hasOverrideFor(event.getProperty()))
                fireMappingChanged(event.getProperty(), event.getOldValue(), event.getNewValue());
        }
    };

    /**
* Create a new instance of this class.
*
* @param parent the parent registry
*/
    public  CascadingColorRegistry(ColorRegistry parent) {
        super(Display.getCurrent(), false);
        this.parent = parent;
        parent.addListener(listener);
    }

    @Override
    public Color get(String symbolicName) {
        if (super.hasValueFor(symbolicName)) {
            return super.get(symbolicName);
        }
        return parent.get(symbolicName);
    }

    @Override
    public Set getKeySet() {
        Set keyUnion = new HashSet(super.getKeySet());
        keyUnion.addAll(parent.getKeySet());
        return keyUnion;
    }

    @Override
    public RGB getRGB(String symbolicName) {
        if (super.hasValueFor(symbolicName)) {
            return super.getRGB(symbolicName);
        }
        return parent.getRGB(symbolicName);
    }

    @Override
    public boolean hasValueFor(String colorKey) {
        return super.hasValueFor(colorKey) || parent.hasValueFor(colorKey);
    }

    /**
* Returns whether this cascading registry has an override for the provided
* color key.
*
* @param colorKey the provided color key
* @return hether this cascading registry has an override
*/
    public boolean hasOverrideFor(String colorKey) {
        return super.hasValueFor(colorKey);
    }

    /**
* Disposes of all allocated resources.
*/
    public void dispose() {
        parent.removeListener(listener);
        PlatformUI.getWorkbench().getDisplay().asyncExec(displayRunnable);
    }
}
