/***/
package org.eclipse.core.internal.databinding.beans;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import org.eclipse.core.databinding.observable.set.SetDiff;
import org.eclipse.core.databinding.property.INativePropertyListener;
import org.eclipse.core.databinding.property.ISimplePropertyListener;
import org.eclipse.core.databinding.property.set.SimpleSetProperty;

/**
* @since 3.3
*
*/
public class PojoSetProperty extends SimpleSetProperty {

    private final PropertyDescriptor propertyDescriptor;

    private final Class elementType;

    /**
* @param propertyDescriptor
* @param elementType
*/
    public  PojoSetProperty(PropertyDescriptor propertyDescriptor, Class elementType) {
        this.propertyDescriptor = propertyDescriptor;
        this.elementType = elementType == null ? BeanPropertyHelper.getCollectionPropertyElementType(propertyDescriptor) : elementType;
    }

    @Override
    public Object getElementType() {
        return elementType;
    }

    @Override
    protected Set doGetSet(Object source) {
        return asSet(BeanPropertyHelper.readProperty(source, propertyDescriptor));
    }

    private Set asSet(Object propertyValue) {
        if (propertyValue == null)
            return Collections.EMPTY_SET;
        if (propertyDescriptor.getPropertyType().isArray())
            return new HashSet(Arrays.asList((Object[]) propertyValue));
        return (Set) propertyValue;
    }

    @Override
    protected void doSetSet(Object source, Set set, SetDiff diff) {
        doSetSet(source, set);
    }

    @Override
    protected void doSetSet(Object source, Set set) {
        BeanPropertyHelper.writeProperty(source, propertyDescriptor, convertSetToBeanPropertyType(set));
    }

    private Object convertSetToBeanPropertyType(Set set) {
        Object propertyValue = set;
        if (propertyDescriptor.getPropertyType().isArray()) {
            Class componentType = propertyDescriptor.getPropertyType().getComponentType();
            Object[] array = (Object[]) Array.newInstance(componentType, set.size());
            propertyValue = set.toArray(array);
        }
        return propertyValue;
    }

    @Override
    public INativePropertyListener adaptListener(ISimplePropertyListener listener) {
        return null;
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        String s = BeanPropertyHelper.propertyName(propertyDescriptor) + "{}";
        if (elementType != null)
            //$NON-NLS-1$//$NON-NLS-2$
            s += "<" + BeanPropertyHelper.shortClassName(elementType) + ">";
        return s;
    }
}
