/***/
package org.eclipse.ui.examples.views.properties.tabbed.article.views;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.CheckboxCellEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.views.properties.PropertyDescriptor;

/**
* A checkbox property descriptor.
*
* @author Anthony Hunter
*/
public class CheckBoxPropertyDescriptor extends PropertyDescriptor {

    /**
* Constructs a new property descriptor with the given id and display name.
*
* @param id
*            the id for the descriptor.
* @param displayName
*            the display name for the descriptor.
*/
    public  CheckBoxPropertyDescriptor(Object id, String displayName) {
        super(id, displayName);
    }

    public CellEditor createPropertyEditor(Composite parent) {
        CellEditor editor = new CheckboxCellEditor(parent);
        if (getValidator() != null)
            editor.setValidator(getValidator());
        return editor;
    }
}
