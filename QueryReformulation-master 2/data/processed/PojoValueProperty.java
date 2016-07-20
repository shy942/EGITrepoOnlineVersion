/***/
package org.eclipse.core.internal.databinding.beans;

import java.beans.PropertyDescriptor;
import org.eclipse.core.databinding.property.INativePropertyListener;
import org.eclipse.core.databinding.property.ISimplePropertyListener;
import org.eclipse.core.databinding.property.value.SimpleValueProperty;

/**
* @since 3.3
*
*/
public class PojoValueProperty extends SimpleValueProperty {

    private final PropertyDescriptor propertyDescriptor;

    private final Class valueType;

    /**
* @param propertyDescriptor
* @param valueType
*/
    public  PojoValueProperty(PropertyDescriptor propertyDescriptor, Class valueType) {
        this.propertyDescriptor = propertyDescriptor;
        this.valueType = valueType == null ? propertyDescriptor.getPropertyType() : valueType;
    }

    @Override
    public Object getValueType() {
        return valueType;
    }

    @Override
    protected Object doGetValue(Object source) {
        if (source == null)
            return null;
        return BeanPropertyHelper.readProperty(source, propertyDescriptor);
    }

    @Override
    protected void doSetValue(Object source, Object value) {
        BeanPropertyHelper.writeProperty(source, propertyDescriptor, value);
    }

    @Override
    public INativePropertyListener adaptListener(ISimplePropertyListener listener) {
        return null;
    }

    @Override
    public String toString() {
        String s = BeanPropertyHelper.propertyName(propertyDescriptor);
        if (valueType != null)
            //$NON-NLS-1$//$NON-NLS-2$
            s += "<" + BeanPropertyHelper.shortClassName(valueType) + ">";
        return s;
    }
}
