/**/
package org.eclipse.core.internal.databinding.conversion;

/**
* StringToBooleanConverter.
*/
public class StringToBooleanConverter extends StringToBooleanPrimitiveConverter {

    @Override
    public Object convert(Object source) {
        String sourceString = (String) source;
        if ("".equals(sourceString.trim())) {
            //$NON-NLS-1$
            return null;
        }
        return super.convert(source);
    }

    @Override
    public Object getToType() {
        return Boolean.class;
    }
}
