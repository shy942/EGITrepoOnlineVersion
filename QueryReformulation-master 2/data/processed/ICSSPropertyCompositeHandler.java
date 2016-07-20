/***/
package org.eclipse.e4.ui.css.core.dom.properties;

/**
* CSS Property Handler interface to manage composite CSS Property (ex:
* background is CSS Property composed with background-color,
* background-image..).
*
* @version 1.0.0
* @author <a href="mailto:angelo.zerr@gmail.com">Angelo ZERR</a>
*
*/
public interface ICSSPropertyCompositeHandler extends ICSSPropertyHandler {

    /**
* Return true if <code>property</code> is CSS Property composite and
* false otherwise.
*
* @param property
* @return
*/
    public boolean isCSSPropertyComposite(String property);

    /**
* Return the CSS Properties names if the CSS Property <code>property</code>
* is composite and null otherwise.
*
* @param property
* @return
*/
    public String[] getCSSPropertiesNames(String property);
}
