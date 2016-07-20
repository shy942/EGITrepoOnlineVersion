/***/
package org.eclipse.e4.ui.tests.css.core.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import java.io.IOException;
import org.eclipse.e4.ui.css.core.engine.CSSEngine;
import org.eclipse.e4.ui.tests.css.core.util.ParserTestUtil;
import org.junit.Before;
import org.junit.Test;
import org.w3c.css.sac.CSSParseException;
import org.w3c.css.sac.SelectorList;

public class SelectorTest {

    private CSSEngine engine;

    @Before
    public void setUp() throws Exception {
        engine = ParserTestUtil.createEngine();
    }

    @Test
    public void testSimpleSelector() throws Exception {
        SelectorList list = engine.parseSelectors("Type1");
        assertNotNull(list);
        assertEquals(1, list.getLength());
        assertEquals("Type1", list.item(0).toString());
    }

    @Test
    public void testMultipleSelectors() throws Exception {
        SelectorList list = engine.parseSelectors("Type1, Type2");
        assertNotNull(list);
        assertEquals(2, list.getLength());
        assertEquals("Type1", list.item(0).toString());
        assertEquals("Type2", list.item(1).toString());
    }

    @Test
    public void testClassSelector() throws Exception {
        SelectorList list = engine.parseSelectors(".Class1");
        assertNotNull(list);
        assertEquals(1, list.getLength());
        assertEquals("*[class=\"Class1\"]", list.item(0).toString());
    }

    @Test
    public void testAttributeSelector() throws Exception {
        SelectorList list = engine.parseSelectors("*[class='Class1']");
        assertNotNull(list);
        assertEquals(1, list.getLength());
        assertEquals("*[class=\"Class1\"]", list.item(0).toString());
    }

    @Test
    public void testErrorAttributeSelector() throws IOException {
        try {
            // missing ']'
            engine.parseSelectors("*[class='Class1'");
            fail("Parser should have errored on missing bracket");
        } catch (CSSParseException e) {
        }
    }
}
