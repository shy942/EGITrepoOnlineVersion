/***/
package org.eclipse.jface.internal.databinding.viewers;

import org.eclipse.core.databinding.property.INativePropertyListener;
import org.eclipse.core.databinding.property.ISimplePropertyListener;
import org.eclipse.core.databinding.property.value.SimpleValueProperty;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Control;

/**
* @since 3.3
*
*/
public class CellEditorControlProperty extends SimpleValueProperty {

    @Override
    public Object getValueType() {
        return Control.class;
    }

    @Override
    protected Object doGetValue(Object source) {
        return ((CellEditor) source).getControl();
    }

    @Override
    protected void doSetValue(Object source, Object value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public INativePropertyListener adaptListener(ISimplePropertyListener listener) {
        return null;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
