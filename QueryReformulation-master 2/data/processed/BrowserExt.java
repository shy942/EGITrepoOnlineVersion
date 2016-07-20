/***/
package org.eclipse.ui.internal.browser;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.ui.browser.BrowserFactory;
import org.eclipse.ui.browser.IWebBrowser;

/**
* @since 1.0
*/
public class BrowserExt implements IBrowserExt {

    //$NON-NLS-1$
    private static final String ATTR_FACTORY_CLASS = "factoryclass";

    private IConfigurationElement element;

    private BrowserFactory delegate;

    /**
* BrowserExt constructor comment.
*/
    public  BrowserExt(IConfigurationElement element) {
        super();
        this.element = element;
    }

    /**
* Returns the id of this browser.
*
* @return java.lang.String
*/
    @Override
    public String getId() {
        //$NON-NLS-1$
        return element.getAttribute("id");
    }

    @Override
    public String getName() {
        //$NON-NLS-1$
        String label = element.getAttribute("name");
        if (label == null)
            //$NON-NLS-1$
            return "n/a";
        return label;
    }

    @Override
    public String getParameters() {
        //$NON-NLS-1$
        return element.getAttribute("parameters");
    }

    @Override
    public String getExecutable() {
        //$NON-NLS-1$
        return element.getAttribute("executable");
    }

    @Override
    public String getOS() {
        //$NON-NLS-1$
        String os = element.getAttribute("os");
        if (os == null)
            //$NON-NLS-1$
            os = "";
        return os;
    }

    @Override
    public String[] getDefaultLocations() {
        List<String> list = new ArrayList();
        //$NON-NLS-1$
        IConfigurationElement[] children = element.getChildren("location");
        if (children != null) {
            int size = children.length;
            for (int i = 0; i < size; i++) {
                list.add(children[i].getValue());
            }
        }
        String[] s = new String[list.size()];
        list.toArray(s);
        return s;
    }

    protected BrowserFactory getDelegate() {
        if (delegate == null) {
            if (element.getAttribute(ATTR_FACTORY_CLASS) == null || element.getAttribute(ATTR_FACTORY_CLASS).length() == 0)
                return null;
            try {
                delegate = (BrowserFactory) element.createExecutableExtension(ATTR_FACTORY_CLASS);
            } catch (Exception e) {
                Trace.trace(Trace.SEVERE, "Could not create delegate" + toString() + ": " + e.getMessage());
            }
        }
        return delegate;
    }

    /**
* Checks whether the factory can work on the user system.
*
* @return false if the factory cannot work on this system; for example the
*         required native browser required by browser adapters that it
*         creates is not installed
*/
    @Override
    public boolean isAvailable() {
        if (delegate == null && (element.getAttribute(ATTR_FACTORY_CLASS) == null || element.getAttribute(ATTR_FACTORY_CLASS).length() == 0))
            return true;
        try {
            return getDelegate().isAvailable();
        } catch (Exception e) {
            Trace.trace(Trace.SEVERE, "Error calling delegate " + toString() + ": " + e.getMessage());
            return false;
        }
    }

    /**
* Obtains a new instance of a web browser.
*
* @return instance of IBrowser
*/
    @Override
    public IWebBrowser createBrowser(String id, String location, String parameters) {
        try {
            return getDelegate().createBrowser(id, location, parameters);
        } catch (Exception e) {
            Trace.trace(Trace.SEVERE, "Error calling delegate " + toString() + ": " + e.getMessage());
            return null;
        }
    }

    /**
* Return a string representation of this object.
*
* @return java.lang.String
*/
    @Override
    public String toString() {
        String s = //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
        "BrowserExt: " + getId() + ", " + getName() + ", " + getOS() + ", " + getExecutable() + ", " + getParameters() + //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
        ", ";
        String[] locations = getDefaultLocations();
        if (locations != null) {
            int size = locations.length;
            for (int i = 0; i < size; i++) {
                //$NON-NLS-1$
                s += locations[i] + ";";
            }
        }
        return s;
    }
}
