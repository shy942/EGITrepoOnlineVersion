/***/
package org.eclipse.e4.ui.tests.css.swt;

import static org.junit.Assert.fail;
import java.io.IOException;
import java.io.StringReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.eclipse.e4.ui.css.core.engine.CSSEngine;
import org.eclipse.e4.ui.css.swt.engine.CSSSWTEngineImpl;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Widget;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;

public class CSSSWTTestCase {

    static final RGB RED = new RGB(255, 0, 0);

    static final RGB GREEN = new RGB(0, 255, 0);

    static final RGB BLUE = new RGB(0, 0, 255);

    @Rule
    public TestName testName = new TestName();

    protected Display display;

    protected CSSEngine engine;

    public CSSEngine createEngine(String styleSheet, Display display) {
        engine = new CSSSWTEngineImpl(display);
        engine.setErrorHandler( e -> fail(e.getMessage()));
        try {
            engine.parseStyleSheet(new StringReader(styleSheet));
        } catch (IOException e) {
            fail(e.getMessage());
        }
        return engine;
    }

    /**
* Parse and apply the style sheet, forgetting previous style sheets applied.
* This is helpful for reusing the same engine but writing independent tests.
* Styles are applied down the widget hierarchy.
* @param engine the engine
* @param widget the start of the widget hierarchy
* @param styleSheet a string style sheet
*/
    public void clearAndApply(CSSEngine engine, Widget widget, String styleSheet) {
        //Forget all previous styles
        engine.reset();
        if (styleSheet != null) {
            try {
                engine.parseStyleSheet(new StringReader(styleSheet));
            } catch (IOException e) {
                fail(e.getMessage());
            }
        }
        engine.applyStyles(widget, true, true);
    }

    @Before
    public void setUp() {
        System.out.println("[" + DateTimeFormatter.ISO_DATE_TIME.format(LocalDateTime.now()) + "] " + getClass().getName() + "#" + testName.getMethodName());
        System.out.format("  memory (free/max/total): %s/%s/%s MB\n", Runtime.getRuntime().freeMemory() / 1000000, Runtime.getRuntime().maxMemory() / 1000000, Runtime.getRuntime().totalMemory() / 1000000);
        display = Display.getDefault();
    }

    @After
    public void tearDown() {
        display = Display.getDefault();
        if (!display.isDisposed()) {
            for (Shell shell : display.getShells()) {
                shell.dispose();
            }
            display.dispose();
        }
    }

    protected Label createTestLabel(String styleSheet) {
        engine = createEngine(styleSheet, display);
        // Create widgets
        Shell shell = new Shell(display, SWT.SHELL_TRIM);
        FillLayout layout = new FillLayout();
        shell.setLayout(layout);
        Composite panel = new Composite(shell, SWT.NONE);
        panel.setLayout(new FillLayout());
        Label labelToTest = new Label(panel, SWT.NONE);
        labelToTest.setText("Some label text");
        // Apply styles
        engine.applyStyles(labelToTest, true);
        shell.pack();
        return labelToTest;
    }

    protected Link createTestLink(String styleSheet) {
        engine = createEngine(styleSheet, display);
        // Create widgets
        Shell shell = new Shell(display, SWT.SHELL_TRIM);
        FillLayout layout = new FillLayout();
        shell.setLayout(layout);
        Composite panel = new Composite(shell, SWT.NONE);
        panel.setLayout(new FillLayout());
        Link labelToTest = new Link(panel, SWT.NONE);
        labelToTest.setText("Some text <A HREF='./somewhere'>some link text</A>");
        // Apply styles
        engine.applyStyles(labelToTest, true);
        shell.pack();
        return labelToTest;
    }
}