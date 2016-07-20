/***/
package org.eclipse.core.internal.databinding.beans;

import java.util.HashMap;
import java.util.Map;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.property.set.DelegatingSetProperty;
import org.eclipse.core.databinding.property.set.ISetProperty;

/**
* @since 3.3
*
*/
public class AnonymousBeanSetProperty extends DelegatingSetProperty {

    private final String propertyName;

    private Map delegates;

    /**
* @param propertyName
* @param elementType
*/
    public  AnonymousBeanSetProperty(String propertyName, Class elementType) {
        super(elementType);
        this.propertyName = propertyName;
        this.delegates = new HashMap();
    }

    @Override
    protected ISetProperty doGetDelegate(Object source) {
        Class beanClass = source.getClass();
        if (delegates.containsKey(beanClass))
            return (ISetProperty) delegates.get(beanClass);
        ISetProperty delegate;
        try {
            delegate = BeanProperties.set(beanClass, propertyName, (Class) getElementType());
        } catch (IllegalArgumentException noSuchProperty) {
            delegate = null;
        }
        delegates.put(beanClass, delegate);
        return delegate;
    }

    @Override
    public String toString() {
        //$NON-NLS-1$ //$NON-NLS-2$
        String s = "?." + propertyName + "{}";
        Class elementType = (Class) getElementType();
        if (elementType != null)
            //$NON-NLS-1$//$NON-NLS-2$
            s += "<" + BeanPropertyHelper.shortClassName(elementType) + ">";
        return s;
    }
}
