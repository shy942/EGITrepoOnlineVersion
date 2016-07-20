/***/
package org.eclipse.core.internal.databinding.beans;

import java.beans.PropertyDescriptor;
import java.util.Map;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.beans.IBeanMapProperty;
import org.eclipse.core.databinding.beans.IBeanValueProperty;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.map.MapDiff;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.property.map.IMapProperty;
import org.eclipse.core.databinding.property.map.MapProperty;

/**
* @since 3.3
*
*/
public class BeanMapPropertyDecorator extends MapProperty implements IBeanMapProperty {

    private final IMapProperty delegate;

    private final PropertyDescriptor propertyDescriptor;

    /**
* @param delegate
* @param propertyDescriptor
*/
    public  BeanMapPropertyDecorator(IMapProperty delegate, PropertyDescriptor propertyDescriptor) {
        this.delegate = delegate;
        this.propertyDescriptor = propertyDescriptor;
    }

    @Override
    public PropertyDescriptor getPropertyDescriptor() {
        return propertyDescriptor;
    }

    @Override
    public Object getKeyType() {
        return delegate.getKeyType();
    }

    @Override
    public Object getValueType() {
        return delegate.getValueType();
    }

    @Override
    protected Map doGetMap(Object source) {
        return delegate.getMap(source);
    }

    @Override
    protected void doSetMap(Object source, Map map) {
        delegate.setMap(source, map);
    }

    @Override
    protected void doUpdateMap(Object source, MapDiff diff) {
        delegate.updateMap(source, diff);
    }

    @Override
    public IBeanMapProperty values(String propertyName) {
        return values(propertyName, null);
    }

    @Override
    public IBeanMapProperty values(String propertyName, Class valueType) {
        Class beanClass = (Class) delegate.getValueType();
        return values(BeanProperties.value(beanClass, propertyName, valueType));
    }

    @Override
    public IBeanMapProperty values(IBeanValueProperty property) {
        return new BeanMapPropertyDecorator(super.values(property), property.getPropertyDescriptor());
    }

    @Override
    public IObservableMap observe(Object source) {
        return new BeanObservableMapDecorator(delegate.observe(source), propertyDescriptor);
    }

    @Override
    public IObservableMap observe(Realm realm, Object source) {
        return new BeanObservableMapDecorator(delegate.observe(realm, source), propertyDescriptor);
    }

    @Override
    public IObservableMap observeDetail(IObservableValue master) {
        return new BeanObservableMapDecorator(delegate.observeDetail(master), propertyDescriptor);
    }

    @Override
    public String toString() {
        return delegate.toString();
    }
}
