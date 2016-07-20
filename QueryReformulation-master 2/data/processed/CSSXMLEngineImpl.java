/***/
package org.eclipse.e4.ui.css.xml.engine;

import org.eclipse.e4.ui.css.core.dom.properties.css2.ICSSPropertyBackgroundHandler;
import org.eclipse.e4.ui.css.core.dom.properties.css2.ICSSPropertyFontHandler;
import org.eclipse.e4.ui.css.core.dom.properties.css2.ICSSPropertyTextHandler;
import org.eclipse.e4.ui.css.core.engine.CSSEngine;
import org.eclipse.e4.ui.css.core.impl.engine.CSSEngineImpl;
import org.eclipse.e4.ui.css.xml.properties.css2.CSSPropertyBackgroundXMLHandler;
import org.eclipse.e4.ui.css.xml.properties.css2.CSSPropertyFontXMLHandler;
import org.eclipse.e4.ui.css.xml.properties.css2.CSSPropertyTextXMLHandler;

/**
* {@link CSSEngine} implementation to apply style sheet to XML DOM.
*/
public class CSSXMLEngineImpl extends CSSEngineImpl {

    public  CSSXMLEngineImpl() {
        // Register XML CSS Property Background Handler
        super.registerCSSPropertyHandler(ICSSPropertyBackgroundHandler.class, CSSPropertyBackgroundXMLHandler.INSTANCE);
        // Register XML CSS Property Text Handler
        super.registerCSSPropertyHandler(ICSSPropertyTextHandler.class, CSSPropertyTextXMLHandler.INSTANCE);
        // Register XML CSS Property Font Handler
        super.registerCSSPropertyHandler(ICSSPropertyFontHandler.class, CSSPropertyFontXMLHandler.INSTANCE);
    }

    @Override
    public void reapply() {
    // TODO Auto-generated method stub
    }
}
