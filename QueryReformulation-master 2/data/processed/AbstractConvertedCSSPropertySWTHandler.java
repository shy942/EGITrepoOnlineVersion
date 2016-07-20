/***/
package org.eclipse.e4.ui.css.swt.properties;

import org.eclipse.e4.ui.css.core.engine.CSSEngine;
import org.eclipse.swt.widgets.Control;
import org.w3c.dom.css.CSSValue;

public abstract class AbstractConvertedCSSPropertySWTHandler extends AbstractCSSPropertySWTHandler {

    @Override
    protected void applyCSSProperty(Control control, String property, CSSValue value, String pseudo, CSSEngine engine) throws Exception {
        Object toType = getToType(value);
        if (toType != null) {
            Object newValue = engine.convert(value, toType, control.getDisplay());
            // if (newValue != null)
            applyCSSPropertyValue(control, property, newValue, pseudo, engine);
        // else
        // applyCSSPropertyValue(control, property, value, pseudo, engine);
        } else {
            applyCSSPropertyValue(control, property, value, pseudo, engine);
        }
    }

    protected String retrieveCSSProperty(Object value, String pseudo, CSSEngine engine) {
        Object toType = getToType(value);
        if (toType != null) {
            try {
                String newValue = engine.convert(value, toType, null);
                return newValue;
            } catch (Exception e) {
            }
        }
        return null;
    }

    protected abstract void applyCSSPropertyValue(Control control, String property, Object value, String pseudo, CSSEngine engine) throws Exception;

    protected abstract Object getToType(Object value);
}
