/***/
package org.eclipse.jface.internal.databinding.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.property.INativePropertyListener;
import org.eclipse.core.databinding.property.ISimplePropertyListener;
import org.eclipse.core.databinding.property.NativePropertyListener;
import org.eclipse.core.databinding.property.value.SimpleValueProperty;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;

/**
* Class that supports the use of {@link IObservableValue} with objects that
* follow standard bean method naming conventions but notify an
* {@link IPropertyChangeListener} when the property changes.
*/
public class JFaceProperty extends SimpleValueProperty {

    private Class returnType;

    private Method setterMethod;

    private Method getterMethod;

    private final String property;

    private Method removePropertyListenerMethod;

    private Method addPropertyListenerMethod;

    private static String getSetterName(String fieldName) {
        //$NON-NLS-1$
        return "set" + toMethodSuffix(fieldName);
    }

    private static String getGetterName(String fieldName) {
        //$NON-NLS-1$
        return "get" + toMethodSuffix(fieldName);
    }

    private static String getBooleanGetterName(String fieldName) {
        //$NON-NLS-1$
        return "is" + toMethodSuffix(fieldName);
    }

    private static String toMethodSuffix(String fieldName) {
        if (Character.isLowerCase(fieldName.charAt(0))) {
            return Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);
        }
        return fieldName;
    }

    class Listener extends NativePropertyListener implements IPropertyChangeListener {

        public  Listener(ISimplePropertyListener listener) {
            super(JFaceProperty.this, listener);
        }

        @Override
        public void propertyChange(PropertyChangeEvent event) {
            if (event.getProperty().equals(JFaceProperty.this.property)) {
                fireChange(event.getSource(), null);
            }
        }

        @Override
        protected void doAddTo(Object model) {
            try {
                addPropertyListenerMethod.invoke(model, new Object[] { this });
            } catch (Exception e) {
                throw new IllegalStateException(e.getMessage());
            }
        }

        @Override
        protected void doRemoveFrom(Object model) {
            try {
                removePropertyListenerMethod.invoke(model, new Object[] { this });
            } catch (Exception e) {
                throw new IllegalStateException(e.getMessage());
            }
        }
    }

    /**
* @param fieldName
* @param property
* @param clazz
*/
    public  JFaceProperty(String fieldName, String property, Class clazz) {
        this.property = property;
        // available
        try {
            try {
                String getterName = getGetterName(fieldName);
                getterMethod = clazz.getMethod(getterName, new Class[] {});
            } catch (NoSuchMethodException e) {
                String getterName = getBooleanGetterName(fieldName);
                getterMethod = clazz.getMethod(getterName, new Class[] {});
            }
            returnType = getterMethod.getReturnType();
            setterMethod = clazz.getMethod(getSetterName(fieldName), new Class[] { returnType });
            addPropertyListenerMethod = clazz.getMethod("addPropertyChangeListener", //$NON-NLS-1$
            new Class[] { IPropertyChangeListener.class });
            removePropertyListenerMethod = clazz.getMethod("removePropertyChangeListener", //$NON-NLS-1$
            new Class[] { IPropertyChangeListener.class });
        } catch (SecurityException e) {
            throw new IllegalArgumentException();
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public INativePropertyListener adaptListener(ISimplePropertyListener listener) {
        return new Listener(listener);
    }

    @Override
    protected Object doGetValue(Object model) {
        try {
            return getterMethod.invoke(model, new Object[] {});
        } catch (InvocationTargetException e) {
            throw new IllegalStateException(e.getMessage());
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    @Override
    protected void doSetValue(Object model, Object value) {
        try {
            setterMethod.invoke(model, new Object[] { value });
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e.getMessage());
        } catch (InvocationTargetException e) {
            throw new IllegalStateException(e.getMessage());
        }
    }

    @Override
    public Object getValueType() {
        return returnType;
    }
}
