/***/
package org.eclipse.e4.ui.css.swt.properties.custom;

import org.eclipse.e4.ui.css.core.engine.CSSEngine;
import org.eclipse.e4.ui.css.swt.properties.AbstractCSSPropertySWTHandler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.widgets.Control;
import org.w3c.dom.css.CSSPrimitiveValue;
import org.w3c.dom.css.CSSValue;

public class CSSPropertyTabPositionSWTHandler extends AbstractCSSPropertySWTHandler {

    @Override
    protected void applyCSSProperty(Control control, String property, CSSValue value, String pseudo, CSSEngine engine) throws Exception {
        if (!(control instanceof CTabFolder)) {
            return;
        }
        if ((value.getCssValueType() == CSSValue.CSS_PRIMITIVE_VALUE) && (((CSSPrimitiveValue) value).getPrimitiveType() == CSSPrimitiveValue.CSS_IDENT)) {
            String postion = ((CSSPrimitiveValue) value).getStringValue();
            if (postion.equalsIgnoreCase("bottom")) {
                ((CTabFolder) control).setTabPosition(SWT.BOTTOM);
            }
            if (postion.equalsIgnoreCase("top")) {
                ((CTabFolder) control).setTabPosition(SWT.TOP);
            }
        }
    }

    @Override
    protected String retrieveCSSProperty(Control control, String property, String pseudo, CSSEngine engine) throws Exception {
        if (control instanceof CTabFolder) {
            CTabFolder folder = (CTabFolder) control;
            int position = folder.getTabPosition();
            if (position == SWT.TOP) {
                return "top";
            } else {
                return "bottom";
            }
        }
        return null;
    }
}
