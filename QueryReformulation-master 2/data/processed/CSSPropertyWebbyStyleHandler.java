/***/
package org.eclipse.e4.ui.css.swt.properties.custom;

import org.eclipse.e4.ui.css.core.dom.properties.ICSSPropertyHandler;
import org.eclipse.e4.ui.css.core.engine.CSSEngine;
import org.eclipse.e4.ui.css.swt.properties.AbstractCSSPropertySWTHandler;
import org.eclipse.swt.widgets.Control;
import org.w3c.dom.css.CSSValue;

public class CSSPropertyWebbyStyleHandler extends AbstractCSSPropertySWTHandler {

    public static final ICSSPropertyHandler INSTANCE = new CSSPropertyWebbyStyleHandler();

    @Override
    public void applyCSSProperty(Control control, String property, CSSValue value, String pseudo, CSSEngine engine) throws Exception {
    // FIXME: Commented out since it only causes a compile warning. Filed https://bugs.eclipse.org/415436 to track this.
    //		boolean bool = (Boolean)engine.convert(value, Boolean.class, null);
    }

    @Override
    public String retrieveCSSProperty(Control control, String property, String pseudo, CSSEngine engine) throws Exception {
        return null;
    }
}
