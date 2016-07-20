/***/
package org.eclipse.ui.views.properties;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.TextCellEditor;
import org.eclipse.swt.widgets.Composite;

/**
* Descriptor for a property that has a value which should be edited with a
* text cell editor.
* <p>
* This class may be instantiated; it is not intended to be subclassed.
* </p>
* <p>
* Example:
* <pre>
* IPropertyDescriptor pd = new TextPropertyDescriptor("surname", "Last Name");
* </pre>
* </p>
* @noextend This class is not intended to be subclassed by clients.
*/
public class TextPropertyDescriptor extends PropertyDescriptor {

    /**
* Creates an property descriptor with the given id and display name.
*
* @param id the id of the property
* @param displayName the name to display for the property
*/
    public  TextPropertyDescriptor(Object id, String displayName) {
        super(id, displayName);
    }

    /**
* The <code>TextPropertyDescriptor</code> implementation of this
* <code>IPropertyDescriptor</code> method creates and returns a new
* <code>TextCellEditor</code>.
* <p>
* The editor is configured with the current validator if there is one.
* </p>
*/
    @Override
    public CellEditor createPropertyEditor(Composite parent) {
        CellEditor editor = new TextCellEditor(parent);
        if (getValidator() != null) {
            editor.setValidator(getValidator());
        }
        return editor;
    }
}
