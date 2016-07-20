/***/
package org.eclipse.e4.ui.css.core.dom.properties;

import org.eclipse.e4.ui.css.core.engine.CSSEngine;

/**
* CSS Property Handler to intercept when all CSS Properties are applied.
*
* @version 1.0.0
* @author <a href="mailto:angelo.zerr@gmail.com">Angelo ZERR</a>
*
*/
public interface ICSSPropertyHandler2 {

    /**
* Callback method called when all CSS properties are applied.
*
* @param element
* @param engine
* @throws Exception
*/
    default void onAllCSSPropertiesApplyed(Object element, CSSEngine engine) throws Exception {
    // do nothing
    }

    /**
* Callback method called when all CSS properties are applied.
*
* @param element
* @param engine
* @param pseudo
* @throws Exception
*/
    default void onAllCSSPropertiesApplyed(Object element, CSSEngine engine, String pseudo) throws Exception {
        onAllCSSPropertiesApplyed(element, engine);
    }
}
