/***/
package org.eclipse.core.internal.databinding;

import org.eclipse.core.databinding.conversion.IConverter;
import org.eclipse.core.databinding.property.INativePropertyListener;
import org.eclipse.core.databinding.property.ISimplePropertyListener;
import org.eclipse.core.databinding.property.value.SimpleValueProperty;

/**
* Simple value property which applies a given converter on a source object in
* order to produce the property's value.
*/
public class ConverterValueProperty extends SimpleValueProperty {

    private final IConverter converter;

    /**
* Creates a new value property which applies the given converter on the
* source object in order to produce the property's value.
*
* @param converter
*            The converter to apply to the source object.
*/
    public  ConverterValueProperty(IConverter converter) {
        this.converter = converter;
    }

    @Override
    public Object getValueType() {
        // the property type is the converter's target type
        return converter.getToType();
    }

    @Override
    public Object getValue(Object source) {
        // We do also pass null values to the converter.
        return doGetValue(source);
    }

    @Override
    protected Object doGetValue(Object source) {
        // delegate to the IConverter
        return converter.convert(source);
    }

    @Override
    protected void doSetValue(Object source, Object value) {
        // setting a value is not supported
        throw new UnsupportedOperationException(toString() + //$NON-NLS-1$
        ": Setter not supported on a converted value!");
    }

    @Override
    public INativePropertyListener adaptListener(ISimplePropertyListener listener) {
        // no listener API
        return null;
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "IConverter#convert(source) <IConverter#getToType()>";
    }
}
