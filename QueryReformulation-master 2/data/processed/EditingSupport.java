/***/
package org.eclipse.jface.viewers;

import org.eclipse.core.runtime.Assert;

/**
* EditingSupport is the abstract superclass of the support for cell editing.
*
* @since 3.3
*
*/
public abstract class EditingSupport {

    private ColumnViewer viewer;

    /**
* @param viewer
*            a new viewer
*/
    public  EditingSupport(ColumnViewer viewer) {
        //$NON-NLS-1$
        Assert.isNotNull(viewer, "Viewer is not allowed to be null");
        this.viewer = viewer;
    }

    /**
* The editor to be shown
*
* @param element
*            the model element
* @return the CellEditor
*/
    protected abstract CellEditor getCellEditor(Object element);

    /**
* Is the cell editable
*
* @param element
*            the model element
* @return true if editable
*/
    protected abstract boolean canEdit(Object element);

    /**
* Get the value to set to the editor
*
* @param element
*            the model element
* @return the value shown
*/
    protected abstract Object getValue(Object element);

    /**
* Sets the new value on the given element. Note that implementers need to
* ensure that <code>getViewer().update(element, null)</code> or similar
* methods are called, either directly or through some kind of listener
* mechanism on the implementer's model, to cause the new value to appear in
* the viewer.
*
* <p>
* <b>Subclasses should overwrite.</b>
* </p>
*
* @param element
*            the model element
* @param value
*            the new value
*/
    protected abstract void setValue(Object element, Object value);

    /**
* @return the viewer this editing support works for
*/
    public ColumnViewer getViewer() {
        return viewer;
    }

    /**
* Initialize the editor. Frameworks like Databinding can hook in here and provide
* a customized implementation. <p><b>Standard customers should not overwrite this method but {@link #getValue(Object)}</b></p>
*
* @param cellEditor
*            the cell editor
* @param cell
*            the cell the editor is working for
*/
    protected void initializeCellEditorValue(CellEditor cellEditor, ViewerCell cell) {
        Object value = getValue(cell.getElement());
        cellEditor.setValue(value);
    }

    /**
* Save the value of the cell editor back to the model. Frameworks like Databinding can hook in here and provide
* a customized implementation. <p><b>Standard customers should not overwrite this method but {@link #setValue(Object, Object)} </b></p>
* @param cellEditor
*            the cell-editor
* @param cell
* 			  the cell the editor is working for
*/
    protected void saveCellEditorValue(CellEditor cellEditor, ViewerCell cell) {
        Object value = cellEditor.getValue();
        setValue(cell.getElement(), value);
    }

    boolean isLegacySupport() {
        return false;
    }
}
