/***/
package org.eclipse.ui.internal.navigator;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.osgi.util.NLS;

/**
* @since 3.5
*
*/
public abstract class NavigatorSafeRunnable extends SafeRunnable {

    protected String _message;

    protected IConfigurationElement _element;

    protected Object _object;

    /**
*
*/
    public  NavigatorSafeRunnable() {
        super();
    }

    /**
* @param message
*/
    public  NavigatorSafeRunnable(String message) {
        _message = message;
    }

    /**
* @param element
*/
    public  NavigatorSafeRunnable(IConfigurationElement element) {
        _element = element;
    }

    /**
* @param element
* @param object
*            an object to provide additional context
*/
    public  NavigatorSafeRunnable(IConfigurationElement element, Object object) {
        _element = element;
        _object = object;
    }

    @Override
    public abstract void run() throws Exception;

    @Override
    public void handleException(Throwable e) {
        String msg = _message;
        if (msg == null)
            msg = e.getMessage() != null ? e.getMessage() : e.toString();
        if (_element != null) {
            msg += //$NON-NLS-1$
            ": " + NLS.bind(CommonNavigatorMessages.Exception_Invoking_Extension, new Object[] { _element.getAttribute("id") + ": " + _element.getName(), //$NON-NLS-1$ //$NON-NLS-2$
            _object });
        }
        NavigatorPlugin.log(IStatus.ERROR, 0, msg, e);
    }
}
