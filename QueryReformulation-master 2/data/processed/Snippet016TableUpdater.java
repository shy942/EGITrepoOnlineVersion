/***/
package org.eclipse.jface.examples.databinding.snippets;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.databinding.swt.DisplayRealm;
import org.eclipse.jface.internal.databinding.provisional.swt.TableUpdater;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

/**
* @since 3.2
*
*/
public class Snippet016TableUpdater {

    public static void main(String[] args) {
        final Display display = new Display();
        Realm.runWithDefault(DisplayRealm.getRealm(display), new Runnable() {

            @Override
            public void run() {
                final Shell shell = createShell(display);
                GridLayoutFactory.fillDefaults().generateLayout(shell);
                shell.open();
                // The SWT event loop
                while (!shell.isDisposed()) {
                    if (!display.readAndDispatch()) {
                        display.sleep();
                    }
                }
            }
        });
    }

    static class Stuff {

        private WritableValue counter = new WritableValue(Integer.valueOf(1), Integer.class);

        public  Stuff(final Display display) {
            display.timerExec(1000, new Runnable() {

                @Override
                public void run() {
                    counter.setValue(Integer.valueOf(1 + ((Integer) counter.getValue()).intValue()));
                    display.timerExec(1000, this);
                }
            });
        }

        @Override
        public String toString() {
            return counter.getValue().toString();
        }
    }

    protected static Shell createShell(final Display display) {
        Shell shell = new Shell();
        Table t = new Table(shell, SWT.VIRTUAL);
        final WritableList list = new WritableList();
        new TableUpdater(t, list) {

            @Override
            protected void updateItem(int index, TableItem item, Object element) {
                item.setText(element.toString());
            }
        };
        display.timerExec(2000, new Runnable() {

            @Override
            public void run() {
                list.add(new Stuff(display));
                display.timerExec(2000, this);
            }
        });
        return shell;
    }
}
