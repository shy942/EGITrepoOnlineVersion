/***/
package org.eclipse.jface.examples.databinding.snippets;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.HashSet;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.beans.BeanProperties;
import org.eclipse.core.databinding.observable.Observables;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.jface.databinding.swt.DisplayRealm;
import org.eclipse.jface.databinding.swt.WidgetProperties;
import org.eclipse.jface.databinding.viewers.ObservableMapLabelProvider;
import org.eclipse.jface.databinding.viewers.ViewersObservables;
import org.eclipse.jface.layout.GridLayoutFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.ListViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.List;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
* Demonstrates nested selection.<br>
* At the first level, user may select a person.<br>
* At the second level, user may select a city to associate with the selected<br>
* person or edit the person's name.
*/
public class Snippet001NestedSelectionWithCombo {

    public static void main(String[] args) {
        ViewModel viewModel = new ViewModel();
        Shell shell = new View(viewModel).createShell();
        // The SWT event loop
        Display display = Display.getCurrent();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
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

    // to instances of this class will automatically be propogated to the UI.
    public static class Person extends AbstractModelObject {

        // Constructor
        public  Person(String name, String city) {
            this.name = name;
            this.city = city;
        }

        // Some JavaBean bound properties...
        String name;

        String city;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            String oldValue = this.name;
            this.name = name;
            firePropertyChange("name", oldValue, name);
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            String oldValue = this.city;
            this.city = city;
            firePropertyChange("city", oldValue, city);
        }
    }

    // This ViewModel also implements JavaBean bound properties.
    static class ViewModel extends AbstractModelObject {

        // The model to bind
        private ArrayList people = new ArrayList();

        {
            people.add(new Person("Wile E. Coyote", "Tucson"));
            people.add(new Person("Road Runner", "Lost Horse"));
            people.add(new Person("Bugs Bunny", "Forrest"));
        }

        // Choice of cities for the Combo
        private ArrayList cities = new ArrayList();

        {
            cities.add("Tucson");
            cities.add("AcmeTown");
            cities.add("Lost Horse");
            cities.add("Forrest");
            cities.add("Lost Mine");
        }

        public ArrayList getPeople() {
            return people;
        }

        public ArrayList getCities() {
            return cities;
        }
    }

    // The GUI view
    static class View {

        private ViewModel viewModel;

        public  View(ViewModel viewModel) {
            this.viewModel = viewModel;
        }

        public Shell createShell() {
            // Build a UI
            Shell shell = new Shell(Display.getCurrent());
            Realm realm = DisplayRealm.getRealm(shell.getDisplay());
            List peopleList = new List(shell, SWT.BORDER);
            Text name = new Text(shell, SWT.BORDER);
            Combo city = new Combo(shell, SWT.BORDER | SWT.READ_ONLY);
            ListViewer peopleListViewer = new ListViewer(peopleList);
            IObservableMap attributeMap = BeanProperties.value(Person.class, "name").observeDetail(Observables.staticObservableSet(realm, new HashSet(viewModel.getPeople())));
            peopleListViewer.setLabelProvider(new ObservableMapLabelProvider(attributeMap));
            peopleListViewer.setContentProvider(new ArrayContentProvider());
            peopleListViewer.setInput(viewModel.getPeople());
            DataBindingContext dbc = new DataBindingContext(realm);
            IObservableValue selectedPerson = ViewersObservables.observeSingleSelection(peopleListViewer);
            Class selectedPersonValueType = null;
            if (selectedPerson.getValueType() instanceof Class<?>) {
                selectedPersonValueType = (Class) selectedPerson.getValueType();
            }
            dbc.bindValue(WidgetProperties.text(SWT.Modify).observe(name), BeanProperties.value(selectedPersonValueType, "name", String.class).observeDetail(selectedPerson));
            ComboViewer cityViewer = new ComboViewer(city);
            cityViewer.setContentProvider(new ArrayContentProvider());
            cityViewer.setInput(viewModel.getCities());
            IObservableValue citySelection = ViewersObservables.observeSingleSelection(cityViewer);
            dbc.bindValue(citySelection, BeanProperties.value(selectedPersonValueType, "city", String.class).observeDetail(selectedPerson));
            GridLayoutFactory.swtDefaults().applyTo(shell);
            // Open and return the Shell
            shell.pack();
            shell.open();
            return shell;
        }
    }
}
