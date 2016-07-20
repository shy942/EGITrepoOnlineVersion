/***/
package org.eclipse.e4.ui.css.core.util.resources;

/**
* Interface URI resolver to resolve URI.
*/
public interface IURIResolver {

    /**
* Return the uri resolved.
*
* @param uri
* @return
*/
    public String resolve(String uri);
}
