/***/
package org.eclipse.e4.ui.tests.css.swt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotSame;
import org.eclipse.e4.ui.css.swt.dom.CTabFolderElement;
import org.eclipse.e4.ui.css.swt.properties.custom.CSSPropertyMruVisibleSWTHandler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.junit.Ignore;
import org.junit.Test;

public class CTabFolderTest extends CSSSWTTestCase {

    static final RGB RED = new RGB(255, 0, 0);

    static final RGB GREEN = new RGB(0, 255, 0);

    static final RGB BLUE = new RGB(0, 0, 255);

    static final RGB WHITE = new RGB(255, 255, 255);

    protected CTabFolder createTestCTabFolder(String styleSheet) {
        engine = createEngine(styleSheet, display);
        // Create widgets
        Shell shell = new Shell(display, SWT.SHELL_TRIM);
        FillLayout layout = new FillLayout();
        shell.setLayout(layout);
        Composite panel = new Composite(shell, SWT.NONE);
        panel.setLayout(new FillLayout());
        CTabFolder folderToTest = new CTabFolder(panel, SWT.NONE);
        CTabItem tab1 = new CTabItem(folderToTest, SWT.NONE);
        tab1.setText("A TAB ITEM");
        engine.applyStyles(shell, true);
        shell.pack();
        return folderToTest;
    }

    protected ToolBar[] createTestToolBars(String styleSheet) {
        engine = createEngine(styleSheet, display);
        // Create widgets
        Shell shell = new Shell(display, SWT.SHELL_TRIM);
        FillLayout layout = new FillLayout();
        shell.setLayout(layout);
        Composite panel = new Composite(shell, SWT.NONE);
        panel.setLayout(new FillLayout());
        CTabFolder folderA = new CTabFolder(panel, SWT.NONE);
        CTabItem tabA = new CTabItem(folderA, SWT.NONE);
        tabA.setText("FolderA TAB ITEM");
        ToolBar toolbarA = new ToolBar(folderA, SWT.FLAT | SWT.HORIZONTAL);
        folderA.setTopRight(toolbarA);
        CTabFolder folderB = new CTabFolder(panel, SWT.NONE);
        CTabItem tabB = new CTabItem(folderB, SWT.NONE);
        tabB.setText("FolderB TAB ITEM");
        ToolBar toolbarB = new ToolBar(folderB, SWT.FLAT | SWT.HORIZONTAL);
        folderB.setTopRight(toolbarB);
        //One toolbar on its own, no CTabFolder parent
        ToolBar toolbarC = new ToolBar(panel, SWT.FLAT | SWT.HORIZONTAL);
        engine.applyStyles(shell, true);
        return new ToolBar[] { toolbarA, toolbarB, toolbarC };
    }

    protected Shell createShell(String styleSheet) {
        Display display = Display.getDefault();
        engine = createEngine(styleSheet, display);
        // Create widgets
        Shell shell = new Shell(display, SWT.NONE);
        engine.applyStyles(shell, true);
        shell.pack();
        return shell;
    }

    @Test
    public void testBackgroundColor() {
        CTabFolder folderToTest = createTestCTabFolder("CTabFolder { background-color: #0000FF }");
        assertEquals(BLUE, folderToTest.getBackground().getRGB());
    }

    @Test
    public void testTextColor() {
        CTabFolder folderToTest = createTestCTabFolder("CTabFolder { color: #0000FF }");
        assertEquals(BLUE, folderToTest.getForeground().getRGB());
    }

    //See GradientTest for testing background gradient
    @Test
    public void testFontRegular() {
        CTabFolder folderToTest = createTestCTabFolder("CTabFolder { font: Verdana 16px }");
        assertEquals(1, folderToTest.getFont().getFontData().length);
        FontData fontData = folderToTest.getFont().getFontData()[0];
        assertEquals("Verdana", fontData.getName());
        assertEquals(16, fontData.getHeight());
        assertEquals(SWT.NORMAL, fontData.getStyle());
    }

    @Test
    public void testFontBold() {
        CTabFolder folderToTest = createTestCTabFolder("CTabFolder { font: Arial 12px; font-weight: bold }");
        assertEquals(1, folderToTest.getFont().getFontData().length);
        FontData fontData = folderToTest.getFont().getFontData()[0];
        assertEquals("Arial", fontData.getName());
        assertEquals(12, fontData.getHeight());
        assertEquals(SWT.BOLD, fontData.getStyle());
    }

    @Test
    public void testFontItalic() {
        CTabFolder folderToTest = createTestCTabFolder("CTabFolder { font: Arial 12px; font-style: italic }");
        assertEquals(1, folderToTest.getFont().getFontData().length);
        FontData fontData = folderToTest.getFont().getFontData()[0];
        assertEquals("Arial", fontData.getName());
        assertEquals(12, fontData.getHeight());
        assertEquals(SWT.ITALIC, fontData.getStyle());
    }

    @Ignore("this test was commented before bug 443094")
    @Test
    public void testBorderVisible() {
        CTabFolder folderToTest = createTestCTabFolder("CTabFolder { border-visible: true}");
        assertEquals(true, folderToTest.getBorderVisible());
        assertEquals("true", engine.retrieveCSSProperty(folderToTest, "border-visible", null));
        folderToTest.getShell().close();
        folderToTest = createTestCTabFolder("CTabFolder { border-visible: false}");
        assertEquals(false, folderToTest.getBorderVisible());
        assertEquals("false", engine.retrieveCSSProperty(folderToTest, "border-visible", null));
    }

    @Test
    public void testSimple() {
        CTabFolder folderToTest = createTestCTabFolder("CTabFolder { simple: true}");
        assertEquals(true, folderToTest.getSimple());
        assertEquals("true", engine.retrieveCSSProperty(folderToTest, "simple", null));
        folderToTest.getShell().close();
        folderToTest = createTestCTabFolder("CTabFolder { simple: false}");
        assertEquals(false, folderToTest.getSimple());
        assertEquals("false", engine.retrieveCSSProperty(folderToTest, "simple", null));
    }

    @Test
    public void testMaximizeVisible() {
        CTabFolder folderToTest = createTestCTabFolder("CTabFolder { maximize-visible: true}");
        assertEquals(true, folderToTest.getMaximizeVisible());
        assertEquals("true", engine.retrieveCSSProperty(folderToTest, "maximize-visible", null));
        folderToTest.getShell().close();
        folderToTest = createTestCTabFolder("CTabFolder { maximize-visible: false}");
        assertEquals(false, folderToTest.getMaximizeVisible());
        assertEquals("false", engine.retrieveCSSProperty(folderToTest, "maximize-visible", null));
    }

    @Test
    public void testMinimizeVisible() {
        CTabFolder folderToTest = createTestCTabFolder("CTabFolder { minimize-visible: true}");
        assertEquals(true, folderToTest.getMinimizeVisible());
        assertEquals("true", engine.retrieveCSSProperty(folderToTest, "minimize-visible", null));
        folderToTest.getShell().close();
        folderToTest = createTestCTabFolder("CTabFolder { minimize-visible: false}");
        assertEquals(false, folderToTest.getMinimizeVisible());
        assertEquals("false", engine.retrieveCSSProperty(folderToTest, "minimize-visible", null));
    }

    @Test
    public void testMRUVisible() {
        final boolean mruControlledByCSSDefault = CSSPropertyMruVisibleSWTHandler.isMRUControlledByCSS();
        try {
            CSSPropertyMruVisibleSWTHandler.setMRUControlledByCSS(true);
            CTabFolder folderToTest = createTestCTabFolder("CTabFolder { mru-visible: true}");
            assertEquals(true, folderToTest.getMRUVisible());
            assertEquals("true", engine.retrieveCSSProperty(folderToTest, "mru-visible", null));
            folderToTest.getShell().close();
            folderToTest = createTestCTabFolder("CTabFolder { mru-visible: false}");
            assertEquals("false", engine.retrieveCSSProperty(folderToTest, "mru-visible", null));
            assertEquals(false, folderToTest.getMRUVisible());
        } finally {
            CSSPropertyMruVisibleSWTHandler.setMRUControlledByCSS(mruControlledByCSSDefault);
        }
    }

    @Test
    public void testMRUVisibleCSSControlOff() {
        final boolean mruControlledByCSSDefault = CSSPropertyMruVisibleSWTHandler.isMRUControlledByCSS();
        try {
            CSSPropertyMruVisibleSWTHandler.setMRUControlledByCSS(false);
            CTabFolder folderToTest = createTestCTabFolder("CTabFolder { mru-visible: true}");
            assertEquals(false, folderToTest.getMRUVisible());
            assertEquals("false", engine.retrieveCSSProperty(folderToTest, "mru-visible", null));
            folderToTest.getShell().close();
            folderToTest = createTestCTabFolder("CTabFolder { mru-visible: false}");
            assertEquals("false", engine.retrieveCSSProperty(folderToTest, "mru-visible", null));
            assertEquals(false, folderToTest.getMRUVisible());
        } finally {
            CSSPropertyMruVisibleSWTHandler.setMRUControlledByCSS(mruControlledByCSSDefault);
        }
    }

    @Test
    public void testMaximized() {
        CTabFolder folderToTest = createTestCTabFolder("CTabFolder { maximized: true}");
        assertEquals(true, folderToTest.getMaximized());
        assertEquals("true", engine.retrieveCSSProperty(folderToTest, "maximized", null));
        folderToTest = createTestCTabFolder("CTabFolder { maximized: false}");
        assertEquals(false, folderToTest.getMaximized());
        assertEquals("false", engine.retrieveCSSProperty(folderToTest, "maximized", null));
    }

    @Test
    public void testMinimized() {
        CTabFolder folderToTest = createTestCTabFolder("CTabFolder { minimized: true}");
        assertEquals(true, folderToTest.getMinimized());
        assertEquals("true", engine.retrieveCSSProperty(folderToTest, "minimized", null));
        folderToTest = createTestCTabFolder("CTabFolder { minimized: false}");
        assertEquals(false, folderToTest.getMinimized());
        assertEquals("false", engine.retrieveCSSProperty(folderToTest, "minimized", null));
    }

    @Test
    public void testTabHeight() {
        CTabFolder folderToTest = createTestCTabFolder("CTabFolder { tab-height: 30px }");
        assertEquals(30, folderToTest.getTabHeight());
        folderToTest = createTestCTabFolder("CTabFolder { tab-height: 40px }");
        assertEquals(40, folderToTest.getTabHeight());
        //negative test to ensure we don't try to interpret a list
        folderToTest = createTestCTabFolder("CTabFolder { tab-height: 40px 50px }");
        assertNotSame(40, folderToTest.getTabHeight());
        assertNotSame(50, folderToTest.getTabHeight());
        //negative test for ambiguous unit value
        folderToTest = createTestCTabFolder("CTabFolder { tab-height: 40 }");
        assertNotSame(40, folderToTest.getTabHeight());
    }

    @Test
    public void testSingle() {
        CTabFolder folderToTest = createTestCTabFolder("CTabFolder { single: true}");
        assertEquals(true, folderToTest.getSingle());
        assertEquals("true", engine.retrieveCSSProperty(folderToTest, "single", null));
        folderToTest = createTestCTabFolder("CTabFolder { single: false}");
        assertEquals(false, folderToTest.getSingle());
        assertEquals("false", engine.retrieveCSSProperty(folderToTest, "single", null));
    }

    @Test
    public void testUnselectedCloseVisible() {
        CTabFolder folderToTest = createTestCTabFolder("CTabFolder { unselected-close-visible: true}");
        assertEquals(true, folderToTest.getUnselectedCloseVisible());
        assertEquals("true", engine.retrieveCSSProperty(folderToTest, "unselected-close-visible", null));
        folderToTest = createTestCTabFolder("CTabFolder { unselected-close-visible: false}");
        assertEquals(false, folderToTest.getUnselectedCloseVisible());
        assertEquals("false", engine.retrieveCSSProperty(folderToTest, "unselected-close-visible", null));
    }

    @Test
    public void testUnselectedImageVisible() {
        CTabFolder folderToTest = createTestCTabFolder("CTabFolder { unselected-image-visible: true}");
        assertEquals(true, folderToTest.getUnselectedImageVisible());
        assertEquals("true", engine.retrieveCSSProperty(folderToTest, "unselected-image-visible", null));
        folderToTest = createTestCTabFolder("CTabFolder { unselected-image-visible: false}");
        assertEquals(false, folderToTest.getUnselectedImageVisible());
        assertEquals("false", engine.retrieveCSSProperty(folderToTest, "unselected-image-visible", null));
    }

    @Test
    public void testRetrievePropertyNull() {
        Shell shell = createShell("Shell {color:red}");
        assertEquals(null, engine.retrieveCSSProperty(shell, "border-visible", null));
        assertEquals(null, engine.retrieveCSSProperty(shell, "maximized", null));
        assertEquals(null, engine.retrieveCSSProperty(shell, "maximize-visible", null));
        assertEquals(null, engine.retrieveCSSProperty(shell, "minimize-visible", null));
        assertEquals(null, engine.retrieveCSSProperty(shell, "mru-visible", null));
        assertEquals(null, engine.retrieveCSSProperty(shell, "show-close", null));
        assertEquals(null, engine.retrieveCSSProperty(shell, "simple", null));
        assertEquals(null, engine.retrieveCSSProperty(shell, "single", null));
        assertEquals(null, engine.retrieveCSSProperty(shell, "unselected-close-visible", null));
        assertEquals(null, engine.retrieveCSSProperty(shell, "unselected-image-visible", null));
    }

    @Test
    public void testTopRightAsDescendentChild() {
        ToolBar[] toolBars = createTestToolBars("CTabFolder.special ToolBar { background: #FF0000}\n" + "CTabFolder ToolBar { background: #00FF00}\n" + "CTabFolder.extraordinary ToolBar { background: #FFFFFF}\n" + "ToolBar { background: #0000FF}");
        ToolBar barA = toolBars[0];
        ToolBar barB = toolBars[1];
        ToolBar barC = toolBars[2];
        CTabFolderElement.setCSSClass(barA.getParent(), "special");
        engine.applyStyles(barA.getShell(), true);
        assertEquals(RED, barA.getBackground().getRGB());
        assertEquals(GREEN, barB.getBackground().getRGB());
        assertEquals(BLUE, barC.getBackground().getRGB());
        CTabFolderElement.setCSSClass(barA.getParent(), "extraordinary");
        engine.applyStyles(barA.getShell(), true);
        assertEquals(WHITE, barA.getBackground().getRGB());
    }
}
