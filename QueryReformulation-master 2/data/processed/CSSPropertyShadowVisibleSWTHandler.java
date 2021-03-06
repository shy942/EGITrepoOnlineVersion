/***/
package org.eclipse.e4.ui.css.swt.properties.custom;

import org.eclipse.e4.ui.css.core.dom.properties.ICSSPropertyHandler;
import org.eclipse.e4.ui.css.core.engine.CSSEngine;
import org.eclipse.e4.ui.css.swt.properties.AbstractCSSPropertySWTHandler;
import org.eclipse.e4.ui.internal.css.swt.ICTabRendering;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabFolderRenderer;
import org.eclipse.swt.widgets.Control;
import org.w3c.dom.css.CSSValue;

public class CSSPropertyShadowVisibleSWTHandler extends AbstractCSSPropertySWTHandler {

    public static final ICSSPropertyHandler INSTANCE = new CSSPropertyShadowVisibleSWTHandler();

    @Override
    protected void applyCSSProperty(Control control, String property, CSSValue value, String pseudo, CSSEngine engine) throws Exception {
        if (!(control instanceof CTabFolder)) {
            return;
        }
        boolean shadowVisible = (Boolean) engine.convert(value, Boolean.class, null);
        CTabFolderRenderer renderer = ((CTabFolder) control).getRenderer();
        if (renderer instanceof ICTabRendering) {
            ((ICTabRendering) renderer).setShadowVisible(shadowVisible);
        }
    }

    @Override
    protected String retrieveCSSProperty(Control control, String property, String pseudo, CSSEngine engine) throws Exception {
        return null;
    }
}
