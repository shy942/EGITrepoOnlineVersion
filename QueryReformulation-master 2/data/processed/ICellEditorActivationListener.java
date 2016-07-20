/***/
package org.eclipse.ui.views.properties;

import org.eclipse.jface.viewers.CellEditor;

/*package*/
interface ICellEditorActivationListener {

    /**
* Notifies that the cell editor has been activated
*
* @param cellEditor the cell editor which has been activated
*/
    public void cellEditorActivated(CellEditor cellEditor);

    /**
* Notifies that the cell editor has been deactivated
*
* @param cellEditor the cell editor which has been deactivated
*/
    public void cellEditorDeactivated(CellEditor cellEditor);
}
