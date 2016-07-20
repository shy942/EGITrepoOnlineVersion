/***/
package org.eclipse.e4.ui.css.swt.properties.custom;

import org.eclipse.e4.ui.css.core.dom.properties.ICSSPropertyHandler;
import org.eclipse.e4.ui.css.core.engine.CSSEngine;
import org.eclipse.e4.ui.css.swt.dom.LinkElement;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Link;
import org.w3c.dom.css.CSSValue;

public class CSSPropertyLinkSWTHandler implements ICSSPropertyHandler {

    //$NON-NLS-1$
    public static final String SWT_LINK_FOREGROUND_COLOR = "swt-link-foreground-color";

    @Override
    public boolean applyCSSProperty(Object element, String property, CSSValue value, String pseudo, CSSEngine engine) throws Exception {
        // sanity checks for the CSS property
        if (!(element instanceof LinkElement)) {
            return false;
        }
        if (!(value.getCssValueType() == CSSValue.CSS_PRIMITIVE_VALUE)) {
            return false;
        }
        LinkElement linkElement = (LinkElement) element;
        Link link = linkElement.getLink();
        if (SWT_LINK_FOREGROUND_COLOR.equals(property)) {
            Color newColor = (Color) engine.convert(value, Color.class, link.getDisplay());
            link.setLinkForeground(newColor);
        }
        return false;
    }
}
