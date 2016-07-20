/***/
package org.eclipse.e4.ui.css.swt.dom.preference;

import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.e4.ui.css.core.dom.IElementProvider;
import org.eclipse.e4.ui.css.core.engine.CSSEngine;
import org.w3c.dom.Element;

public class EclipsePreferencesProvider implements IElementProvider {

    @Override
    public Element getElement(Object element, CSSEngine engine) {
        if (element instanceof IEclipsePreferences) {
            return new EclipsePreferencesElement((IEclipsePreferences) element, engine);
        }
        return null;
    }
}
