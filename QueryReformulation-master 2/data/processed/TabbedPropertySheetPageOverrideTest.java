/***/
package org.eclipse.ui.tests.views.properties.tabbed;

import junit.framework.TestCase;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.tests.views.properties.tabbed.model.Error;
import org.eclipse.ui.tests.views.properties.tabbed.model.File;
import org.eclipse.ui.tests.views.properties.tabbed.model.Folder;
import org.eclipse.ui.tests.views.properties.tabbed.model.Information;
import org.eclipse.ui.tests.views.properties.tabbed.model.Warning;
import org.eclipse.ui.tests.views.properties.tabbed.override.OverrideTestsView;
import org.eclipse.ui.tests.views.properties.tabbed.views.TestsPerspective;
import org.eclipse.ui.views.properties.tabbed.ITabDescriptor;

/**
* Tests for the override tabs support.
*
* @author Anthony Hunter
* @since 3.4
*/
public class TabbedPropertySheetPageOverrideTest extends TestCase {

    private OverrideTestsView overrideTestsView;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        /**
* Close the existing perspectives.
*/
        IWorkbenchWindow workbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
        assertNotNull(workbenchWindow);
        IWorkbenchPage workbenchPage = workbenchWindow.getActivePage();
        assertNotNull(workbenchPage);
        workbenchPage.closeAllPerspectives(false, false);
        /**
* Open the tests perspective.
*/
        PlatformUI.getWorkbench().showPerspective(TestsPerspective.TESTS_PERSPECTIVE_ID, workbenchWindow);
        /**
* Open the dynamic tests view.
*/
        IViewPart view = workbenchPage.showView(OverrideTestsView.OVERRIDE_TESTS_VIEW_ID);
        assertNotNull(view);
        assertTrue(view instanceof OverrideTestsView);
        overrideTestsView = (OverrideTestsView) view;
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        /**
* Bug 175070: Make sure the views have finished painting.
*/
        while (Display.getCurrent().readAndDispatch()) {
        //
        }
    }

    /**
* When nothing is selected, there is one tab called empty.
* <p>
* Normally an empty structured selection shows "Properties are not
* available". The override tests provide a custom selection provider that
* allows for the display of a tab and section when the selection is empty.
*/
    public void test_tabForEmpty() {
        /**
* select nothing
*/
        overrideTestsView.setSelection(null);
        ITabDescriptor[] tabDescriptors = overrideTestsView.getTabbedPropertySheetPage().getActiveTabs();
        /**
* First tab is "Empty Item"
*/
        //$NON-NLS-1$
        assertEquals("Empty Item", tabDescriptors[0].getLabel());
        /**
* No second tab
*/
        assertEquals(1, tabDescriptors.length);
    }

    /**
* Tests for the dynamic tabs and sections (items) when the Error element is
* selected.
*/
    public void test_tabForError() {
        /**
* select "Error"
*/
        overrideTestsView.setSelection(Error.class);
        ITabDescriptor[] tabDescriptors = overrideTestsView.getTabbedPropertySheetPage().getActiveTabs();
        /**
* First tab is "Information".
*/
        //$NON-NLS-1$
        assertEquals("Information", tabDescriptors[0].getLabel());
        /**
* Second tab is "Warning".
*/
        //$NON-NLS-1$
        assertEquals("Warning", tabDescriptors[1].getLabel());
        /**
* Third tab is "Error" and is selected.
*/
        //$NON-NLS-1$
        assertEquals("Error", tabDescriptors[2].getLabel());
        assertEquals("Error", overrideTestsView.getTabbedPropertySheetPage().getSelectedTab().getLabel());
        /**
* No fourth tab
*/
        assertEquals(3, tabDescriptors.length);
    }

    /**
* Tests for the dynamic tabs and sections (items) when the File element is
* selected.
*/
    public void test_tabForFile() {
        /**
* select "File"
*/
        overrideTestsView.setSelection(File.class);
        ITabDescriptor[] tabDescriptors = overrideTestsView.getTabbedPropertySheetPage().getActiveTabs();
        /**
* First tab is "File" and is selected.
*/
        //$NON-NLS-1$
        assertEquals("File", tabDescriptors[0].getLabel());
        assertEquals("File", overrideTestsView.getTabbedPropertySheetPage().getSelectedTab().getLabel());
        /**
* Second tab is "Folder" and is selected.
*/
        //$NON-NLS-1$
        assertEquals("Folder", tabDescriptors[1].getLabel());
        /**
* No third tab
*/
        assertEquals(2, tabDescriptors.length);
    }

    /**
* Tests for the dynamic tabs and sections (items) when the Folder element
* is selected.
*/
    public void test_tabForFolder() {
        /**
* select "Folder"
*/
        overrideTestsView.setSelection(Folder.class);
        ITabDescriptor[] tabDescriptors = overrideTestsView.getTabbedPropertySheetPage().getActiveTabs();
        /**
* First tab is "File".
*/
        //$NON-NLS-1$
        assertEquals("File", tabDescriptors[0].getLabel());
        //$NON-NLS-1$
        assertEquals("Folder", tabDescriptors[1].getLabel());
        assertEquals("Folder", overrideTestsView.getTabbedPropertySheetPage().getSelectedTab().getLabel());
        assertEquals(2, tabDescriptors.length);
    }

    /**
* Tests for the dynamic tabs and sections (items) when the Information
* element is selected.
*/
    public void test_tabForInformation() {
        /**
* select "Information"
*/
        overrideTestsView.setSelection(Information.class);
        ITabDescriptor[] tabDescriptors = overrideTestsView.getTabbedPropertySheetPage().getActiveTabs();
        /**
* First tab is "Information" and is selected.
*/
        //$NON-NLS-1$
        assertEquals("Information", tabDescriptors[0].getLabel());
        assertEquals("Information", overrideTestsView.getTabbedPropertySheetPage().getSelectedTab().getLabel());
        //$NON-NLS-1$
        assertEquals("Warning", tabDescriptors[1].getLabel());
        /**
* Third tab is "Error".
*/
        //$NON-NLS-1$
        assertEquals("Error", tabDescriptors[2].getLabel());
        assertEquals(3, tabDescriptors.length);
    }

    /**
* Tests for the dynamic tabs and sections (items) when the Warning element
* is selected.
*/
    public void test_tabForWarning() {
        /**
* select "Warning"
*/
        overrideTestsView.setSelection(Warning.class);
        ITabDescriptor[] tabDescriptors = overrideTestsView.getTabbedPropertySheetPage().getActiveTabs();
        //$NON-NLS-1$
        assertEquals("Information", tabDescriptors[0].getLabel());
        /**
* Second tab is "Warning" and is selected.
*/
        //$NON-NLS-1$
        assertEquals("Warning", tabDescriptors[1].getLabel());
        assertEquals("Warning", overrideTestsView.getTabbedPropertySheetPage().getSelectedTab().getLabel());
        //$NON-NLS-1$
        assertEquals("Error", tabDescriptors[2].getLabel());
        assertEquals(3, tabDescriptors.length);
    }
}
