/***/
package org.eclipse.ui.tests.views.properties.tabbed;

import junit.framework.TestCase;
import org.eclipse.swt.widgets.Display;
import org.eclipse.jface.text.IDocument;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.tests.views.properties.tabbed.text.TextTestsLabelSection;
import org.eclipse.ui.tests.views.properties.tabbed.text.TextTestsView;
import org.eclipse.ui.tests.views.properties.tabbed.views.TestsPerspective;
import org.eclipse.ui.views.properties.tabbed.ISection;
import org.eclipse.ui.views.properties.tabbed.ITabDescriptor;
import org.eclipse.ui.views.properties.tabbed.TabContents;

/**
* Tests for the text tests view.
*
* @since 3.4
*/
public class TabbedPropertySheetPageTextTest extends TestCase {

    // in ms
    private static final long TIME_OUT_TO_GET_ACTIVE_TABS = 30000;

    private TextTestsView textTestsView;

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
        IViewPart view = workbenchPage.showView(TextTestsView.TEXT_TESTS_VIEW_ID);
        assertNotNull(view);
        assertTrue(view instanceof TextTestsView);
        textTestsView = (TextTestsView) view;
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
* When text is selected, there is one tab for each selected word.
*/
    public void test_tabForSelectedTextDisplay() {
        IDocument document = textTestsView.getViewer().getDocument();
        document.set("This is a test");
        textTestsView.getViewer().setSelectedRange(0, 14);
        ITabDescriptor[] tabDescriptors = waitForActiveTabs();
        /**
* First tab is "This"
*/
        //$NON-NLS-1$
        assertEquals("This", tabDescriptors[0].getLabel());
        /**
* Second tab is "is"
*/
        //$NON-NLS-1$
        assertEquals("is", tabDescriptors[1].getLabel());
        /**
* Third tab is "a"
*/
        //$NON-NLS-1$
        assertEquals("a", tabDescriptors[2].getLabel());
        /**
* Third tab is "test"
*/
        //$NON-NLS-1$
        assertEquals("test", tabDescriptors[3].getLabel());
        /**
* No fifth tab
*/
        assertEquals(4, tabDescriptors.length);
    }

    /**
* Test changing the selected tab through API (Bug 119085).
*/
    public void test_tabSelectedTab() {
        IDocument document = textTestsView.getViewer().getDocument();
        document.set("The fifth tab is selected");
        textTestsView.getViewer().setSelectedRange(0, 26);
        ITabDescriptor[] tabDescriptors = waitForActiveTabs();
        /**
* First tab is "the" and is selected.
*/
        //$NON-NLS-1$
        assertEquals("The", tabDescriptors[0].getLabel());
        assertEquals("The", textTestsView.getTabbedPropertySheetPage().getSelectedTab().getLabel());
        /**
* Fifth tab is "selected"
*/
        //$NON-NLS-1$
        assertEquals("selected", tabDescriptors[4].getLabel());
        /**
* Set the new selected tab.
*/
        textTestsView.getTabbedPropertySheetPage().setSelectedTab(tabDescriptors[4].getId());
        tabDescriptors = textTestsView.getTabbedPropertySheetPage().getActiveTabs();
        /**
* First tab is "the"
*/
        //$NON-NLS-1$
        assertEquals("The", tabDescriptors[0].getLabel());
        /**
* Fifth tab is "selected" and is selected.
*/
        //$NON-NLS-1$
        assertEquals("selected", tabDescriptors[4].getLabel());
        assertEquals("selected", textTestsView.getTabbedPropertySheetPage().getSelectedTab().getLabel());
    }

    private ITabDescriptor[] waitForActiveTabs() {
        long threshold = System.currentTimeMillis() + TIME_OUT_TO_GET_ACTIVE_TABS;
        ITabDescriptor[] tabDescriptors;
        do {
            textTestsView.getSite().getShell().getDisplay().readAndDispatch();
            tabDescriptors = textTestsView.getTabbedPropertySheetPage().getActiveTabs();
        } while (tabDescriptors.length == 0 && System.currentTimeMillis() < threshold);
        assertTrue("No tab got activated", tabDescriptors.length > 0);
        return tabDescriptors;
    }

    /**
* This test makes sure that the list of sections has only one section. The
* TextTestsTabDescriptor returns two sections and one section is always
* filtered (Bug 245690).
*/
    public void test_listOfSections() {
        IDocument document = textTestsView.getViewer().getDocument();
        document.set("This is a test");
        textTestsView.getViewer().setSelectedRange(0, 14);
        waitForActiveTabs();
        /**
* each tab has one section.
*/
        TabContents tabContents = textTestsView.getTabbedPropertySheetPage().getCurrentTab();
        ISection[] sections = tabContents.getSections();
        assertEquals(1, sections.length);
        assertEquals(TextTestsLabelSection.class, sections[0].getClass());
    }
}
