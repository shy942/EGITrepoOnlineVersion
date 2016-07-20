/***/
package org.eclipse.e4.ui.css.core.resources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import org.eclipse.e4.ui.css.core.dom.properties.css2.CSS2FontProperties;
import org.eclipse.e4.ui.css.swt.helpers.CSSSWTHelperTestCase;
import org.eclipse.e4.ui.css.swt.resources.ResourceByDefinitionKey;
import org.eclipse.e4.ui.css.swt.resources.SWTResourceRegistryKeyFactory;
import org.eclipse.swt.SWT;
import org.junit.Test;
import org.w3c.dom.css.CSSPrimitiveValue;

public class SWTResourceRegistryKeyFactoryTest extends CSSSWTHelperTestCase {

    private SWTResourceRegistryKeyFactory factory = new SWTResourceRegistryKeyFactory();

    @Test
    public void testCreateKeyWhenFontProperty() {
        CSS2FontProperties fontProperties = null;
        fontProperties = fontProperties("Arial", 12, SWT.ITALIC);
        Object result = factory.createKey(fontProperties);
        assertEquals(String.class, result.getClass());
        assertEquals(CSSResourcesHelpers.getCSSValueKey(fontProperties), result);
    }

    @Test
    public void testCreateKeyWhenColorValue() {
        CSSPrimitiveValue colorValue = colorValue("red");
        Object result = factory.createKey(colorValue);
        assertEquals(String.class, result.getClass());
        assertEquals(CSSResourcesHelpers.getCSSValueKey(colorValue), result);
    }

    @Test
    public void testCreateKeyWhenFontByDefinition() {
        CSS2FontProperties fontProperties = null;
        try {
            fontProperties = fontProperties("#font-by-definition", 12, SWT.ITALIC);
        } catch (Exception e) {
            fail("FontProperties should not throw exception");
        }
        Object result = factory.createKey(fontProperties);
        assertEquals(ResourceByDefinitionKey.class, result.getClass());
        assertEquals(CSSResourcesHelpers.getCSSValueKey(fontProperties).toString(), result.toString());
    }

    @Test
    public void testCreateKeyWhenColorByDefinition() {
        CSSPrimitiveValue colorValue = colorValue("#color-by-definition");
        Object result = factory.createKey(colorValue);
        assertEquals(ResourceByDefinitionKey.class, result.getClass());
        assertEquals(CSSResourcesHelpers.getCSSValueKey(colorValue).toString(), result.toString());
    }
}
