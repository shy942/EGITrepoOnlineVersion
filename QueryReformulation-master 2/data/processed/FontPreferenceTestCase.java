/***/
package org.eclipse.ui.tests.preferences;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.PreferenceConverter;
import org.eclipse.jface.resource.FontRegistry;
import org.eclipse.jface.resource.JFaceResources;
import org.eclipse.jface.util.ILogger;
import org.eclipse.jface.util.Policy;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.internal.WorkbenchPlugin;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.ui.tests.harness.util.UITestCase;

/**
* The FontPreferenceTestCase tests adding fonts to
* the preference store and what occurs if the values
* are bogus
*/
public class FontPreferenceTestCase extends UITestCase {

    public String BAD_FONT_DEFINITION = "BadFont-regular-10";

    public String TEST_FONT_ID = "org.eclipse.jface.tests.preference.testfont";

    public String MISSING_FONT_ID = "org.eclipse.jface.tests.preference.missingfont";

    private IPreferenceStore preferenceStore;

    /**
* Constructor for FontPreferenceTestCase.
* @param testName
*/
    public  FontPreferenceTestCase(String testName) {
        super(testName);
    }

    @Override
    protected void doSetUp() throws Exception {
        super.doSetUp();
        AbstractUIPlugin plugin = WorkbenchPlugin.getDefault();
        preferenceStore = plugin.getPreferenceStore();
        //Set up the bogus entry for the bad first test
        FontData bogusData = new FontData();
        bogusData.setName("BadData");
        bogusData.setHeight(11);
        FontData[] storedValue = new FontData[2];
        //We assume here that the text font is OK
        storedValue[0] = bogusData;
        storedValue[1] = (PreferenceConverter.getDefaultFontDataArray(preferenceStore, JFaceResources.TEXT_FONT))[0];
        PreferenceConverter.setValue(preferenceStore, TEST_FONT_ID, storedValue);
        PreferenceConverter.setDefault(preferenceStore, TEST_FONT_ID, storedValue);
    }

    /**
* Test for a valid font like the test font. The first good one
* we should find should be the first one in the list.
*/
    public void testGoodFontDefinition() {
        FontRegistry fontRegistry = JFaceResources.getFontRegistry();
        FontData[] currentTextFonts = PreferenceConverter.getFontDataArray(preferenceStore, JFaceResources.TEXT_FONT);
        FontData[] bestFont = fontRegistry.bestDataArray(currentTextFonts, Display.getCurrent());
        //Assert that it is the first font that we get as the
        //valid one
        assertEquals(bestFont[0].getName(), currentTextFonts[0].getName());
        assertEquals(bestFont[0].getHeight(), currentTextFonts[0].getHeight());
    }

    /**
* Test that if the first font in the list is bad that the
* second one comes back as valid.
*/
    public void testBadFirstFontDefinition() {
        FontRegistry fontRegistry = JFaceResources.getFontRegistry();
        FontData[] currentTestFonts = PreferenceConverter.getFontDataArray(preferenceStore, TEST_FONT_ID);
        FontData[] bestFont = fontRegistry.filterData(currentTestFonts, Display.getCurrent());
        //Assert that it is the second font that we get as the
        //valid one
        assertEquals(bestFont[0].getName(), currentTestFonts[1].getName());
        assertEquals(bestFont[0].getHeight(), currentTestFonts[1].getHeight());
    }

    /**
* Test that the no valid font is returned when the entry
* is missing.
*/
    public void testNoFontDefinition() {
        FontRegistry fontRegistry = JFaceResources.getFontRegistry();
        FontData[] currentTestFonts = PreferenceConverter.getFontDataArray(preferenceStore, MISSING_FONT_ID);
        FontData[] bestFont = fontRegistry.filterData(currentTestFonts, Display.getCurrent());
        FontData[] systemFontData = Display.getCurrent().getSystemFont().getFontData();
        //Assert that the first font is the system font
        assertEquals(bestFont[0].getName(), systemFontData[0].getName());
        assertEquals(bestFont[0].getHeight(), systemFontData[0].getHeight());
    }

    /**
* The test added to assess results of accessing FontRegistry from a non-UI
* thread. See bug 230360.
*/
    public void testNonUIThreadFontAccess() {
        // create a separate font registry to avoid contaminating other tests
        //$NON-NLS-1$
        final FontRegistry fontRegistry = new FontRegistry("org.eclipse.jface.resource.jfacefonts");
        // pre-calculate the default font; calling it in worker thread will only cause SWTException
        Font defaultFont = fontRegistry.defaultFont();
        // avoids compiler warning
        defaultFont.toString();
        // redirect logging so that we catch the error log
        final boolean[] errorLogged = new boolean[] { false };
        ILogger logger = Policy.getLog();
        try {
            Policy.setLog(new ILogger() {

                @Override
                public void log(IStatus status) {
                    if (status != null && status.getSeverity() == IStatus.ERROR && status.getPlugin().equals(Policy.JFACE)) {
                        errorLogged[0] = true;
                    }
                }
            });
            Job job = new Job("Non-UI thread FontRegistry Access Test") {

                @Override
                protected IStatus run(IProgressMonitor monitor) {
                    // this should produce no exception, but should log a error
                    boolean created = checkFont(fontRegistry);
                    assertFalse(created);
                    return Status.OK_STATUS;
                }
            };
            job.schedule();
            job.join();
            assertTrue(errorLogged[0]);
        } catch (InterruptedException e) {
            fail("Worker thread was interrupted in the FontRegistry access test");
        } finally {
            Policy.setLog(logger);
        }
        // now let's try to create the same font in the UI thread and check that the correct
        boolean created = checkFont(fontRegistry);
        assertTrue(created);
    }

    public boolean checkFont(final FontRegistry fontRegistry) {
        // Create a font description that will use default font with height increased by 20
        FontData[] data = fontRegistry.defaultFont().getFontData();
        int defaultHeight = data[0].getHeight();
        int testHeight = defaultHeight + 20;
        data[0].setHeight(testHeight);
        fontRegistry.put("testFont", data);
        // Get the font from the registry
        // getItalic("testFont");
        Font testFont = fontRegistry.get("testFont");
        // Check the font size
        FontData[] receivedData = testFont.getFontData();
        int receivedHeight = receivedData[0].getHeight();
        // so test size not being the default rather then being exactly the testHeight
        return (receivedHeight != defaultHeight);
    }
}
