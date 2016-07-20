/***/
package org.eclipse.jface.tests.wizards;

import junit.framework.TestCase;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;

public class ButtonAlignmentTest extends TestCase {

    private TheTestWizard wizard;

    private TheTestWizardDialog dialog;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        // ensure we've initialized a display for this thread
        Display.getDefault();
    }

    @Override
    protected void tearDown() throws Exception {
        if (dialog != null && dialog.getShell() != null && !dialog.getShell().isDisposed()) {
            dialog.close();
        }
        super.tearDown();
    }

    public  ButtonAlignmentTest() {
        super("ButtonAlignmentTest");
    }

    public void testButtonAlignment() {
        wizard = new TheTestWizard();
        dialog = new TheTestWizardDialog(null, wizard);
        dialog.create();
        dialog.open();
        // retrieve the parent control for the button bar
        Composite parent = dialog.getFinishedButton().getParent();
        Control[] children = parent.getChildren();
        assertEquals(//$NON-NLS-1$
        "There should be three children, a composite for back/next buttons, the finish button, and the cancel button", 3, children.length);
        // first children should be the Composite holding the 'Back' and 'Next'
        // buttons
        assertTrue(children[0] instanceof Composite);
        Composite backNextParent = (Composite) children[0];
        // retrieve its children and verify its contents
        Control[] backNextChildren = backNextParent.getChildren();
        assertEquals("Back button should be the first button", //$NON-NLS-1$
        dialog.getBackButton(), backNextChildren[0]);
        assertEquals("Next button should be the second button", //$NON-NLS-1$
        dialog.getNextButton(), backNextChildren[1]);
        // verify button alignment based on the platform's dismissal alignment
        int finishIndex = parent.getDisplay().getDismissalAlignment() == SWT.LEFT ? 1 : 2;
        int cancelIndex = parent.getDisplay().getDismissalAlignment() == SWT.LEFT ? 2 : 1;
        assertEquals("Finish button's alignment is off", dialog.getFinishedButton(), //$NON-NLS-1$
        children[finishIndex]);
        assertEquals("Cancel button's alignment is off", dialog.getCancelButton(), //$NON-NLS-1$
        children[cancelIndex]);
    }

    public void testButtonAlignmentWithoutBackNextButtons() {
        wizard = new TheTestWizard() {

            @Override
            public void addPages() {
                // only add one page so there are no 'Back' or 'Next' buttons
                addPage(new TheTestWizardPage(page1Name));
            }
        };
        dialog = new TheTestWizardDialog(null, wizard);
        dialog.create();
        dialog.open();
        // retrieve the parent control for the button bar
        Composite parent = dialog.getFinishedButton().getParent();
        Control[] children = parent.getChildren();
        assertEquals(//$NON-NLS-1$
        "There should be two children, the finish button, and the cancel button", 2, children.length);
        // verify button alignment based on the platform's dismissal alignment
        int finishIndex = parent.getDisplay().getDismissalAlignment() == SWT.LEFT ? 0 : 1;
        int cancelIndex = parent.getDisplay().getDismissalAlignment() == SWT.LEFT ? 1 : 0;
        assertEquals("Finish button's alignment is off", dialog.getFinishedButton(), //$NON-NLS-1$
        children[finishIndex]);
        assertEquals("Cancel button's alignment is off", dialog.getCancelButton(), //$NON-NLS-1$
        children[cancelIndex]);
    }

    public void testBug270174() {
        wizard = new TheTestWizard() {

            @Override
            public boolean canFinish() {
                // that the 'Next' button is the default button
                return false;
            }
        };
        dialog = new TheTestWizardDialog(null, wizard);
        dialog.create();
        dialog.open();
        // retrieve the parent control for the button bar
        Composite parent = dialog.getFinishedButton().getParent();
        Control[] children = parent.getChildren();
        assertEquals(//$NON-NLS-1$
        "There should be three children, a composite for back/next buttons, the finish button, and the cancel button", 3, children.length);
        // first children should be the Composite holding the 'Back' and 'Next'
        // buttons
        assertTrue(children[0] instanceof Composite);
        Composite backNextParent = (Composite) children[0];
        // retrieve its children and verify its contents
        Control[] backNextChildren = backNextParent.getChildren();
        assertEquals("Back button should be the first button", //$NON-NLS-1$
        dialog.getBackButton(), backNextChildren[0]);
        assertEquals("Next button should be the second button", //$NON-NLS-1$
        dialog.getNextButton(), backNextChildren[1]);
        // verify button alignment based on the platform's dismissal alignment
        int finishIndex = parent.getDisplay().getDismissalAlignment() == SWT.LEFT ? 1 : 2;
        int cancelIndex = parent.getDisplay().getDismissalAlignment() == SWT.LEFT ? 2 : 1;
        assertEquals("Finish button's alignment is off", dialog.getFinishedButton(), //$NON-NLS-1$
        children[finishIndex]);
        assertEquals("Cancel button's alignment is off", dialog.getCancelButton(), //$NON-NLS-1$
        children[cancelIndex]);
    }
}
