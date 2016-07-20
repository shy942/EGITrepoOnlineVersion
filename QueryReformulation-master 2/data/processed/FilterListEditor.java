/***/
package org.eclipse.ui.internal.monitoring.preferences;

import java.util.Arrays;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.preference.ListEditor;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.List;

/**
* Displays the list of stack frames used as a filter.
*/
public class FilterListEditor extends ListEditor {

    private String dialogMessage;

     FilterListEditor(String name, String label, String addButtonLabel, String removeButtonLabel, String dialogMessage, Composite parent) {
        super(name, label, parent);
        this.dialogMessage = dialogMessage;
        setButtonLabel(getAddButton(), addButtonLabel);
        setButtonLabel(getRemoveButton(), removeButtonLabel);
        getUpButton().setVisible(false);
        getDownButton().setVisible(false);
    }

    private void setButtonLabel(Button button, String label) {
        button.setText(label);
        GridDataFactory.fillDefaults().applyTo(button);
    }

    @Override
    protected void doFillIntoGrid(Composite parent, int numColumns) {
        super.doFillIntoGrid(parent, numColumns);
        List list = getListControl(parent);
        GridDataFactory.defaultsFor(list).applyTo(list);
        GridDataFactory.fillDefaults().applyTo(getButtonBoxControl(parent));
    }

    /**
* Handles parsing of defined traces to be filtered.
*/
    @Override
    protected String createList(String[] items) {
        StringBuilder mergedItems = new StringBuilder();
        for (String item : items) {
            item = item.trim();
            if (mergedItems.length() != 0) {
                mergedItems.append(',');
            }
            mergedItems.append(item);
        }
        return mergedItems.toString();
    }

    @Override
    protected String getNewInputObject() {
        FilterInputDialog dialog = new FilterInputDialog(getShell(), dialogMessage);
        if (dialog.open() == Window.OK) {
            String filter = dialog.getFilter();
            List list = getList();
            if (list.getItemCount() != 0) {
                int pos = Arrays.binarySearch(list.getItems(), filter);
                if (pos >= 0) {
                    // Identical item already exists.
                    return null;
                }
                // Select the element before the insertion point to keep the list sorted.
                list.setSelection(-pos - 2);
            }
            return filter;
        }
        return null;
    }

    @Override
    protected String[] parseString(String stringList) {
        if (stringList.isEmpty()) {
            return new String[0];
        }
        //$NON-NLS-1$
        String[] items = stringList.split(",");
        Arrays.sort(items);
        ;
        return items;
    }

    @Override
    protected void refreshValidState() {
        selectionChanged();
    }
}
