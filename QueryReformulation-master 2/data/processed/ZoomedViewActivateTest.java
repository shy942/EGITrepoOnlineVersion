/***/
package org.eclipse.ui.tests.zoom;

import org.eclipse.ui.IWorkbenchPart;

/**
* @since 3.1
*/
public class ZoomedViewActivateTest extends ActivateTest {

    /**
* @param name
*/
    public  ZoomedViewActivateTest(String name) {
        super(name);
    }

    @Override
    public IWorkbenchPart getStackedPart1() {
        return stackedView1;
    }

    @Override
    public IWorkbenchPart getStackedPart2() {
        return stackedView2;
    }

    @Override
    public IWorkbenchPart getUnstackedPart() {
        return unstackedView;
    }

    /**
* <p>Test: Zoom a view then activate an editor</p>
* <p>Expected result: page unzooms</p>
*/
    public void testActivateEditor() {
        // We allow an editor to be activated *without* unzooming
        System.out.println("Bogus Test: " + getName());
    //        zoom(stackedView1);
    //        page.activate(editor1);
    //
    //        assertZoomed(null);
    //        assertActive(editor1);
    }
}
