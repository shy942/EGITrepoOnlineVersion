/***/
package org.eclipse.jface.examples.databinding.contentprovider.test;

import java.util.Collections;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.jface.databinding.swt.DisplayRealm;
import org.eclipse.jface.databinding.viewers.ListeningLabelProvider;
import org.eclipse.jface.databinding.viewers.ObservableListContentProvider;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.layout.LayoutConstants;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.jface.viewers.ViewerLabel;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
* Tests UpdatableTreeContentProvider and DirtyIndicationLabelProvider. Creates
* a tree containing three randomly-generated sets of integers, and one node
* that contains the union of the other sets.
*
* @since 1.0
*/
public class LabelProviderTest2 {

    private Shell shell;

    private ListViewer list;

    private WritableList<RenamableItem> listOfRenamables;

    private Button addButton;

    private Button removeButton;

    private Button renameButton;

    private SelectionListener buttonSelectionListener = new SelectionAdapter() {

        @Override
        public void widgetSelected(SelectionEvent e) {
            Button pressed = (Button) e.widget;
            if (pressed == addButton) {
                listOfRenamables.add(new RenamableItem());
            } else if (pressed == removeButton) {
                listOfRenamables.remove(getCurrentSelection());
            } else if (pressed == renameButton) {
                rename(getCurrentSelection());
            }
            super.widgetSelected(e);
        }
    };

    private IObservableValue<RenamableItem> selectedRenamable;

    /**
*
*/
    public  LabelProviderTest2() {
        // Create shell
        shell = new Shell(Display.getCurrent());
        {
            // Initialize shell
            listOfRenamables = new WritableList();
            list = new ListViewer(shell);
            ObservableListContentProvider contentProvider = new ObservableListContentProvider();
            list.setContentProvider(contentProvider);
            list.setLabelProvider(new ListeningLabelProvider(contentProvider.getKnownElements()) {

                RenamableItem.Listener listener = new RenamableItem.Listener() {

                    @Override
                    public void handleChanged(RenamableItem item) {
                        fireChangeEvent(Collections.singleton(item));
                    }
                };

                @Override
                public void updateLabel(ViewerLabel label, Object element) {
                    if (element instanceof RenamableItem) {
                        RenamableItem item = (RenamableItem) element;
                        label.setText(item.getName());
                    }
                }

                @Override
                protected void addListenerTo(Object next) {
                    RenamableItem item = (RenamableItem) next;
                    item.addListener(listener);
                }

                @Override
                protected void removeListenerFrom(Object next) {
                    RenamableItem item = (RenamableItem) next;
                    item.removeListener(listener);
                }
            });
            list.setInput(listOfRenamables);
            selectedRenamable = ViewersObservables.observeSingleSelection(list);
            Composite buttonBar = new Composite(shell, SWT.NONE);
            {
                // Initialize buttonBar
                addButton = new Button(buttonBar, SWT.PUSH);
                //$NON-NLS-1$
                addButton.setText("Add");
                addButton.addSelectionListener(buttonSelectionListener);
                removeButton = new Button(buttonBar, SWT.PUSH);
                removeButton.addSelectionListener(buttonSelectionListener);
                //$NON-NLS-1$
                removeButton.setText("Remove");
                renameButton = new Button(buttonBar, SWT.PUSH);
                renameButton.addSelectionListener(buttonSelectionListener);
                //$NON-NLS-1$
                renameButton.setText("Rename");
                selectedRenamable.addValueChangeListener(new IValueChangeListener<RenamableItem>() {

                    @Override
                    public void handleValueChange(ValueChangeEvent<? extends RenamableItem> event) {
                        boolean shouldEnable = selectedRenamable.getValue() != null;
                        removeButton.setEnabled(shouldEnable);
                        renameButton.setEnabled(shouldEnable);
                    }
                });
                removeButton.setEnabled(false);
                renameButton.setEnabled(false);
                GridLayoutFactory.fillDefaults().generateLayout(buttonBar);
            }
        }
        GridLayoutFactory.fillDefaults().numColumns(2).margins(LayoutConstants.getMargins()).generateLayout(shell);
    }

    /**
* @param currentSelection
*/
    protected void rename(final RenamableItem currentSelection) {
        InputDialog inputDialog = new InputDialog(shell, "Edit name", "Enter the new item name", currentSelection.getName(), //$NON-NLS-1$ //$NON-NLS-2$
        null);
        if (Window.OK == inputDialog.open()) {
            currentSelection.setName(inputDialog.getValue());
        }
    }

    /**
* @return
*/
    protected RenamableItem getCurrentSelection() {
        return selectedRenamable.getValue();
    }

    /**
* @param args
*/
    public static void main(String[] args) {
        final Display display = Display.getDefault();
        Realm.runWithDefault(DisplayRealm.getRealm(display), new Runnable() {

            @Override
            public void run() {
                LabelProviderTest2 test = new LabelProviderTest2();
                Shell s = test.getShell();
                s.pack();
                s.setVisible(true);
                while (!s.isDisposed()) {
                    if (!display.readAndDispatch())
                        display.sleep();
                }
            }
        });
        display.dispose();
    }

    private Shell getShell() {
        return shell;
    }
}
