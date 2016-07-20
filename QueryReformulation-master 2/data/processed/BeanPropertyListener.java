/***/
package org.eclipse.core.internal.databinding.beans;

import java.beans.PropertyChangeListener;
import java.beans.PropertyDescriptor;
import org.eclipse.core.databinding.observable.IDiff;
import org.eclipse.core.databinding.property.IProperty;
import org.eclipse.core.databinding.property.ISimplePropertyListener;
import org.eclipse.core.databinding.property.NativePropertyListener;

/**
* @since 3.3
*
*/
public abstract class BeanPropertyListener extends NativePropertyListener implements PropertyChangeListener {

    private final PropertyDescriptor propertyDescriptor;

    protected  BeanPropertyListener(IProperty property, PropertyDescriptor propertyDescriptor, ISimplePropertyListener listener) {
        super(property, listener);
        this.propertyDescriptor = propertyDescriptor;
    }

    @Override
    public void propertyChange(java.beans.PropertyChangeEvent evt) {
        if (evt.getPropertyName() == null || propertyDescriptor.getName().equals(evt.getPropertyName())) {
            Object oldValue = evt.getOldValue();
            Object newValue = evt.getNewValue();
            IDiff diff;
            if (evt.getPropertyName() == null || oldValue == null || newValue == null)
                diff = null;
            else
                diff = computeDiff(oldValue, newValue);
            fireChange(evt.getSource(), diff);
        }
    }

    protected abstract IDiff computeDiff(Object oldValue, Object newValue);

    @Override
    protected void doAddTo(Object source) {
        BeanPropertyListenerSupport.hookListener(source, propertyDescriptor.getName(), this);
    }

    @Override
    protected void doRemoveFrom(Object source) {
        BeanPropertyListenerSupport.unhookListener(source, propertyDescriptor.getName(), this);
    }
}
