/***/
package org.eclipse.ui.tests.keys;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Event;
import org.eclipse.ui.keys.KeyStroke;
import org.eclipse.ui.keys.ParseException;
import org.eclipse.ui.keys.SWTKeySupport;
import org.eclipse.ui.tests.harness.util.UITestCase;

/**
* Test for Bug 42035.
*
* @since 3.0
*/
public class Bug42035Test extends UITestCase {

    /**
* Tests the given key event to see if generates the three key strokes
* represented by the strings.
*
* @param keyEvent
*            The key event to generate key strokes from; must not be
*            <code>null</code>
* @param firstMatch
*            The text format of the first match; must not be <code>null</code>.
* @param secondMatch
*            The text format of the second match; must not be <code>null</code>.
* @param thirdMatch
*            The text format of the third match; must not be <code>null</code>.
* @throws ParseException
*             If anyone of the text formats provided cannot be parsed.
*/
    private static void testKeyEvent(Event keyEvent, String firstMatch, String secondMatch, String thirdMatch) throws ParseException {
        KeyStroke desiredKeyStroke = null;
        KeyStroke actualKeyStroke = null;
        // Test the first-level match.
        //$NON-NLS-1$
        desiredKeyStroke = KeyStroke.getInstance(firstMatch);
        actualKeyStroke = SWTKeySupport.convertAcceleratorToKeyStroke(SWTKeySupport.convertEventToUnmodifiedAccelerator(keyEvent));
        assertEquals("Unmodified character with all modifiers doesn't match.", desiredKeyStroke, //$NON-NLS-1$
        actualKeyStroke);
        // Test the second-level match.
        //$NON-NLS-1$
        desiredKeyStroke = KeyStroke.getInstance(secondMatch);
        actualKeyStroke = SWTKeySupport.convertAcceleratorToKeyStroke(SWTKeySupport.convertEventToUnshiftedModifiedAccelerator(keyEvent));
        assertEquals("Modified character with no shift doesn't match.", desiredKeyStroke, //$NON-NLS-1$
        actualKeyStroke);
        // Test the third-level match.
        //$NON-NLS-1$
        desiredKeyStroke = KeyStroke.getInstance(thirdMatch);
        actualKeyStroke = SWTKeySupport.convertAcceleratorToKeyStroke(SWTKeySupport.convertEventToModifiedAccelerator(keyEvent));
        assertEquals("Modified character with all modifiers doesn't match.", desiredKeyStroke, //$NON-NLS-1$
        actualKeyStroke);
    }

    /**
* Constructs a new instance of this test case.
*
* @param testName
*            The name of the test
*/
    public  Bug42035Test(String testName) {
        super(testName);
    }

    /**
* Tests that "Ctrl+" generates "Ctrl+", "Ctrl+" and "Ctrl+".
*
* @throws ParseException
*             If "CTRL+" cannot be processed.
*/
    public void testCtrl() throws ParseException {
        Event keyEvent = new Event();
        keyEvent.keyCode = 0x40000;
        keyEvent.character = 0x00;
        keyEvent.stateMask = SWT.NONE;
        //$NON-NLS-1$
        KeyStroke desiredKeyStroke = KeyStroke.getInstance("CTRL+");
        KeyStroke actualKeyStroke = SWTKeySupport.convertAcceleratorToKeyStroke(SWTKeySupport.convertEventToUnmodifiedAccelerator(keyEvent));
        assertEquals("Unmodified character with all modifiers doesn't match", desiredKeyStroke, //$NON-NLS-1$
        actualKeyStroke);
    }

    /**
* Tests that "Ctrl+Enter" generates "Ctrl+Enter", "Ctrl+Enter" and
* "Ctrl+Enter".
*
* @throws ParseException
*             If "CTRL+ENTER" cannot be processed.
*/
    public void testCtrlEnter() throws ParseException {
        Event keyEvent = new Event();
        keyEvent.keyCode = 0x0D;
        keyEvent.character = 0x0D;
        keyEvent.stateMask = SWT.CTRL;
        //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
        testKeyEvent(keyEvent, "CTRL+CR", "CTRL+CR", "CTRL+CR");
    }

    /**
* Tests that "Ctrl+J" generates "Ctrl+M", "Ctrl+M" and "Ctrl+M".
*
* @throws ParseException
*             If "CTRL+M" cannot be processed.
*/
    public void testCtrlM() throws ParseException {
        Event keyEvent = new Event();
        keyEvent.keyCode = 0x6D;
        keyEvent.character = 0x0D;
        keyEvent.stateMask = SWT.CTRL;
        //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
        testKeyEvent(keyEvent, "CTRL+M", "CTRL+M", "CTRL+M");
    }

    /**
* Tests that "Ctrl+Shift+2" generates "Ctrl+Shift+2", "Ctrl+@" and
* "Ctrl+Shift+@". This simulates a U.S. keyboard layout.
*
* @throws ParseException
*             If "CTRL+SHIFT+2", "CTRL+@" or "CTRL+SHIFT+@" cannot be
*             processed.
*/
    public void testCtrlShift2() throws ParseException {
        Event keyEvent = new Event();
        keyEvent.keyCode = '2';
        keyEvent.character = 0x00;
        keyEvent.stateMask = SWT.CTRL | SWT.SHIFT;
        //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
        testKeyEvent(keyEvent, "CTRL+SHIFT+2", "CTRL+@", "CTRL+SHIFT+@");
    }

    /**
* Tests that "Ctrl+Shift+7" generates "Ctrl+Shift+7", "Ctrl+/" and
* "Ctrl+Shift+/". This simulates a Swiss-German keyboard layout.
*
* @throws ParseException
*             If "CTRL+SHIFT+7", "CTRL+/" or "CTRL+SHIFT+/" cannot be
*             processed.
*/
    public void testCtrlShift7_SwissGerman() throws ParseException {
        Event keyEvent = new Event();
        keyEvent.keyCode = '7';
        keyEvent.character = '/';
        keyEvent.stateMask = SWT.CTRL | SWT.SHIFT;
        //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
        testKeyEvent(keyEvent, "CTRL+SHIFT+7", "CTRL+/", "CTRL+SHIFT+/");
    }

    /**
* Tests that "Ctrl+Shift+7" generates "Ctrl+Shift+7", "Ctrl+&" and
* "Ctrl+Shift+&". This simulates a US keyboard layout.
*
* @throws ParseException
*             If "CTRL+SHIFT+7", "CTRL+&" or "CTRL+SHIFT+&" cannot be
*             processed.
*/
    public void testCtrlShift7_US() throws ParseException {
        Event keyEvent = new Event();
        keyEvent.keyCode = '7';
        keyEvent.character = '&';
        keyEvent.stateMask = SWT.CTRL | SWT.SHIFT;
        //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
        testKeyEvent(keyEvent, "CTRL+SHIFT+7", "CTRL+&", "CTRL+SHIFT+&");
    }
}
