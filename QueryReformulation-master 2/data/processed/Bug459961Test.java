/***/
package org.eclipse.e4.ui.tests.css.swt;

import static org.junit.Assert.assertEquals;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGBA;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.junit.Test;

public class Bug459961Test extends CSSSWTTestCase {

    @Test
    public void testRegularColorConstantReference() {
        String cssString = "Label { background-color: COLOR-GREEN; }";
        Label label = createTestLabel(cssString);
        RGBA expected = Display.getDefault().getSystemColor(SWT.COLOR_GREEN).getRGBA();
        RGBA actual = label.getBackground().getRGBA();
        assertRGBAEquals(expected, actual);
    }

    @Test
    public void testTransparentColorConstantReference() {
        if (System.getProperty("org.eclipse.swt.internal.gtk.version", "").startsWith("3.")) {
            // $NON-NLS-1//NON-NLS-2//NON-NLS-3
            System.out.println("testTransparentColorConstantReference disabled due to Bug 493640");
            return;
        }
        String cssString = "Label { background-color: COLOR-TRANSPARENT; }";
        Label label = createTestLabel(cssString);
        RGBA expected = Display.getDefault().getSystemColor(SWT.COLOR_TRANSPARENT).getRGBA();
        RGBA actual = label.getBackground().getRGBA();
        assertRGBAEquals(expected, actual);
    }

    private void assertRGBAEquals(RGBA expected, RGBA actual) {
        assertEquals(expected.rgb.red, actual.rgb.red);
        assertEquals(expected.rgb.blue, actual.rgb.blue);
        assertEquals(expected.rgb.green, actual.rgb.green);
        assertEquals(expected.alpha, actual.alpha);
    }
}
