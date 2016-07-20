/***/
package org.eclipse.e4.ui.tests.css.core.parser;

import static org.junit.Assert.assertEquals;
import java.io.IOException;
import org.eclipse.e4.ui.css.core.impl.dom.DocumentCSSImpl;
import org.eclipse.e4.ui.css.core.impl.dom.ViewCSSImpl;
import org.eclipse.e4.ui.css.swt.engine.CSSSWTEngineImpl;
import org.eclipse.e4.ui.tests.css.core.util.ParserTestUtil;
import org.eclipse.e4.ui.tests.css.core.util.TestElement;
import org.eclipse.swt.widgets.Display;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.css.CSSStyleDeclaration;
import org.w3c.dom.css.CSSStyleSheet;
import org.w3c.dom.css.ViewCSS;

public class CascadeTest {

    private Display display;

    private CSSSWTEngineImpl engine;

    @Before
    public void setUp() throws Exception {
        display = Display.getDefault();
        engine = new CSSSWTEngineImpl(display);
    }

    @Test
    public void testPosition() throws Exception {
        // Two rules with the same specificity, the second rule should take
        // precedence because of its position in the stylesheet
        String css = "Button { color: blue; font-weight: bold; }\n" + "Button { color: black }\n";
        ViewCSS viewCSS = createViewCss(css);
        TestElement button = new TestElement("Button", engine);
        CSSStyleDeclaration style = viewCSS.getComputedStyle(button, null);
        assertEquals("black", style.getPropertyCSSValue("color").getCssText());
        assertEquals("bold", style.getPropertyCSSValue("font-weight").getCssText());
    }

    @Test
    public void testSpecificity() throws Exception {
        // Two rules with different specificity, the first should take
        // precedence because of its higher specificity
        String css = "Label, Button.special { color: black; }\n" + "Button { color: blue; font-weight: bold; }\n";
        ViewCSS viewCSS = createViewCss(css);
        TestElement button = new TestElement("Button", engine);
        CSSStyleDeclaration style = viewCSS.getComputedStyle(button, null);
        assertEquals("blue", style.getPropertyCSSValue("color").getCssText());
        button.setClass("special");
        style = viewCSS.getComputedStyle(button, null);
        assertEquals("black", style.getPropertyCSSValue("color").getCssText());
        assertEquals("bold", style.getPropertyCSSValue("font-weight").getCssText());
    }

    @Test
    public void testSpecificities() throws Exception {
        // Different specificities
        String css = "* { color: black; }\n" + "Button { color: blue; }\n" + "Button[BORDER] { color: gray; }\n" + "Button.special { color: green; }\n" + "Button#myid { color: red; }\n";
        ViewCSS viewCSS = createViewCss(css);
        TestElement label = new TestElement("Label", engine);
        CSSStyleDeclaration style = viewCSS.getComputedStyle(label, null);
        assertEquals("black", style.getPropertyCSSValue("color").getCssText());
        TestElement button = new TestElement("Button", engine);
        style = viewCSS.getComputedStyle(button, null);
        assertEquals("blue", style.getPropertyCSSValue("color").getCssText());
        button.setAttribute("BORDER", "true");
        style = viewCSS.getComputedStyle(button, null);
        assertEquals("gray", style.getPropertyCSSValue("color").getCssText());
        button.setClass("special");
        style = viewCSS.getComputedStyle(button, null);
        assertEquals("green", style.getPropertyCSSValue("color").getCssText());
        button.setId("myid");
        style = viewCSS.getComputedStyle(button, null);
        assertEquals("red", style.getPropertyCSSValue("color").getCssText());
    }

    private static ViewCSS createViewCss(String... css) throws IOException {
        DocumentCSSImpl docCss = new DocumentCSSImpl();
        for (String cssString : css) {
            CSSStyleSheet styleSheet = ParserTestUtil.parseCss(cssString);
            docCss.addStyleSheet(styleSheet);
        }
        return new ViewCSSImpl(docCss);
    }

    //	public void testImportantRule() throws Exception {
    //		//Several rules for the same class, if one rule has ! important
    //		//it takes precedence over all other, if more than one
    //		//last one gets precedence
    //
    //		String css = "Button{color:red ! important;}\n"
    //			+"Button{ color: blue ! important;}\n"
    //			+ "Button { color: black }\n";
    //		ViewCSS viewCSS = createViewCss(css);
    //
    //		TestElement button = new TestElement("Button", engine);
    //		CSSStyleDeclaration style = viewCSS.getComputedStyle(button, null);
    //		assertEquals("blue", style.getPropertyCSSValue("color").getCssText());
    //	}
    @Test
    public void testBug261081() throws Exception {
        // Two rules with the same specificity, the second rule should take
        // precedence because of its position in the stylesheet
        String css = "Button, Label { color: blue; font-weight: bold; }\n" + "Button { color: black }\n";
        ViewCSS viewCSS = createViewCss(css);
        TestElement button = new TestElement("Button", engine);
        CSSStyleDeclaration style = viewCSS.getComputedStyle(button, null);
        assertEquals("black", style.getPropertyCSSValue("color").getCssText());
        assertEquals("bold", style.getPropertyCSSValue("font-weight").getCssText());
    }

    @Test
    public void testBug458342_combine() throws Exception {
        // the rules of two stylesheets should be combined
        String css1 = "Button { color: blue; }";
        String css2 = "Button { font-weight: bold; }";
        ViewCSS viewCSS = createViewCss(css1, css2);
        TestElement button = new TestElement("Button", engine);
        CSSStyleDeclaration style = viewCSS.getComputedStyle(button, null);
        assertEquals("blue", style.getPropertyCSSValue("color").getCssText());
        assertEquals("bold", style.getPropertyCSSValue("font-weight").getCssText());
    }

    @Test
    public void testBug458342_override() throws Exception {
        // in case of two stylesheets, the second one should override properties
        // from the first one
        String css1 = "Button { color: blue; font-weight: bold; }";
        String css2 = "Button { color: black; }";
        ViewCSS viewCSS = createViewCss(css1, css2);
        TestElement button = new TestElement("Button", engine);
        CSSStyleDeclaration style = viewCSS.getComputedStyle(button, null);
        assertEquals("black", style.getPropertyCSSValue("color").getCssText());
        assertEquals("bold", style.getPropertyCSSValue("font-weight").getCssText());
    }
}
