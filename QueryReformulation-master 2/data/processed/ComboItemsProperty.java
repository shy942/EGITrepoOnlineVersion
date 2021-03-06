/***/
package org.eclipse.jface.internal.databinding.swt;

import org.eclipse.core.databinding.observable.list.ListDiff;
import org.eclipse.core.databinding.observable.list.ListDiffVisitor;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;

/**
* @since 3.3
*
*/
public class ComboItemsProperty extends ControlStringListProperty {

    @Override
    protected void doUpdateStringList(final Control control, ListDiff diff) {
        diff.accept(new ListDiffVisitor() {

            Combo combo = (Combo) control;

            @Override
            public void handleAdd(int index, Object element) {
                combo.add((String) element, index);
            }

            @Override
            public void handleRemove(int index, Object element) {
                combo.remove(index);
            }

            // public void handleMove(int oldIndex, int newIndex, Object
            // element) {
            // int selectionIndex = combo.getSelectionIndex();
            // Listener[] modifyListeners = combo.getListeners(SWT.Modify);
            // if (selectionIndex == oldIndex) {
            // for (int i = 0; i < modifyListeners.length; i++)
            // combo.removeListener(SWT.Modify, modifyListeners[i]);
            // }
            //
            // super.handleMove(oldIndex, newIndex, element);
            //
            // if (selectionIndex == oldIndex) {
            // combo.select(newIndex);
            // for (int i = 0; i < modifyListeners.length; i++)
            // combo.addListener(SWT.Modify, modifyListeners[i]);
            // }
            // }
            @Override
            public void handleReplace(int index, Object oldElement, Object newElement) {
                combo.setItem(index, (String) newElement);
            }
        });
    }

    @Override
    public String[] doGetStringList(Control control) {
        return ((Combo) control).getItems();
    }

    @Override
    public String toString() {
        //$NON-NLS-1$
        return "Combo.items[] <String>";
    }
}
