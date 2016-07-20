/***/
package org.eclipse.core.internal.databinding.beans;

import java.util.HashMap;
import java.util.Map;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.property.map.DelegatingMapProperty;
import org.eclipse.core.databinding.property.map.IMapProperty;

/**
* @since 3.3
*
*/
public class AnonymousBeanMapProperty extends DelegatingMapProperty {

    private final String propertyName;

    private Map delegates;

    /**
* @param propertyName
* @param keyType
* @param valueType
*/
    public  AnonymousBeanMapProperty(String propertyName, Class keyType, Class valueType) {
        super(keyType, valueType);
        this.propertyName = propertyName;
        this.delegates = new HashMap();
    }

    @Override
    protected IMapProperty doGetDelegate(Object source) {
        Class beanClass = source.getClass();
        if (delegates.containsKey(beanClass))
            return (IMapProperty) delegates.get(beanClass);
        IMapProperty delegate;
        try {
            delegate = BeanProperties.map(beanClass, propertyName, (Class) getKeyType(), (Class) getValueType());
        } catch (IllegalArgumentException noSuchProperty) {
            delegate = null;
        }
        delegates.put(beanClass, delegate);
        return delegate;
    }

    @Override
    public String toString() {
        //$NON-NLS-1$ //$NON-NLS-2$
        String s = "?." + propertyName + "{:}";
        Class keyType = (Class) getKeyType();
        Class valueType = (Class) getValueType();
        if (keyType != null || valueType != null) {
            s += //$NON-NLS-1$//$NON-NLS-2$
            " <" + BeanPropertyHelper.shortClassName(keyType) + ", " + BeanPropertyHelper.shortClassName(valueType) + //$NON-NLS-1$
            ">";
        }
        return s;
    }
}
