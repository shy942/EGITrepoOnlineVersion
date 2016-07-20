/***/
package org.eclipse.jface.tests.databinding.scenarios;

import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.examples.databinding.model.Adventure;
import org.eclipse.jface.examples.databinding.model.SampleData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;

/**
* To run the tests in this class, right-click and select "Run As JUnit Plug-in
* Test". This will also start an Eclipse instance. To clean up the launch
* configuration, open up its "Main" tab and select "[No Application] - Headless
* Mode" as the application to run.
*/
public class ButtonControlScenario extends ScenariosTestCase {

    private Adventure adventure;

    private Button button;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        // do any setup work here
        button = new Button(getComposite(), SWT.CHECK);
        adventure = SampleData.WINTER_HOLIDAY;
    }

    @Override
    protected void tearDown() throws Exception {
        button.dispose();
        super.tearDown();
    }

    public void testScenario01() {
        // Bind the button's selection to the adventure "isPetsAllowed"
        getDbc().bindValue(SWTObservables.observeSelection(button), BeansObservables.observeValue(adventure, "petsAllowed"));
        // Check the model and GUI are in the same state
        assertEquals(button.getSelection(), adventure.isPetsAllowed());
        // Change the model and check the GUI is updated
        boolean newBoolean = !adventure.isPetsAllowed();
        adventure.setPetsAllowed(newBoolean);
        assertEquals(newBoolean, adventure.isPetsAllowed());
        assertEquals(button.getSelection(), newBoolean);
        // Change the GUI and check the model
        newBoolean = !newBoolean;
        button.setSelection(newBoolean);
        button.notifyListeners(SWT.Selection, null);
        assertEquals(newBoolean, adventure.isPetsAllowed());
        newBoolean = !newBoolean;
        final boolean finalNewBoolean = newBoolean;
        adventure.setPetsAllowed(finalNewBoolean);
        spinEventLoop(0);
        assertEquals(newBoolean, button.getSelection());
    }

    public void testScenario02() {
        // Test with an SWT.Toggle button
        button.dispose();
        button = new Button(getComposite(), SWT.TOGGLE);
        // Bind the button's selection to the adventure "isPetsAllowed"
        getDbc().bindValue(SWTObservables.observeSelection(button), BeansObservables.observeValue(adventure, "petsAllowed"));
        // Check the model and GUI are in the same state
        assertEquals(button.getSelection(), adventure.isPetsAllowed());
        // Change the model and check the GUI is updated
        boolean newBoolean = !adventure.isPetsAllowed();
        adventure.setPetsAllowed(newBoolean);
        assertEquals(newBoolean, adventure.isPetsAllowed());
        assertEquals(button.getSelection(), newBoolean);
        // Change the GUI and check the model
        newBoolean = !newBoolean;
        button.setSelection(newBoolean);
        button.notifyListeners(SWT.Selection, null);
        assertEquals(newBoolean, adventure.isPetsAllowed());
    }

    public void testScenario03() {
        // Test with an SWT.Radio button
        button.dispose();
        button = new Button(getComposite(), SWT.RADIO);
        // Bind the button's selection to the adventure "isPetsAllowed"
        getDbc().bindValue(SWTObservables.observeSelection(button), BeansObservables.observeValue(adventure, "petsAllowed"));
        // Check the model and GUI are in the same state
        assertEquals(button.getSelection(), adventure.isPetsAllowed());
        // Change the model and check the GUI is updated
        boolean newBoolean = !adventure.isPetsAllowed();
        adventure.setPetsAllowed(newBoolean);
        assertEquals(newBoolean, adventure.isPetsAllowed());
        assertEquals(button.getSelection(), newBoolean);
        // Change the GUI and check the model
        newBoolean = !newBoolean;
        button.setSelection(newBoolean);
        button.notifyListeners(SWT.Selection, null);
        assertEquals(newBoolean, adventure.isPetsAllowed());
    }
}
