/***/
package org.eclipse.core.internal.databinding.beans;

import java.util.HashMap;
import java.util.Map;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.masterdetail.MasterDetailObservables;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.property.value.DelegatingValueProperty;
import org.eclipse.core.databinding.property.value.IValueProperty;

/**
* @since 3.3
*
*/
public class AnonymousPojoValueProperty extends DelegatingValueProperty {

    private final String propertyName;

    private Map delegates;

    /**
* @param propertyName
* @param valueType
*/
    public  AnonymousPojoValueProperty(String propertyName, Class valueType) {
        super(valueType);
        this.propertyName = propertyName;
        this.delegates = new HashMap();
    }

    @Override
    protected IValueProperty doGetDelegate(Object source) {
        return getClassDelegate(source.getClass());
    }

    private IValueProperty getClassDelegate(Class pojoClass) {
        if (delegates.containsKey(pojoClass))
            return (IValueProperty) delegates.get(pojoClass);
        IValueProperty delegate;
        try {
            delegate = PojoProperties.value(pojoClass, propertyName, (Class) getValueType());
        } catch (IllegalArgumentException noSuchProperty) {
            delegate = null;
        }
        delegates.put(pojoClass, delegate);
        return delegate;
    }

    @Override
    public IObservableValue observeDetail(IObservableValue master) {
        Object valueType = getValueType();
        if (valueType == null)
            valueType = inferValueType(master.getValueType());
        return MasterDetailObservables.detailValue(master, valueFactory(master.getRealm()), valueType);
    }

    private Object inferValueType(Object masterObservableValueType) {
        if (masterObservableValueType instanceof Class) {
            return getClassDelegate((Class) masterObservableValueType).getValueType();
        }
        return null;
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        String s = "?." + propertyName;
        Class valueType = (Class) getValueType();
        if (valueType != null)
            //$NON-NLS-1$//$NON-NLS-2$
            s += "<" + BeanPropertyHelper.shortClassName(valueType) + ">";
        return s;
    }
}
