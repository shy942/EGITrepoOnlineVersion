/***/
package org.eclipse.e4.ui.css.swt.helpers;

import static org.eclipse.e4.ui.css.swt.helpers.CSSSWTFontHelper.getFontData;
import static org.junit.Assert.assertEquals;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;
import org.junit.Test;

public class CSSSWTFontHelperTest extends CSSSWTHelperTestCase {

    @Test
    public void testGetFontData() {
        FontData result = getFontData(fontProperties("Times", 11, SWT.NORMAL), new FontData());
        assertEquals("Times", result.getName());
        assertEquals(11, result.getHeight());
        assertEquals(SWT.NORMAL, result.getStyle());
    }

    @Test
    public void testGetFontDataWhenMissingFamilyInCss() {
        FontData result = getFontData(fontProperties(null, 11, SWT.NORMAL), new FontData("Courier", 5, SWT.ITALIC));
        assertEquals("Courier", result.getName());
        assertEquals(11, result.getHeight());
        assertEquals(SWT.NORMAL, result.getStyle());
    }

    @Test
    public void testGetFontDataWhenMissingSizeInCss() {
        FontData result = getFontData(fontProperties("Arial", null, SWT.NORMAL), new FontData("Courier", 5, SWT.ITALIC));
        assertEquals("Arial", result.getName());
        assertEquals(5, result.getHeight());
        assertEquals(SWT.NORMAL, result.getStyle());
    }

    @Test
    public void testGetFontDataWhenMissingStyleInCss() {
        FontData result = getFontData(fontProperties("Times", 11, null), new FontData("Courier", 5, SWT.ITALIC));
        assertEquals("Times", result.getName());
        assertEquals(11, result.getHeight());
        assertEquals(SWT.ITALIC, result.getStyle());
    }

    @Test
    public void testGetFontDataWhenFontFamilyFromDefinition() {
        registerFontProviderWith("org.eclipse.jface.bannerfont", "Arial", 15, SWT.ITALIC);
        FontData result = getFontData(fontProperties(addFontDefinitionMarker("org-eclipse-jface-bannerfont"), 10, null), new FontData());
        assertEquals("Arial", result.getName());
        assertEquals(10, result.getHeight());
        assertEquals(SWT.ITALIC, result.getStyle());
    }

    @Test
    public void testGetFontDataWhenFontFamilyAndSizeFromDefinition() {
        registerFontProviderWith("org.eclipse.jface.bannerfont", "Arial", 15, SWT.ITALIC);
        FontData result = getFontData(fontProperties(addFontDefinitionMarker("org-eclipse-jface-bannerfont"), null, SWT.NORMAL), new FontData());
        assertEquals("Arial", result.getName());
        assertEquals(15, result.getHeight());
        assertEquals(SWT.ITALIC, result.getStyle());
    }

    @Test
    public void testGetFontDataWhenFontFamilySizeAndStyleFromDefinition() {
        registerFontProviderWith("org.eclipse.jface.bannerfont", "Arial", 15, SWT.ITALIC);
        FontData result = getFontData(fontProperties(addFontDefinitionMarker("org-eclipse-jface-bannerfont")), new FontData());
        assertEquals("Arial", result.getName());
        assertEquals(15, result.getHeight());
        assertEquals(SWT.ITALIC, result.getStyle());
    }
}
