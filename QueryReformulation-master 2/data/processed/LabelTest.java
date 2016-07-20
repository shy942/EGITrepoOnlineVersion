/***/
package org.eclipse.e4.ui.tests.css.swt;

import static org.junit.Assert.assertEquals;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Label;
import org.junit.Test;

public class LabelTest extends CSSSWTTestCase {

    @Test
    public void testColor() {
        Label labelToTest = createTestLabel("Label { background-color: #FF0000; color: #0000FF }");
        assertEquals(RED, labelToTest.getBackground().getRGB());
        assertEquals(BLUE, labelToTest.getForeground().getRGB());
    }

    @Test
    public void testFontRegular() {
        Label labelToTest = createTestLabel("Label { font: Verdana 16px }");
        assertEquals(1, labelToTest.getFont().getFontData().length);
        FontData fontData = labelToTest.getFont().getFontData()[0];
        assertEquals("Verdana", fontData.getName());
        assertEquals(16, fontData.getHeight());
        assertEquals(SWT.NORMAL, fontData.getStyle());
    }

    @Test
    public void testFontBold() {
        Label labelToTest = createTestLabel("Label { font: Arial 12px; font-weight: bold }");
        assertEquals(1, labelToTest.getFont().getFontData().length);
        FontData fontData = labelToTest.getFont().getFontData()[0];
        assertEquals("Arial", fontData.getName());
        assertEquals(12, fontData.getHeight());
        assertEquals(SWT.BOLD, fontData.getStyle());
    }

    @Test
    public void testFontItalic() {
        Label labelToTest = createTestLabel("Label { font-style: italic }");
        assertEquals(1, labelToTest.getFont().getFontData().length);
        FontData fontData = labelToTest.getFont().getFontData()[0];
        assertEquals(SWT.ITALIC, fontData.getStyle());
    }

    @Test
    public void testAlignment() {
        Label labelToTest = createTestLabel("Label { swt-alignment: right }");
        assertEquals(SWT.RIGHT, labelToTest.getAlignment());
        labelToTest = createTestLabel("Label { swt-alignment: center; }");
        assertEquals(SWT.CENTER, labelToTest.getAlignment());
        labelToTest = createTestLabel("Label { swt-alignment: left; }");
        assertEquals(SWT.LEFT, labelToTest.getAlignment());
    }

    @Test
    public void testAlignment2() {
        Label labelToTest = createTestLabel("Label { swt-alignment: trail }");
        assertEquals(SWT.TRAIL, labelToTest.getAlignment());
        labelToTest = createTestLabel("Label { swt-alignment: lead; }");
        assertEquals(SWT.LEAD, labelToTest.getAlignment());
    }
}
