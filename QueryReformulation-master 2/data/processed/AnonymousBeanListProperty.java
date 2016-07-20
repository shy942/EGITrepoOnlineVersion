/***/
package org.eclipse.core.internal.databinding.beans;

import java.util.HashMap;
import java.util.Map;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.property.list.DelegatingListProperty;
import org.eclipse.core.databinding.property.list.IListProperty;

/**
* @since 3.3
*
*/
public class AnonymousBeanListProperty extends DelegatingListProperty {

    private final String propertyName;

    private Map delegates;

    /**
* @param propertyName
* @param elementType
*/
    public  AnonymousBeanListProperty(String propertyName, Class elementType) {
        super(elementType);
        this.propertyName = propertyName;
        this.delegates = new HashMap();
    }

    @Override
    protected IListProperty doGetDelegate(Object source) {
        Class beanClass = source.getClass();
        if (delegates.containsKey(beanClass))
            return (IListProperty) delegates.get(beanClass);
        IListProperty delegate;
        try {
            delegate = BeanProperties.list(beanClass, propertyName, (Class) getElementType());
        } catch (IllegalArgumentException noSuchProperty) {
            delegate = null;
        }
        delegates.put(beanClass, delegate);
        return delegate;
    }

    @Override
    public String toString() {
        //$NON-NLS-1$ //$NON-NLS-2$
        String s = "?." + propertyName + "[]";
        Class elementType = (Class) getElementType();
        if (elementType != null)
            //$NON-NLS-1$//$NON-NLS-2$
            s += "<" + BeanPropertyHelper.shortClassName(elementType) + ">";
        return s;
    }
}
