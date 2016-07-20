/***/
package org.eclipse.core.databinding.conversion;

/**
* Abstract base class for converters.
*
* @since 1.0
*
*/
public abstract class Converter implements IConverter {

    private Object fromType;

    private Object toType;

    /**
* @param fromType
* @param toType
*/
    public  Converter(Object fromType, Object toType) {
        this.fromType = fromType;
        this.toType = toType;
    }

    @Override
    public Object getFromType() {
        return fromType;
    }

    @Override
    public Object getToType() {
        return toType;
    }
}
