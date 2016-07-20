/***/
package org.eclipse.e4.ui.css.swt.properties.css2;

import org.eclipse.e4.ui.css.core.dom.properties.CSSBorderProperties;
import org.eclipse.e4.ui.css.core.dom.properties.ICSSPropertyHandler2;
import org.eclipse.e4.ui.css.core.dom.properties.css2.AbstractCSSPropertyBorderHandler;
import org.eclipse.e4.ui.css.core.dom.properties.css2.ICSSPropertyBorderHandler;
import org.eclipse.e4.ui.css.core.engine.CSSEngine;
import org.eclipse.e4.ui.css.core.impl.dom.properties.CSSBorderPropertiesImpl;
import org.eclipse.e4.ui.css.swt.CSSSWTConstants;
import org.eclipse.e4.ui.css.swt.helpers.CSSSWTHelpers;
import org.eclipse.e4.ui.css.swt.helpers.SWTElementHelpers;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.w3c.dom.css.CSSPrimitiveValue;
import org.w3c.dom.css.CSSValue;

public class CSSPropertyBorderSWTHandler extends AbstractCSSPropertyBorderHandler implements ICSSPropertyHandler2 {

    public static final ICSSPropertyBorderHandler INSTANCE = new CSSPropertyBorderSWTHandler();

    @Override
    public boolean applyCSSProperty(Object element, String property, CSSValue value, String pseudo, CSSEngine engine) throws Exception {
        Control control = SWTElementHelpers.getControl(element);
        if (control != null) {
            Composite parent = control.getParent();
            if (parent == null)
                return true;
            CSSBorderProperties border = (CSSBorderProperties) control.getData(CSSSWTConstants.CONTROL_CSS2BORDER_KEY);
            if (border == null) {
                border = new CSSBorderPropertiesImpl();
                control.setData(CSSSWTConstants.CONTROL_CSS2BORDER_KEY, border);
                parent.addPaintListener(CSSSWTHelpers.createBorderPaintListener(engine, control));
            }
            super.applyCSSProperty(border, property, value, pseudo, engine);
            if ((parent.getData("CSS_SUPPORTS_BORDERS") != null) && (value.getCssValueType() == CSSValue.CSS_PRIMITIVE_VALUE)) {
                int pixelValue = (int) ((CSSPrimitiveValue) value).getFloatValue(CSSPrimitiveValue.CSS_PT);
                if (property.equals("border-width")) {
                    ((FillLayout) parent.getLayout()).marginWidth = pixelValue;
                    ((FillLayout) parent.getLayout()).marginHeight = pixelValue;
                }
            }
            return true;
        } else {
            if (element instanceof CSSBorderProperties) {
                super.applyCSSProperty(element, property, value, pseudo, engine);
                return true;
            }
        }
        return false;
    }

    @Override
    public void onAllCSSPropertiesApplyed(Object element, CSSEngine engine) throws Exception {
        Control control = SWTElementHelpers.getControl(element);
        if (control != null) {
            Composite parent = control.getParent();
            if (parent != null)
                parent.redraw();
        }
    }
}
