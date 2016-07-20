/***/
package org.eclipse.e4.ui.tests.css.swt;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.eclipse.e4.ui.css.core.engine.CSSEngine;
import org.eclipse.e4.ui.css.swt.dom.CTabItemElement;
import org.eclipse.e4.ui.css.swt.dom.WidgetElement;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;

public class CTabItemTest extends CSSSWTTestCase {

    private Shell shell;

    @Override
    @After
    public void tearDown() {
        if (shell != null) {
            shell.dispose();
            shell = null;
        }
        super.tearDown();
    }

    private void spinEventLoop() {
        // Add some delay to allow asynchronous events to come in, but don't get trapped in an endless Display#sleep().
        for (int i = 0; i < 3; i++) {
            while (display.readAndDispatch()) {
                ;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
        }
    }

    private CTabFolder createFolder(Composite composite) {
        CTabFolder folderToTest = new CTabFolder(composite, SWT.BORDER);
        for (int i = 0; i < 4; i++) {
            final CTabItem item = new CTabItem(folderToTest, SWT.NONE);
            item.setText("Item " + i);
            Button control = new Button(folderToTest, SWT.PUSH);
            item.setControl(control);
        }
        folderToTest.setSelection(0);
        return folderToTest;
    }

    private CTabFolder createTestTabFolder() {
        return createTestTabFolder(true);
    }

    private CTabFolder createTestTabFolder(boolean open) {
        // Create widgets
        shell = new Shell(display, SWT.SHELL_TRIM);
        FillLayout layout = new FillLayout();
        shell.setLayout(layout);
        CTabFolder folderToTest = createFolder(shell);
        if (open) {
            shell.open();
        }
        return folderToTest;
    }

    private CTabFolder createTestTabFolder(String styleSheet) {
        return createTestTabFolder(styleSheet, true);
    }

    protected CTabFolder createTestTabFolder(String styleSheet, boolean open) {
        CTabFolder folder = createTestTabFolder(open);
        engine = createEngine(styleSheet, folder.getDisplay());
        // Apply styles
        engine.applyStyles(folder.getShell(), true);
        return folder;
    }

    @Test
    public void testFontRegular() {
        CTabFolder folder = createTestTabFolder("Button { font-family: Verdana; font-size: 12 }\n" + "CTabItem { font-family: Verdana; font-size: 16 }");
        spinEventLoop();
        folder.getItems();
        assertEquals(0, folder.getSelectionIndex());
        CTabItem item = folder.getItem(0);
        {
            FontData fontData = item.getFont().getFontData()[0];
            assertEquals("Verdana", fontData.getName());
            assertEquals(16, fontData.getHeight());
            assertEquals(SWT.NORMAL, fontData.getStyle());
            // verify retrieval
            assertEquals("Verdana", engine.retrieveCSSProperty(item, "font-family", null));
            assertEquals("16", engine.retrieveCSSProperty(item, "font-size", null));
            // make sure child controls are styled
            Control button = item.getControl();
            fontData = button.getFont().getFontData()[0];
            assertEquals("Verdana", fontData.getName());
            assertEquals(12, fontData.getHeight());
            assertEquals(SWT.NORMAL, fontData.getStyle());
        }
    }

    @Test
    public void testFontBold() {
        CTabFolder folder = createTestTabFolder("Button { font-weight: bold }\n" + "CTabItem { font-weight: bold }");
        spinEventLoop();
        assertEquals(0, folder.getSelectionIndex());
        CTabItem item = folder.getItem(0);
        {
            FontData fontData = item.getFont().getFontData()[0];
            assertEquals(SWT.BOLD, fontData.getStyle());
            // verify retrieval
            assertEquals("bold", engine.retrieveCSSProperty(item, "font-weight", null));
            // make sure child controls are styled
            Control button = item.getControl();
            fontData = button.getFont().getFontData()[0];
            assertEquals(SWT.BOLD, fontData.getStyle());
        }
    }

    @Test
    public void testFontItalic() {
        CTabFolder folder = createTestTabFolder("Button { font-weight: bold }\n" + "CTabItem { font-style: italic }");
        spinEventLoop();
        assertEquals(0, folder.getSelectionIndex());
        CTabItem item = folder.getItem(0);
        {
            FontData fontData = item.getFont().getFontData()[0];
            assertEquals(SWT.ITALIC, fontData.getStyle());
            // verify retrieval
            assertEquals("italic", engine.retrieveCSSProperty(item, "font-style", null));
            // make sure child controls are styled
            Control button = item.getControl();
            fontData = button.getFont().getFontData()[0];
            assertEquals(SWT.BOLD, fontData.getStyle());
        }
    }

    private void testSelectedFontBold(CTabFolder folder, int selectionIndex) {
        folder.setSelection(selectionIndex);
        spinEventLoop();
        CTabItem[] items = folder.getItems();
        for (int i = 0; i < items.length; i++) {
            FontData fontData = items[i].getFont().getFontData()[0];
            if (i == selectionIndex) {
                assertEquals(SWT.BOLD, fontData.getStyle());
            } else {
                assertEquals(SWT.NORMAL, fontData.getStyle());
            }
        }
    }

    @Test
    public void testSelectedFontBold() {
        CTabFolder folder = createTestTabFolder("CTabItem:selected { font-weight: bold }");
        spinEventLoop();
        for (int i = 0; i < folder.getItemCount(); i++) {
            testSelectedFontBold(folder, i);
        }
    }

    @Test
    public void testSelectedFontMerged() {
        CTabFolder folder = createTestTabFolder("CTabItem { font-weight: normal; font-style: italic }\n" + "CTabItem:selected { font-weight: bold }");
        spinEventLoop();
        for (int i = 0; i < folder.getItemCount(); i++) {
            CTabItem item = folder.getItem(i);
            FontData fd = item.getFont().getFontData()[0];
            if (item == folder.getSelection()) {
                assertEquals(SWT.BOLD | SWT.ITALIC, fd.getStyle());
            } else {
                assertEquals(SWT.ITALIC, fd.getStyle());
            }
        }
    }

    @Test
    public void testSelectedFontMerged2() {
        CTabFolder folder = createTestTabFolder("CTabItem { font-style: italic }\n" + "CTabItem:selected { font-weight: bold }");
        spinEventLoop();
        for (int i = 0; i < folder.getItemCount(); i++) {
            CTabItem item = folder.getItem(i);
            FontData fd = item.getFont().getFontData()[0];
            if (item == folder.getSelection()) {
                assertEquals(SWT.BOLD | SWT.ITALIC, fd.getStyle());
            } else {
                assertEquals(SWT.ITALIC, fd.getStyle());
            }
        }
    }

    @Test
    public void testSelectedFontMerged3() {
        CTabFolder folder = createTestTabFolder("CTabItem { font-weight: bold }\n" + "CTabItem:selected { font-style: italic; font-weight: normal }");
        spinEventLoop();
        for (int i = 0; i < folder.getItemCount(); i++) {
            CTabItem item = folder.getItem(i);
            FontData fd = item.getFont().getFontData()[0];
            if (item == folder.getSelection()) {
                assertEquals(SWT.ITALIC, fd.getStyle());
            } else {
                assertEquals(SWT.BOLD, fd.getStyle());
            }
        }
    }

    private void testShowClose(boolean showClose) {
        CTabFolder folder = createTestTabFolder("CTabItem { show-close: " + Boolean.toString(showClose) + " }");
        CTabItem[] items = folder.getItems();
        for (CTabItem item : items) {
            assertEquals(showClose, item.getShowClose());
            assertEquals(Boolean.toString(showClose), engine.retrieveCSSProperty(item, "show-close", null));
        }
    }

    @Test
    public void testShowCloseFalse() {
        testShowClose(false);
    }

    @Test
    public void testShowCloseTrue() {
        testShowClose(true);
    }

    @Test
    public void testShowClose() {
        CTabFolder folder = createTestTabFolder("CTabItem { show-close: true }");
        for (int i = 0; i < folder.getItemCount(); i++) {
            assertEquals(true, folder.getItem(i).getShowClose());
        }
        engine = createEngine("CTabItem { show-close: false }", folder.getDisplay());
        engine.applyStyles(folder.getShell(), true);
        for (int i = 0; i < folder.getItemCount(); i++) {
            assertEquals(false, folder.getItem(i).getShowClose());
        }
    }

    @Test
    public void testShowClose2() {
        CTabFolder folder = createTestTabFolder();
        CTabFolder folder2 = createFolder(folder.getShell());
        engine = createEngine("CTabItem { show-close: true }", folder.getDisplay());
        engine.applyStyles(folder.getShell(), true);
        for (int i = 0; i < folder.getItemCount(); i++) {
            assertEquals(true, folder.getItem(i).getShowClose());
        }
        for (int i = 0; i < folder2.getItemCount(); i++) {
            assertEquals(true, folder2.getItem(i).getShowClose());
        }
        engine = createEngine("CTabItem { show-close: false }", folder.getDisplay());
        engine.applyStyles(folder.getShell(), true);
        for (int i = 0; i < folder.getItemCount(); i++) {
            assertEquals(false, folder.getItem(i).getShowClose());
        }
        for (int i = 0; i < folder.getItemCount(); i++) {
            assertEquals(false, folder2.getItem(i).getShowClose());
        }
    }

    private void testSelectedShowClose(CTabFolder folder, int index) {
        CTabItem[] items = folder.getItems();
        folder.setSelection(index);
        spinEventLoop();
        for (int i = 0; i < items.length; i++) {
            if (i == index) {
                assertEquals(true, items[i].getShowClose());
                assertEquals("true", engine.retrieveCSSProperty(items[i], "show-close", null));
            } else {
                assertEquals(false, items[i].getShowClose());
                assertEquals("false", engine.retrieveCSSProperty(items[i], "show-close", null));
            }
        }
    }

    @Test
    public void testSelectedShowClose() {
        CTabFolder folder = createTestTabFolder("CTabItem:selected { show-close: true }");
        for (int i = 0; i < folder.getItemCount(); i++) {
            testSelectedShowClose(folder, i);
        }
        engine = createEngine("CTabItem:selected { show-close: false }", folder.getDisplay());
        engine.applyStyles(folder.getShell(), true);
        for (int i = 0; i < folder.getItemCount(); i++) {
            assertFalse(folder.getItem(i).getShowClose());
        }
    }

    @Test
    public void testSelectedShowClose2() {
        CTabFolder folder = createTestTabFolder("CTabItem { show-close: false }\n" + "CTabItem:selected { show-close: true }");
        for (int i = 0; i < folder.getItemCount(); i++) {
            testSelectedShowClose(folder, i);
        }
    }

    @Ignore("test was commented before bug 443094")
    @Test
    public void testClassSelectedShowClose() {
        CTabFolder folder = createTestTabFolder();
        WidgetElement.setCSSClass(folder, "editorStack");
        CSSEngine engine = createEngine("CTabFolder.editorStack CTabItem { show-close: true }", folder.getDisplay());
        engine.applyStyles(folder.getShell(), true);
        for (int i = 0; i < folder.getItemCount(); i++) {
            assertTrue(folder.getItem(i).getShowClose());
        }
    }

    @Ignore("test was commented before bug 443094")
    @Test
    public void testFontsEditorStackClass() {
        CTabFolder folder = createTestTabFolder(false);
        CTabFolder folder2 = createFolder(folder.getShell());
        WidgetElement.setCSSClass(folder2, "editorStack");
        engine = createEngine("CTabItem { font-size: 10 }" + "CTabItem:selected { font-size: 14; font-weight: bold }" + "CTabFolder.editorStack CTabItem { font-size: 11; }" + "CTabFolder.editorStack CTabItem:selected { font-size: 13; font-style: italic }", folder.getDisplay());
        engine.applyStyles(folder.getShell(), true);
        folder.getShell().open();
        folder.setSelection(0);
        spinEventLoop();
        assertNotNull(folder.getSelection());
        assertNull(folder2.getSelection());
        for (int i = 0; i < folder.getItemCount(); i++) {
            CTabItem item = folder.getItem(i);
            FontData data = item.getFont().getFontData()[0];
            if (item == folder.getSelection()) {
                assertEquals(14, data.getHeight());
                assertEquals(SWT.BOLD, data.getStyle());
            } else {
                assertEquals(10, data.getHeight());
                assertEquals(SWT.NORMAL, data.getStyle());
            }
        }
        for (int i = 0; i < folder2.getItemCount(); i++) {
            CTabItem item = folder2.getItem(i);
            FontData data = item.getFont().getFontData()[0];
            assertEquals(11, data.getHeight());
            assertEquals(SWT.NORMAL, data.getStyle());
        }
        folder2.setSelection(0);
        spinEventLoop();
        for (int i = 0; i < folder2.getItemCount(); i++) {
            CTabItem item = folder2.getItem(i);
            FontData data = item.getFont().getFontData()[0];
            if (item == folder2.getSelection()) {
                assertEquals(13, data.getHeight());
                assertEquals(SWT.ITALIC | SWT.BOLD, data.getStyle());
            } else {
                assertEquals(11, data.getHeight());
                assertEquals(SWT.NORMAL, data.getStyle());
            }
        }
    }

    @Ignore("test was commented before bug 443094")
    @Test
    public void testFontsEditorStackClass2() {
        CTabFolder folder = createTestTabFolder(false);
        CTabFolder folder2 = createFolder(folder.getShell());
        WidgetElement.setCSSClass(folder2, "editorStack");
        engine = createEngine("CTabItem { font-size: 10 }" + "CTabItem:selected { font-size: 14; font-weight: bold }" + "CTabFolder.editorStack CTabItem { font-size: 11; }" + "CTabFolder.editorStack CTabItem:selected { font-size: 13; font-weight: normal; font-style: italic }", folder.getDisplay());
        engine.applyStyles(folder.getShell(), true);
        folder.getShell().open();
        folder.setSelection(0);
        spinEventLoop();
        assertNotNull(folder.getSelection());
        assertNull(folder2.getSelection());
        for (int i = 0; i < folder.getItemCount(); i++) {
            CTabItem item = folder.getItem(i);
            FontData data = item.getFont().getFontData()[0];
            if (item == folder.getSelection()) {
                assertEquals(14, data.getHeight());
                assertEquals(SWT.BOLD, data.getStyle());
            } else {
                assertEquals(10, data.getHeight());
                assertEquals(SWT.NORMAL, data.getStyle());
            }
        }
        for (int i = 0; i < folder2.getItemCount(); i++) {
            CTabItem item = folder2.getItem(i);
            FontData data = item.getFont().getFontData()[0];
            assertEquals(11, data.getHeight());
            assertEquals(SWT.NORMAL, data.getStyle());
        }
        folder2.setSelection(0);
        spinEventLoop();
        for (int i = 0; i < folder2.getItemCount(); i++) {
            CTabItem item = folder2.getItem(i);
            FontData data = item.getFont().getFontData()[0];
            if (item == folder2.getSelection()) {
                assertEquals(13, data.getHeight());
                assertEquals(SWT.ITALIC, data.getStyle());
            } else {
                assertEquals(11, data.getHeight());
                assertEquals(SWT.NORMAL, data.getStyle());
            }
        }
    }

    @Ignore("test was commented before bug 443094")
    @Test
    public void testShowCloseEditorStack() {
        CTabFolder folder = createTestTabFolder(false);
        CTabFolder folder2 = createFolder(folder.getShell());
        WidgetElement.setCSSClass(folder2, "editorStack");
        engine = createEngine("CTabItem { show-close: false }" + "CTabItem:selected { show-close: true }" + "CTabFolder.editorStack CTabItem { show-close: true }", folder.getDisplay());
        engine.applyStyles(folder.getShell(), true);
        folder.getShell().open();
        folder.setSelection(0);
        spinEventLoop();
        assertNotNull(folder.getSelection());
        assertNull(folder2.getSelection());
        for (int i = 0; i < folder.getItemCount(); i++) {
            CTabItem item = folder.getItem(i);
            if (item == folder.getSelection()) {
                assertTrue(item.getShowClose());
            } else {
                assertFalse(item.getShowClose());
            }
        }
        for (int i = 0; i < folder2.getItemCount(); i++) {
            CTabItem item = folder2.getItem(i);
            assertTrue(item.getShowClose());
        }
        folder2.setSelection(0);
        spinEventLoop();
        for (int i = 0; i < folder2.getItemCount(); i++) {
            CTabItem item = folder2.getItem(i);
            assertTrue(item.getShowClose());
        }
    }

    @Ignore("test was commented before bug 443094")
    @Test
    public void testShowCloseViewStack() {
        CTabFolder folder = createTestTabFolder(false);
        CTabFolder folder2 = createFolder(folder.getShell());
        WidgetElement.setCSSClass(folder2, "viewStack");
        engine = createEngine("CTabItem { show-close: false }" + "CTabItem:selected { show-close: true }" + "CTabFolder.viewStack CTabItem { show-close: false }" + "CTabFolder.viewStack CTabItem.selected { show-close: true }", folder.getDisplay());
        engine.applyStyles(folder.getShell(), true);
        folder.getShell().open();
        folder.setSelection(0);
        spinEventLoop();
        assertNotNull(folder.getSelection());
        assertNull(folder2.getSelection());
        for (int i = 0; i < folder.getItemCount(); i++) {
            CTabItem item = folder.getItem(i);
            if (item == folder.getSelection()) {
                assertTrue(item.getShowClose());
            } else {
                assertFalse(item.getShowClose());
            }
        }
        for (int i = 0; i < folder2.getItemCount(); i++) {
            CTabItem item = folder2.getItem(i);
            assertFalse(item.getShowClose());
        }
        folder2.setSelection(0);
        spinEventLoop();
        for (int i = 0; i < folder2.getItemCount(); i++) {
            CTabItem item = folder.getItem(i);
            if (item == folder.getSelection()) {
                assertTrue(item.getShowClose());
            } else {
                assertFalse(item.getShowClose());
            }
        }
    }

    @Test
    public void testBackground() {
        CTabFolder folder = createTestTabFolder("CTabItem { background-color: #0000ff }", false);
        assertEquals(new RGB(0, 0, 255), folder.getBackground().getRGB());
        for (int i = 0; i < folder.getItemCount(); i++) {
            assertEquals("#0000ff", engine.retrieveCSSProperty(folder.getItem(i), "background-color", null));
        }
    }

    @Test
    public void testBackground2() {
        CTabFolder folder = createTestTabFolder(false);
        Color preStyledSelectionBackground = folder.getSelectionBackground();
        RGB rgb = new RGB(0, 0, 255);
        String colour = "#0000ff";
        // we want to make sure we pick a unique colour so that we actually test that the selection's colour has not changed
        if (rgb.equals(preStyledSelectionBackground.getRGB())) {
            rgb = new RGB(0, 255, 0);
            colour = "#00ff00";
        }
        CSSEngine engine = createEngine("CTabItem { background-color: " + colour + " }", folder.getDisplay());
        engine.applyStyles(folder, true);
        assertEquals(rgb, folder.getBackground().getRGB());
        for (int i = 0; i < folder.getItemCount(); i++) {
            assertEquals(colour, engine.retrieveCSSProperty(folder.getItem(i), "background-color", null));
        }
        assertEquals(preStyledSelectionBackground.getRGB(), folder.getSelectionBackground().getRGB());
    }

    @Test
    public void testSelectionBackground() {
        CTabFolder folder = createTestTabFolder("CTabItem:selected { background-color: #00ff00 }", false);
        assertEquals(new RGB(0, 255, 0), folder.getSelectionBackground().getRGB());
        for (int i = 0; i < folder.getItemCount(); i++) {
            assertEquals("#00ff00", engine.retrieveCSSProperty(folder.getItem(i), "background-color", "selected"));
        }
    }

    @Test
    public void testForeground() {
        CTabFolder folder = createTestTabFolder("CTabItem { color: #0000ff }", false);
        assertEquals(new RGB(0, 0, 255), folder.getForeground().getRGB());
        for (int i = 0; i < folder.getItemCount(); i++) {
            assertEquals("#0000ff", engine.retrieveCSSProperty(folder.getItem(i), "color", null));
        }
    }

    @Test
    public void testForeground2() {
        CTabFolder folder = createTestTabFolder(false);
        Color preStyledSelectionForeground = folder.getSelectionForeground();
        RGB rgb = new RGB(0, 0, 255);
        String colour = "#0000ff";
        // that the selection's colour has not changed
        if (rgb.equals(preStyledSelectionForeground.getRGB())) {
            rgb = new RGB(0, 255, 0);
            colour = "#00ff00";
        }
        CSSEngine engine = createEngine("CTabItem { color: " + colour + " }", folder.getDisplay());
        engine.applyStyles(folder, true);
        assertEquals(rgb, folder.getForeground().getRGB());
        for (int i = 0; i < folder.getItemCount(); i++) {
            assertEquals(colour, engine.retrieveCSSProperty(folder.getItem(i), "color", null));
        }
        assertEquals(preStyledSelectionForeground.getRGB(), folder.getSelectionForeground().getRGB());
    }

    @Test
    public void testSelectionForeground() {
        CTabFolder folder = createTestTabFolder("CTabItem:selected { color: #00ff00 }", false);
        assertEquals(new RGB(0, 255, 0), folder.getSelectionForeground().getRGB());
        for (int i = 0; i < folder.getItemCount(); i++) {
            assertEquals("#00ff00", engine.retrieveCSSProperty(folder.getItem(i), "color", "selected"));
        }
    }

    @Test
    public void testParent() {
        CTabFolder folder = createTestTabFolder("CTabItem:selected { color: #00ff00 }", false);
        for (int i = 0; i < folder.getItemCount(); i++) {
            CTabItemElement element = (CTabItemElement) engine.getElement(folder.getItem(i));
            assertNotNull(element.getParentNode());
        }
    }
}
