/***/
package org.eclipse.e4.ui.css.swt.properties.custom;

import org.eclipse.e4.ui.css.core.dom.properties.ICSSPropertyHandler;
import org.eclipse.e4.ui.css.core.engine.CSSEngine;
import org.eclipse.e4.ui.css.swt.properties.AbstractCSSPropertySWTHandler;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.widgets.Control;
import org.w3c.dom.css.CSSValue;

public class CSSPropertySingleSWTHandler extends AbstractCSSPropertySWTHandler {

    public static final ICSSPropertyHandler INSTANCE = new CSSPropertySingleSWTHandler();

    @Override
    public void applyCSSProperty(Control control, String property, CSSValue value, String pseudo, CSSEngine engine) throws Exception {
        boolean isSingle = (Boolean) engine.convert(value, Boolean.class, null);
        if (control instanceof CTabFolder) {
            CTabFolder folder = (CTabFolder) control;
            folder.setSingle(isSingle);
        }
    }

    @Override
    public String retrieveCSSProperty(Control control, String property, String pseudo, CSSEngine engine) throws Exception {
        if (control instanceof CTabFolder) {
            CTabFolder folder = (CTabFolder) control;
            return Boolean.toString(folder.getSingle());
        }
        return null;
    }
}
