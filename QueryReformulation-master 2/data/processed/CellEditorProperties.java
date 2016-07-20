/***/
package org.eclipse.jface.databinding.viewers;

import org.eclipse.core.databinding.property.value.IValueProperty;
import org.eclipse.jface.internal.databinding.viewers.CellEditorControlProperty;
import org.eclipse.jface.viewers.CellEditor;

/**
* A factory for creating properties of JFace {@link CellEditor cell editors}.
*
* @since 1.3
*/
public class CellEditorProperties {

    /**
* Returns a value property for observing the control of a
* {@link CellEditor}.
*
* @return a value property for observing the control of a
*         {@link CellEditor}.
*/
    public static IValueProperty control() {
        return new CellEditorControlProperty();
    }
}
