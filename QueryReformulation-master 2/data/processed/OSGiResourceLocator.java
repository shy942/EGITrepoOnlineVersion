/***/
package org.eclipse.e4.ui.css.core.util.impl.resources;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.e4.ui.css.core.util.resources.IResourceLocator;

public class OSGiResourceLocator implements IResourceLocator {

    private String startLocation;

    public  OSGiResourceLocator(String start) {
        startLocation = start;
    }

    @Override
    public String resolve(String uri) {
        if (!uri.startsWith("platform:/plugin/")) {
            uri = startLocation + uri;
        }
        try {
            URL resolvedURL = FileLocator.resolve(new URL(uri));
            return resolvedURL.toString();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        return null;
    }

    @Override
    public InputStream getInputStream(String uri) throws Exception {
        return FileLocator.resolve(new URL(startLocation + uri)).openStream();
    }

    @Override
    public Reader getReader(String uri) throws Exception {
        return new InputStreamReader(getInputStream(uri));
    }
}
