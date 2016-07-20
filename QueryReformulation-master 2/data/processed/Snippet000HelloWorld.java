/***/
package org.eclipse.jface.examples.databinding.snippets;

import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.PojoProperties;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.jface.databinding.swt.DisplayRealm;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
* Hello, databinding. Bind changes in a GUI to a Model object but don't worry
* about propagating changes from the Model to the GUI.
* <p>
* Illustrates the basic Model-ViewModel-Binding-View architecture typically
* used in data binding applications.
*/
public class Snippet000HelloWorld {

    public static void main(String[] args) {
        Display display = new Display();
        final ViewModel viewModel = new ViewModel();
        Realm.runWithDefault(DisplayRealm.getRealm(display), new Runnable() {

            @Override
            public void run() {
                final Shell shell = new View(viewModel).createShell();
                // The SWT event loop
                Display display = Display.getCurrent();
                while (!shell.isDisposed()) {
                    if (!display.readAndDispatch()) {
                        display.sleep();
                    }
                }
            }
        });
        // Print the results
        System.out.println("person.getName() = " + viewModel.getPerson().getName());
    }

    // listener methods.
    static class Person {

        // A property...
        String name = "HelloWorld";

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    // retrieve, this ViewModel just instantiates a model object to edit.
    static class ViewModel {

        // The model to bind
        private Person person = new Person();

        public Person getPerson() {
            return person;
        }
    }

    // The GUI view
    static class View {

        private ViewModel viewModel;

        private Text name;

        public  View(ViewModel viewModel) {
            this.viewModel = viewModel;
        }

        public Shell createShell() {
            Display display = Display.getDefault();
            Shell shell = new Shell(display);
            shell.setLayout(new RowLayout(SWT.VERTICAL));
            name = new Text(shell, SWT.BORDER);
            DataBindingContext bindingContext = new DataBindingContext();
            Person person = viewModel.getPerson();
            bindingContext.bindValue(WidgetProperties.text(SWT.Modify).observe(name), PojoProperties.value("name").observe(person));
            // Open and return the Shell
            shell.pack();
            shell.open();
            return shell;
        }
    }
}
