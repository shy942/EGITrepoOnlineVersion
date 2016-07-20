/***/
package org.eclipse.e4.ui.services;

import org.w3c.dom.css.CSSStyleDeclaration;

/**
* @noimplement This interface is not intended to be implemented by clients.
* @noreference This interface is not intended to be referenced by clients.
* @since 1.0
*/
public interface IStylingEngine {

    /**
* The service name for a styling engine. This name can be
* used to obtain instances of the service from a context or service registry.
*/
    public static final String SERVICE_NAME = IStylingEngine.class.getName();

    public void setClassname(Object widget, String classname);

    public void setId(Object widget, String id);

    public void setClassnameAndId(Object widget, String classname, String id);

    public void style(Object widget);

    public CSSStyleDeclaration getStyle(Object widget);
}
