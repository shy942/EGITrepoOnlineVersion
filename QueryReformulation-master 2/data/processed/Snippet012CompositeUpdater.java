/***/
package org.eclipse.jface.examples.databinding.snippets;

import java.util.Timer;
import java.util.TimerTask;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.jface.databinding.swt.DisplayRealm;
import org.eclipse.jface.internal.databinding.provisional.swt.CompositeUpdater;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Widget;

/**
* @since 3.2
*
*/
public class Snippet012CompositeUpdater {

    public static void main(String[] args) {
        final Display display = new Display();
        Realm.runWithDefault(DisplayRealm.getRealm(display), new Runnable() {

            @Override
            public void run() {
                Shell shell = new Shell(display);
                final WritableList list = new WritableList();
                Button button = new Button(shell, SWT.PUSH);
                button.setText("add");
                button.addSelectionListener(new SelectionAdapter() {

                    @Override
                    public void widgetSelected(org.eclipse.swt.events.SelectionEvent e) {
                        list.add(0, new Counter());
                    }
                });
                final Composite composite = new Composite(shell, SWT.None);
                new CompositeUpdater(composite, list) {

                    @Override
                    protected Widget createWidget(int index) {
                        Label label = new Label(composite, SWT.BORDER);
                        //requestLayout(label);
                        return label;
                    }

                    @Override
                    protected void updateWidget(Widget widget, Object element) {
                        ((Label) widget).setText(((Counter) element).getValue() + "");
                        requestLayout((Label) widget);
                    }
                };
                GridLayoutFactory.fillDefaults().numColumns(10).generateLayout(composite);
                GridDataFactory.fillDefaults().grab(true, true).applyTo(composite);
                GridLayoutFactory.fillDefaults().generateLayout(shell);
                shell.pack();
                shell.open();
                while (!shell.isDisposed()) {
                    if (!display.readAndDispatch())
                        display.sleep();
                }
            }
        });
        display.dispose();
    }

    static Timer timer = new Timer(true);

    static class Counter extends WritableValue {

         Counter() {
            super(Integer.valueOf(0), Integer.class);
            scheduleIncrementTask();
        }

        private void scheduleIncrementTask() {
            timer.schedule(new TimerTask() {

                @Override
                public void run() {
                    // we have to get onto the realm (UI thread) to perform the
                    // increment
                    getRealm().asyncExec(new Runnable() {

                        @Override
                        public void run() {
                            Integer currentVal = (Integer) getValue();
                            setValue(Integer.valueOf(currentVal.intValue() + 1));
                        }
                    });
                    scheduleIncrementTask();
                }
            }, 1000);
        }
    }
}
