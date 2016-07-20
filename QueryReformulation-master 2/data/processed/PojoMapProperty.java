/***/
package org.eclipse.core.internal.databinding.beans;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.core.databinding.observable.map.MapDiff;
import org.eclipse.core.databinding.property.INativePropertyListener;
import org.eclipse.core.databinding.property.ISimplePropertyListener;
import org.eclipse.core.databinding.property.map.SimpleMapProperty;

/**
* @since 3.3
*
*/
public class PojoMapProperty extends SimpleMapProperty {

    private final PropertyDescriptor propertyDescriptor;

    private final Class keyType;

    private final Class valueType;

    /**
* @param propertyDescriptor
* @param keyType
* @param valueType
*/
    public  PojoMapProperty(PropertyDescriptor propertyDescriptor, Class keyType, Class valueType) {
        this.propertyDescriptor = propertyDescriptor;
        this.keyType = keyType;
        this.valueType = valueType;
    }

    @Override
    public Object getKeyType() {
        return keyType;
    }

    @Override
    public Object getValueType() {
        return valueType;
    }

    @Override
    protected Map doGetMap(Object source) {
        return asMap(BeanPropertyHelper.readProperty(source, propertyDescriptor));
    }

    private Map asMap(Object propertyValue) {
        if (propertyValue == null)
            return new HashMap();
        return (Map) propertyValue;
    }

    @Override
    protected void doSetMap(Object source, Map map, MapDiff diff) {
        doSetMap(source, map);
    }

    @Override
    protected void doSetMap(Object source, Map map) {
        BeanPropertyHelper.writeProperty(source, propertyDescriptor, map);
    }

    @Override
    public INativePropertyListener adaptListener(ISimplePropertyListener listener) {
        return null;
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        String s = BeanPropertyHelper.propertyName(propertyDescriptor) + "{:}";
        if (keyType != null || valueType != null)
            s += //$NON-NLS-1$ //$NON-NLS-2$
            "<" + BeanPropertyHelper.shortClassName(keyType) + ", " + BeanPropertyHelper.shortClassName(valueType) + //$NON-NLS-1$
            ">";
        return s;
    }
}
