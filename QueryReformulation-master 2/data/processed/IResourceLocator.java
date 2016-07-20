/***/
package org.eclipse.e4.ui.css.core.util.resources;

import java.io.InputStream;
import java.io.Reader;

/**
* Resources locator to get {@link InputStream} or {@link Reader} from an URI.
*/
public interface IResourceLocator extends IURIResolver {

    /**
* Return {@link InputStream} from the <code>uri</code>.
*
* @param uri
* @return
* @throws Exception
*/
    public InputStream getInputStream(String uri) throws Exception;

    /**
* Return {@link Reader} from the <code>uri</code>.
*
* @param uri
* @return
* @throws Exception
*/
    public Reader getReader(String uri) throws Exception;
}
