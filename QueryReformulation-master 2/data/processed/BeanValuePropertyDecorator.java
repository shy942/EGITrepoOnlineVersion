/***/
package org.eclipse.core.internal.databinding.beans;

import java.beans.PropertyDescriptor;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.beans.IBeanListProperty;
import org.eclipse.core.databinding.beans.IBeanMapProperty;
import org.eclipse.core.databinding.beans.IBeanSetProperty;
import org.eclipse.core.databinding.beans.IBeanValueProperty;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.set.IObservableSet;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.property.value.IValueProperty;
import org.eclipse.core.databinding.property.value.ValueProperty;

/**
* @since 3.3
*
*/
public class BeanValuePropertyDecorator extends ValueProperty implements IBeanValueProperty {

    private final IValueProperty delegate;

    private final PropertyDescriptor propertyDescriptor;

    /**
* @param delegate
* @param propertyDescriptor
*/
    public  BeanValuePropertyDecorator(IValueProperty delegate, PropertyDescriptor propertyDescriptor) {
        this.delegate = delegate;
        this.propertyDescriptor = propertyDescriptor;
    }

    @Override
    public PropertyDescriptor getPropertyDescriptor() {
        return propertyDescriptor;
    }

    @Override
    public Object getValueType() {
        return delegate.getValueType();
    }

    @Override
    protected Object doGetValue(Object source) {
        return delegate.getValue(source);
    }

    @Override
    protected void doSetValue(Object source, Object value) {
        delegate.setValue(source, value);
    }

    @Override
    public IBeanValueProperty value(String propertyName) {
        return value(propertyName, null);
    }

    @Override
    public IBeanValueProperty value(String propertyName, Class valueType) {
        Class beanClass = (Class) delegate.getValueType();
        return value(BeanProperties.value(beanClass, propertyName, valueType));
    }

    @Override
    public IBeanValueProperty value(IBeanValueProperty property) {
        return new BeanValuePropertyDecorator(super.value(property), property.getPropertyDescriptor());
    }

    @Override
    public IBeanListProperty list(String propertyName) {
        return list(propertyName, null);
    }

    @Override
    public IBeanListProperty list(String propertyName, Class elementType) {
        Class beanClass = (Class) delegate.getValueType();
        return list(BeanProperties.list(beanClass, propertyName, elementType));
    }

    @Override
    public IBeanListProperty list(IBeanListProperty property) {
        return new BeanListPropertyDecorator(super.list(property), property.getPropertyDescriptor());
    }

    @Override
    public IBeanSetProperty set(String propertyName) {
        return set(propertyName, null);
    }

    @Override
    public IBeanSetProperty set(String propertyName, Class elementType) {
        Class beanClass = (Class) delegate.getValueType();
        return set(BeanProperties.set(beanClass, propertyName, elementType));
    }

    @Override
    public IBeanSetProperty set(IBeanSetProperty property) {
        return new BeanSetPropertyDecorator(super.set(property), property.getPropertyDescriptor());
    }

    @Override
    public IBeanMapProperty map(String propertyName) {
        return map(propertyName, null, null);
    }

    @Override
    public IBeanMapProperty map(String propertyName, Class keyType, Class valueType) {
        Class beanClass = (Class) delegate.getValueType();
        return map(BeanProperties.map(beanClass, propertyName, keyType, valueType));
    }

    @Override
    public IBeanMapProperty map(IBeanMapProperty property) {
        return new BeanMapPropertyDecorator(super.map(property), property.getPropertyDescriptor());
    }

    @Override
    public IObservableValue observe(Object source) {
        return new BeanObservableValueDecorator(delegate.observe(source), propertyDescriptor);
    }

    @Override
    public IObservableValue observe(Realm realm, Object source) {
        return new BeanObservableValueDecorator(delegate.observe(realm, source), propertyDescriptor);
    }

    @Override
    public IObservableValue observeDetail(IObservableValue master) {
        return new BeanObservableValueDecorator(delegate.observeDetail(master), propertyDescriptor);
    }

    @Override
    public IObservableList observeDetail(IObservableList master) {
        return new BeanObservableListDecorator(delegate.observeDetail(master), propertyDescriptor);
    }

    @Override
    public IObservableMap observeDetail(IObservableSet master) {
        return new BeanObservableMapDecorator(delegate.observeDetail(master), propertyDescriptor);
    }

    @Override
    public IObservableMap observeDetail(IObservableMap master) {
        return new BeanObservableMapDecorator(delegate.observeDetail(master), propertyDescriptor);
    }

    @Override
    public String toString() {
        return delegate.toString();
    }
}
