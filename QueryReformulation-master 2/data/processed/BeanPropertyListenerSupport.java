/***/
package org.eclipse.core.internal.databinding.beans;

import java.beans.PropertyChangeListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.core.databinding.util.Policy;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;

/**
* This is a helper that will hook up and listen for
* <code>PropertyChangeEvent</code> events for a set of target JavaBeans
*
* @since 1.0
*/
public class BeanPropertyListenerSupport {

    /**
* Start listen to target (if it supports the JavaBean property change
* listener pattern)
*
* @param bean
* @param propertyName
* @param listener
*/
    public static void hookListener(Object bean, String propertyName, PropertyChangeListener listener) {
        //$NON-NLS-1$
        Assert.isNotNull(bean, "Bean cannot be null");
        //$NON-NLS-1$
        Assert.isNotNull(listener, "Listener cannot be null");
        //$NON-NLS-1$
        Assert.isNotNull(propertyName, "Property name cannot be null");
        processListener(bean, propertyName, listener, "addPropertyChangeListener", //$NON-NLS-1$ //$NON-NLS-2$
        "Could not attach listener to ");
    }

    /**
* Stop listen to target
*
* @param bean
* @param propertyName
* @param listener
*/
    public static void unhookListener(Object bean, String propertyName, PropertyChangeListener listener) {
        //$NON-NLS-1$
        Assert.isNotNull(bean, "Bean cannot be null");
        //$NON-NLS-1$
        Assert.isNotNull(listener, "Listener cannot be null");
        //$NON-NLS-1$
        Assert.isNotNull(propertyName, "Property name cannot be null");
        processListener(bean, propertyName, listener, "removePropertyChangeListener", //$NON-NLS-1$ //$NON-NLS-2$
        "Cound not remove listener from ");
    }

    /**
* Invokes the method for the provided <code>methodName</code> attempting to
* first use the method with the property name and then the unnamed version.
*
* @param bean
*            object to invoke the method on
* @param methodName
*            either addPropertyChangeListener or
*            removePropertyChangeListener
* @param message
*            string that will be prefixed to the target in an error message
*
* @return <code>true</code> if the method was invoked successfully
*/
    private static boolean processListener(Object bean, String propertyName, PropertyChangeListener listener, String methodName, String message) {
        Method method = null;
        Object[] parameters = null;
        try {
            try {
                method = bean.getClass().getMethod(methodName, new Class[] { String.class, PropertyChangeListener.class });
                parameters = new Object[] { propertyName, listener };
            } catch (NoSuchMethodException e) {
                method = bean.getClass().getMethod(methodName, new Class[] { PropertyChangeListener.class });
                parameters = new Object[] { listener };
            }
        } catch (SecurityException e) {
        } catch (NoSuchMethodException e) {
            log(IStatus.WARNING, message + bean, e);
        }
        if (method != null) {
            if (!method.isAccessible()) {
                method.setAccessible(true);
            }
            try {
                method.invoke(bean, parameters);
                return true;
            } catch (IllegalArgumentException e) {
                log(IStatus.WARNING, message + bean, e);
            } catch (IllegalAccessException e) {
                log(IStatus.WARNING, message + bean, e);
            } catch (InvocationTargetException e) {
                log(IStatus.WARNING, message + bean, e);
            }
        }
        return false;
    }

    /**
* Logs a message to the Data Binding logger.
*/
    private static void log(int severity, String message, Throwable throwable) {
        if (BeansObservables.DEBUG) {
            Policy.getLog().log(new Status(severity, Policy.JFACE_DATABINDING, IStatus.OK, message, throwable));
        }
    }
}
