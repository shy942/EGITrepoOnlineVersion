/***/
package org.eclipse.e4.ui.css.swt.properties.custom;

import org.eclipse.e4.ui.css.core.dom.properties.Gradient;
import org.eclipse.e4.ui.css.core.dom.properties.ICSSPropertyHandler;
import org.eclipse.e4.ui.css.core.engine.CSSEngine;
import org.eclipse.e4.ui.css.swt.helpers.CSSSWTColorHelper;
import org.eclipse.e4.ui.css.swt.properties.AbstractCSSPropertySWTHandler;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Control;
import org.w3c.dom.css.CSSValue;

public class CSSPropertySelectedTabsSWTHandler extends AbstractCSSPropertySWTHandler {

    public static final ICSSPropertyHandler INSTANCE = new CSSPropertySelectedTabsSWTHandler();

    @Override
    protected void applyCSSProperty(Control control, String property, CSSValue value, String pseudo, CSSEngine engine) throws Exception {
        if (!(control instanceof CTabFolder)) {
            return;
        }
        if (value.getCssValueType() == CSSValue.CSS_VALUE_LIST) {
            Gradient grad = (Gradient) engine.convert(value, Gradient.class, control.getDisplay());
            CTabFolder folder = ((CTabFolder) control);
            if (grad.getValues().isEmpty()) {
                folder.setSelectionBackground(null, null, true);
                return;
            }
            Color[] colors = CSSSWTColorHelper.getSWTColors(grad, folder.getDisplay(), engine);
            int[] percents = CSSSWTColorHelper.getPercents(grad);
            folder.setSelectionBackground(colors, percents, true);
        }
    }

    @Override
    protected String retrieveCSSProperty(Control control, String property, String pseudo, CSSEngine engine) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }
}
