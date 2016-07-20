/***/
package org.eclipse.jface.examples.databinding.snippets;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.list.IObservableList;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.masterdetail.IObservableFactory;
import org.eclipse.core.databinding.observable.masterdetail.MasterDetailObservables;
import org.eclipse.jface.databinding.swt.DisplayRealm;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

/**
* Shows how to bind a Combo so that when update its items, the selection is
* retained if at all possible.
*
* @since 3.2
*/
public class Snippet002UpdateComboRetainSelection {

    public static void main(String[] args) {
        final Display display = new Display();
        Realm.runWithDefault(DisplayRealm.getRealm(display), new Runnable() {

            @Override
            public void run() {
                ViewModel viewModel = new ViewModel();
                Shell shell = new View(viewModel).createShell();
                // The SWT event loop
                while (!shell.isDisposed()) {
                    if (!display.readAndDispatch()) {
                        display.sleep();
                    }
                }
                // Print the results
                System.out.println(viewModel.getText());
            }
        });
        display.dispose();
    }

    // Minimal JavaBeans support
    public abstract static class AbstractModelObject {

        private PropertyChangeSupport propertyChangeSupport = new PropertyChangeSupport(this);

        public void addPropertyChangeListener(PropertyChangeListener listener) {
            propertyChangeSupport.addPropertyChangeListener(listener);
        }

        public void addPropertyChangeListener(String propertyName, PropertyChangeListener listener) {
            propertyChangeSupport.addPropertyChangeListener(propertyName, listener);
        }

        public void removePropertyChangeListener(PropertyChangeListener listener) {
            propertyChangeSupport.removePropertyChangeListener(listener);
        }

        public void removePropertyChangeListener(String propertyName, PropertyChangeListener listener) {
            propertyChangeSupport.removePropertyChangeListener(propertyName, listener);
        }

        protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
            propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
        }
    }

    // The View's model--the root of our Model graph for this particular GUI.
    public static class ViewModel extends AbstractModelObject {

        private String text = "beef";

        private List choices = new ArrayList();

        {
            choices.add("pork");
            choices.add("beef");
            choices.add("poultry");
            choices.add("vegatables");
        }

        public List getChoices() {
            return choices;
        }

        public void setChoices(List choices) {
            List old = this.choices;
            this.choices = choices;
            firePropertyChange("choices", old, choices);
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            String oldValue = this.text;
            this.text = text;
            firePropertyChange("text", oldValue, text);
        }
    }

    // The GUI view
    static class View {

        private ViewModel viewModel;

        /**
* used to make a new choices array unique
*/
        static int count;

        public  View(ViewModel viewModel) {
            this.viewModel = viewModel;
        }

        public Shell createShell() {
            // Build a UI
            Shell shell = new Shell(Display.getCurrent());
            shell.setLayout(new RowLayout(SWT.VERTICAL));
            Combo combo = new Combo(shell, SWT.BORDER | SWT.READ_ONLY);
            Button reset = new Button(shell, SWT.NULL);
            reset.setText("reset collection");
            reset.addSelectionListener(new SelectionAdapter() {

                @Override
                public void widgetSelected(SelectionEvent e) {
                    List newList = new ArrayList();
                    newList.add("Chocolate");
                    newList.add("Vanilla");
                    newList.add("Mango Parfait");
                    newList.add("beef");
                    newList.add("Cheesecake");
                    newList.add(Integer.toString(++count));
                    viewModel.setChoices(newList);
                }
            });
            // Print value out first
            System.out.println(viewModel.getText());
            DataBindingContext dbc = new DataBindingContext();
            IObservableList list = MasterDetailObservables.detailList(BeanProperties.value(viewModel.getClass(), "choices").observe(viewModel), getListDetailFactory(), String.class);
            dbc.bindList(WidgetProperties.items().observe(combo), list);
            dbc.bindValue(WidgetProperties.text().observe(combo), BeanProperties.value(viewModel.getClass(), "text").observe(viewModel));
            // Open and return the Shell
            shell.pack();
            shell.open();
            return shell;
        }
    }

    private static IObservableFactory getListDetailFactory() {
        return new IObservableFactory() {

            @Override
            public IObservable createObservable(Object target) {
                WritableList list = WritableList.withElementType(String.class);
                list.addAll((Collection) target);
                return list;
            }
        };
    }
}
