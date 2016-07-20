/***/
package org.eclipse.e4.ui.tests.css.core.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.eclipse.e4.ui.css.core.engine.CSSEngine;
import org.eclipse.e4.ui.css.core.impl.dom.Measure;
import org.eclipse.e4.ui.tests.css.core.util.ParserTestUtil;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.css.CSSPrimitiveValue;
import org.w3c.dom.css.CSSValue;
import org.w3c.dom.css.CSSValueList;

public class ValueTest {

    private CSSEngine engine;

    @Before
    public void setUp() {
        engine = ParserTestUtil.createEngine();
    }

    @Test
    public void testFloat() throws Exception {
        CSSValue value = engine.parsePropertyValue("2.0");
        assertTrue(value instanceof Measure);
        assertEquals("2.0", value.getCssText());
    }

    @Test
    public void testInt() throws Exception {
        CSSValue value = engine.parsePropertyValue("34");
        assertTrue(value instanceof Measure);
        assertEquals(((Measure) value).getPrimitiveType(), CSSPrimitiveValue.CSS_NUMBER);
        assertEquals("34", value.getCssText());
    }

    @Test
    public void testIdentifier() throws Exception {
        CSSValue value = engine.parsePropertyValue("SomeWord");
        assertTrue(value instanceof Measure);
        assertEquals(((Measure) value).getPrimitiveType(), CSSPrimitiveValue.CSS_IDENT);
        assertEquals("SomeWord", value.getCssText());
    }

    @Test
    public void testPercent() throws Exception {
        CSSValue value = engine.parsePropertyValue("30%");
        assertTrue(value instanceof Measure);
        assertEquals(((Measure) value).getPrimitiveType(), CSSPrimitiveValue.CSS_PERCENTAGE);
        assertEquals("30.0%", value.getCssText());
    }

    @Test
    public void testPixel() throws Exception {
        CSSValue value = engine.parsePropertyValue("26px");
        assertTrue(value instanceof Measure);
        assertEquals(((Measure) value).getPrimitiveType(), CSSPrimitiveValue.CSS_PX);
        assertEquals("26.0px", value.getCssText());
    }

    @Test
    public void testInch() throws Exception {
        CSSValue value = engine.parsePropertyValue("88in");
        assertTrue(value instanceof Measure);
        assertEquals(((Measure) value).getPrimitiveType(), CSSPrimitiveValue.CSS_IN);
        assertEquals("88.0in", value.getCssText());
    }

    @Test
    public void testEm() throws Exception {
        CSSValue value = engine.parsePropertyValue("75em");
        assertTrue(value instanceof Measure);
        assertEquals(((Measure) value).getPrimitiveType(), CSSPrimitiveValue.CSS_EMS);
        assertEquals("75.0em", value.getCssText());
    }

    @Test
    public void testURI() throws Exception {
        CSSValue value = engine.parsePropertyValue("url(./somepath/picture.gif)");
        assertTrue(value instanceof Measure);
        assertEquals(((Measure) value).getPrimitiveType(), CSSPrimitiveValue.CSS_URI);
        assertEquals("url(./somepath/picture.gif)", value.getCssText());
    }

    @Test
    public void testList() throws Exception {
        CSSValue value = engine.parsePropertyValue("34 34 34");
        assertTrue(value instanceof CSSValueList);
        assertEquals(((CSSValueList) value).getCssValueType(), CSSValue.CSS_VALUE_LIST);
        assertEquals(3, ((CSSValueList) value).getLength());
        assertTrue(((CSSValueList) value).item(0) instanceof Measure);
        assertEquals(CSSPrimitiveValue.CSS_NUMBER, ((Measure) ((CSSValueList) value).item(0)).getPrimitiveType());
        assertTrue(((CSSValueList) value).item(1) instanceof Measure);
        assertEquals(CSSPrimitiveValue.CSS_NUMBER, ((Measure) ((CSSValueList) value).item(1)).getPrimitiveType());
        assertTrue(((CSSValueList) value).item(2) instanceof Measure);
        assertEquals(CSSPrimitiveValue.CSS_NUMBER, ((Measure) ((CSSValueList) value).item(2)).getPrimitiveType());
        assertEquals("34 34 34", value.getCssText());
    }

    @Test
    public void testCommaSeparatedList() throws Exception {
        CSSValue value = engine.parsePropertyValue("34, 34, 34");
        assertTrue(value instanceof CSSValueList);
        CSSValueList list = (CSSValueList) value;
        assertEquals(list.getCssValueType(), CSSValue.CSS_VALUE_LIST);
        assertEquals(5, list.getLength());
        // FIXME: see comments in bug 278139
        for (int i = 0; i < list.getLength(); i++) {
            assertTrue(list.item(i) instanceof Measure);
        }
        assertEquals(CSSPrimitiveValue.CSS_NUMBER, ((Measure) list.item(0)).getPrimitiveType());
        assertEquals(CSSPrimitiveValue.CSS_CUSTOM, ((Measure) list.item(1)).getPrimitiveType());
        assertEquals(CSSPrimitiveValue.CSS_NUMBER, ((Measure) list.item(2)).getPrimitiveType());
        assertEquals(CSSPrimitiveValue.CSS_CUSTOM, ((Measure) list.item(3)).getPrimitiveType());
        assertEquals(CSSPrimitiveValue.CSS_NUMBER, ((Measure) list.item(4)).getPrimitiveType());
        // use String#matches() as there may be white-space differences
        assertTrue(value.getCssText().matches("34\\s*,\\s*34\\s*,\\s*34"));
    }
}
