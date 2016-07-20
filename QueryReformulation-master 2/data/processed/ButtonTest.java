/***/
package org.eclipse.e4.ui.tests.css.swt;

import static org.junit.Assert.assertEquals;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.junit.Ignore;
import org.junit.Test;

public class ButtonTest extends CSSSWTTestCase {

    static final RGB RED = new RGB(255, 0, 0);

    static final RGB GREEN = new RGB(0, 255, 0);

    static final RGB BLUE = new RGB(0, 0, 255);

    protected Button createTestButton(String styleSheet) {
        engine = createEngine(styleSheet, display);
        // Create widgets
        Shell shell = new Shell(display, SWT.SHELL_TRIM);
        FillLayout layout = new FillLayout();
        shell.setLayout(layout);
        Composite panel = new Composite(shell, SWT.NONE);
        panel.setLayout(new FillLayout());
        Button buttonToTest = new Button(panel, SWT.CHECK);
        buttonToTest.setText("Some button text");
        // Apply styles
        engine.applyStyles(shell, true);
        shell.pack();
        return buttonToTest;
    }

    protected Button createTestArrowButton(String styleSheet) {
        engine = createEngine(styleSheet, display);
        // Create widgets
        Shell shell = new Shell(display, SWT.SHELL_TRIM);
        FillLayout layout = new FillLayout();
        shell.setLayout(layout);
        Composite panel = new Composite(shell, SWT.NONE);
        panel.setLayout(new FillLayout());
        Button buttonToTest = new Button(panel, SWT.ARROW);
        // Apply styles
        engine.applyStyles(shell, true);
        shell.pack();
        return buttonToTest;
    }

    @Test
    public void testColor() {
        Button buttonToTest = createTestButton("Button { background-color: #FF0000; color: #0000FF }");
        assertEquals(RED, buttonToTest.getBackground().getRGB());
        assertEquals(BLUE, buttonToTest.getForeground().getRGB());
    }

    @Test
    public void testFontRegular() {
        Button buttonToTest = createTestButton("Button { font: Verdana 16px }");
        assertEquals(1, buttonToTest.getFont().getFontData().length);
        FontData fontData = buttonToTest.getFont().getFontData()[0];
        assertEquals("Verdana", fontData.getName());
        assertEquals(16, fontData.getHeight());
        assertEquals(SWT.NORMAL, fontData.getStyle());
    }

    @Test
    public void testFontBold() {
        Button buttonToTest = createTestButton("Button { font: Arial 12px; font-weight: bold }");
        assertEquals(1, buttonToTest.getFont().getFontData().length);
        FontData fontData = buttonToTest.getFont().getFontData()[0];
        assertEquals("Arial", fontData.getName());
        assertEquals(12, fontData.getHeight());
        assertEquals(SWT.BOLD, fontData.getStyle());
    }

    @Test
    public void testFontItalic() {
        Button buttonToTest = createTestButton("Button { font-style: italic }");
        assertEquals(1, buttonToTest.getFont().getFontData().length);
        FontData fontData = buttonToTest.getFont().getFontData()[0];
        assertEquals(SWT.ITALIC, fontData.getStyle());
    }

    @Ignore
    public void testSelectedPseudo() {
        Button buttonToTest = createTestButton("Button { color: #FF0000; }\n" + "Button:selected { color: #0000FF; }");
        assertEquals(RED, buttonToTest.getForeground().getRGB());
        buttonToTest.setSelection(true);
        engine.applyStyles(buttonToTest.getShell(), true);
        assertEquals(BLUE, buttonToTest.getForeground().getRGB());
    }

    @Test
    public void testAlignment() {
        Button buttonToTest = createTestButton("Button { alignment: right; }");
        assertEquals(SWT.RIGHT, buttonToTest.getAlignment());
        buttonToTest = createTestButton("Button { alignment: left; }");
        assertEquals(SWT.LEFT, buttonToTest.getAlignment());
        buttonToTest = createTestButton("Button { alignment: center; }");
        assertEquals(SWT.CENTER, buttonToTest.getAlignment());
    }

    @Test
    public void testAlignment2() {
        Button buttonToTest = createTestButton("Button { alignment: trail; }");
        assertEquals(SWT.TRAIL, buttonToTest.getAlignment());
        buttonToTest = createTestButton("Button { alignment: lead; }");
        assertEquals(SWT.LEAD, buttonToTest.getAlignment());
    }

    @Test
    public void testArrowAlignment() {
        Button buttonToTest = createTestArrowButton("Button { alignment: up; }");
        assertEquals(SWT.UP, buttonToTest.getAlignment());
        buttonToTest = createTestArrowButton("Button { alignment: down; }");
        assertEquals(SWT.DOWN, buttonToTest.getAlignment());
        buttonToTest = createTestArrowButton("Button { alignment: left; }");
        assertEquals(SWT.LEFT, buttonToTest.getAlignment());
        buttonToTest = createTestArrowButton("Button { alignment: right; }");
        assertEquals(SWT.RIGHT, buttonToTest.getAlignment());
    }
}
