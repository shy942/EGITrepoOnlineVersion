/***/
package org.eclipse.core.internal.databinding.beans;

import java.beans.PropertyDescriptor;
import java.util.List;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.beans.IBeanListProperty;
import org.eclipse.core.databinding.beans.IBeanValueProperty;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.ListDiff;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.property.list.IListProperty;
import org.eclipse.core.databinding.property.list.ListProperty;

/**
* @since 3.3
*
*/
public class BeanListPropertyDecorator extends ListProperty implements IBeanListProperty {

    private final IListProperty delegate;

    private final PropertyDescriptor propertyDescriptor;

    /**
* @param delegate
* @param propertyDescriptor
*/
    public  BeanListPropertyDecorator(IListProperty delegate, PropertyDescriptor propertyDescriptor) {
        this.delegate = delegate;
        this.propertyDescriptor = propertyDescriptor;
    }

    @Override
    public Object getElementType() {
        return delegate.getElementType();
    }

    @Override
    protected List doGetList(Object source) {
        return delegate.getList(source);
    }

    @Override
    protected void doSetList(Object source, List list) {
        delegate.setList(source, list);
    }

    @Override
    protected void doUpdateList(Object source, ListDiff diff) {
        delegate.updateList(source, diff);
    }

    @Override
    public IBeanListProperty values(String propertyName) {
        return values(propertyName, null);
    }

    @Override
    public IBeanListProperty values(String propertyName, Class valueType) {
        Class beanClass = (Class) delegate.getElementType();
        return values(BeanProperties.value(beanClass, propertyName, valueType));
    }

    @Override
    public IBeanListProperty values(IBeanValueProperty property) {
        return new BeanListPropertyDecorator(super.values(property), property.getPropertyDescriptor());
    }

    @Override
    public PropertyDescriptor getPropertyDescriptor() {
        return propertyDescriptor;
    }

    @Override
    public IObservableList observe(Object source) {
        return new BeanObservableListDecorator(delegate.observe(source), propertyDescriptor);
    }

    @Override
    public IObservableList observe(Realm realm, Object source) {
        return new BeanObservableListDecorator(delegate.observe(realm, source), propertyDescriptor);
    }

    @Override
    public IObservableList observeDetail(IObservableValue master) {
        return new BeanObservableListDecorator(delegate.observeDetail(master), propertyDescriptor);
    }

    @Override
    public String toString() {
        return delegate.toString();
    }
}
