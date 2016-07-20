/***/
package org.eclipse.e4.ui.css.core.impl.dom;

import org.eclipse.e4.ui.css.core.dom.CSSExtendedProperties;
import org.eclipse.e4.ui.css.core.engine.CSSEngine;

/**
* {@link CSSExtendedProperties} implementation.
*/
public class CSSExtendedPropertiesImpl extends CSS2PropertiesImpl implements CSSExtendedProperties {

    public  CSSExtendedPropertiesImpl(Object widget, CSSEngine engine) {
        super(widget, engine);
    }
}
