/***/
package org.eclipse.core.internal.databinding.beans;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.eclipse.core.databinding.observable.list.ListDiff;
import org.eclipse.core.databinding.property.INativePropertyListener;
import org.eclipse.core.databinding.property.ISimplePropertyListener;
import org.eclipse.core.databinding.property.list.SimpleListProperty;

/**
* @since 3.3
*
*/
public class PojoListProperty extends SimpleListProperty {

    private final PropertyDescriptor propertyDescriptor;

    private final Class elementType;

    /**
* @param propertyDescriptor
* @param elementType
*/
    public  PojoListProperty(PropertyDescriptor propertyDescriptor, Class elementType) {
        this.propertyDescriptor = propertyDescriptor;
        this.elementType = elementType == null ? BeanPropertyHelper.getCollectionPropertyElementType(propertyDescriptor) : elementType;
    }

    @Override
    public Object getElementType() {
        return elementType;
    }

    @Override
    protected List doGetList(Object source) {
        return asList(BeanPropertyHelper.readProperty(source, propertyDescriptor));
    }

    private List asList(Object propertyValue) {
        if (propertyValue == null)
            return Collections.EMPTY_LIST;
        if (propertyDescriptor.getPropertyType().isArray())
            return Arrays.asList((Object[]) propertyValue);
        return (List) propertyValue;
    }

    @Override
    protected void doSetList(Object source, List list, ListDiff diff) {
        doSetList(source, list);
    }

    @Override
    protected void doSetList(Object source, List list) {
        BeanPropertyHelper.writeProperty(source, propertyDescriptor, convertListToBeanPropertyType(list));
    }

    private Object convertListToBeanPropertyType(List list) {
        Object propertyValue = list;
        if (propertyDescriptor.getPropertyType().isArray()) {
            Class componentType = propertyDescriptor.getPropertyType().getComponentType();
            Object[] array = (Object[]) Array.newInstance(componentType, list.size());
            list.toArray(array);
            propertyValue = array;
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
        String s = BeanPropertyHelper.propertyName(propertyDescriptor) + "[]";
        if (elementType != null)
            //$NON-NLS-1$//$NON-NLS-2$
            s += "<" + BeanPropertyHelper.shortClassName(elementType) + ">";
        return s;
    }
}
