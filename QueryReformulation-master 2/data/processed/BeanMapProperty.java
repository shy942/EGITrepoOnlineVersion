/***/
package org.eclipse.core.internal.databinding.beans;

import java.beans.PropertyDescriptor;
import java.util.Collections;
import java.util.Map;
import org.eclipse.core.databinding.observable.Diffs;
import org.eclipse.core.databinding.observable.IDiff;
import org.eclipse.core.databinding.observable.map.MapDiff;
import org.eclipse.core.databinding.property.INativePropertyListener;
import org.eclipse.core.databinding.property.ISimplePropertyListener;
import org.eclipse.core.databinding.property.map.SimpleMapProperty;

/**
* @since 3.3
*
*/
public class BeanMapProperty extends SimpleMapProperty {

    private final PropertyDescriptor propertyDescriptor;

    private final Class keyType;

    private final Class valueType;

    /**
* @param propertyDescriptor
* @param keyType
* @param valueType
*/
    public  BeanMapProperty(PropertyDescriptor propertyDescriptor, Class keyType, Class valueType) {
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
            return Collections.EMPTY_MAP;
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
    public INativePropertyListener adaptListener(final ISimplePropertyListener listener) {
        return new BeanPropertyListener(this, propertyDescriptor, listener) {

            @Override
            protected IDiff computeDiff(Object oldValue, Object newValue) {
                return Diffs.computeMapDiff(asMap(oldValue), asMap(newValue));
            }
        };
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
