/***/
package org.eclipse.jface.tests.databinding.scenarios;

import org.eclipse.core.databinding.beans.BeansObservables;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.jface.examples.databinding.model.Adventure;
import org.eclipse.jface.examples.databinding.model.SampleData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;

/**
* To run the tests in this class, right-click and select "Run As JUnit Plug-in
* Test". This will also start an Eclipse instance. To clean up the launch
* configuration, open up its "Main" tab and select "[No Application] - Headless
* Mode" as the application to run.
*/
public class LabelControlScenario extends ScenariosTestCase {

    private Adventure adventure;

    private Label label;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        // do any setup work here
        label = new Label(getComposite(), SWT.NONE);
        adventure = SampleData.WINTER_HOLIDAY;
    }

    @Override
    protected void tearDown() throws Exception {
        // do any teardown work here
        super.tearDown();
        label.dispose();
        label = null;
    }

    public void testScenario01() {
        // Bind the adventure "name" property to a label control
        // Change the UI and verify the model and UI are the same value
        // Change the model and verify the UI changes
        getDbc().bindValue(SWTObservables.observeText(label), BeansObservables.observeValue(adventure, "name"));
        assertEquals(adventure.getName(), label.getText());
        adventure.setName("France");
        assertEquals("France", label.getText());
        adventure.setName("Climb Everest");
        spinEventLoop(0);
        assertEquals("Climb Everest", label.getText());
    }
}
