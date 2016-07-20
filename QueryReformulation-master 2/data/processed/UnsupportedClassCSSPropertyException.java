/***/
package org.eclipse.e4.ui.css.core.exceptions;

import org.eclipse.e4.ui.css.core.dom.properties.ICSSPropertyHandler;
import org.eclipse.e4.ui.css.core.dom.properties.providers.CSSPropertyHandlerLazyProviderImpl;

/**
* Exception used when java Class CSS property is not retrieved.
*
* @version 1.0.0
* @author <a href="mailto:angelo.zerr@gmail.com">Angelo ZERR</a>
* @see CSSPropertyHandlerLazyProviderImpl
*/
public class UnsupportedClassCSSPropertyException extends Exception {

    private static final long serialVersionUID = 1L;

    private Class<?> clazz;

    public  UnsupportedClassCSSPropertyException(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public String getMessage() {
        return clazz + " must implement " + ICSSPropertyHandler.class.getName();
    }
}
