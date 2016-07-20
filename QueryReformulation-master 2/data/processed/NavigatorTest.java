/***/
package org.eclipse.ui.tests.navigator;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.PartInitException;

/**
* Tests the Resource Navigator view.
*/
public class NavigatorTest extends AbstractNavigatorTest {

    public  NavigatorTest(String testName) {
        super(testName);
    }

    /**
* Tests that the Navigator is initially populated with
* the correct elements from the workspace.
*/
    public void testInitialPopulation() throws CoreException, PartInitException {
        createTestFile();
        showNav();
        // test its initial content by setting and getting selection on the file
        ISelectionProvider selProv = navigator.getSite().getSelectionProvider();
        StructuredSelection sel = new StructuredSelection(testFile);
        selProv.setSelection(sel);
        assertEquals(sel.size(), ((IStructuredSelection) selProv.getSelection()).size());
        assertEquals(sel.getFirstElement(), ((IStructuredSelection) selProv.getSelection()).getFirstElement());
    }

    /**
* Tests that the Navigator updates properly when a file is added to the workbench.
*/
    public void testFileAddition() throws CoreException, PartInitException {
        // create the project and folder before the Navigator is shown
        createTestFolder();
        showNav();
        // create the file after the Navigator is shown
        createTestFile();
        // test its initial content by setting and getting selection on the file
        ISelectionProvider selProv = navigator.getSite().getSelectionProvider();
        StructuredSelection sel = new StructuredSelection(testFile);
        selProv.setSelection(sel);
        assertEquals(sel.size(), ((IStructuredSelection) selProv.getSelection()).size());
        assertEquals(sel.getFirstElement(), ((IStructuredSelection) selProv.getSelection()).getFirstElement());
    }
}
