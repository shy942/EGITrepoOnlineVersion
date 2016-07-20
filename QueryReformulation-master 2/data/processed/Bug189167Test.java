/***/
package org.eclipse.ui.tests.keys;

import junit.framework.TestCase;
import org.eclipse.jface.bindings.Binding;

/**
* Tests Bug 189167
*
* @since 3.4
*/
public class Bug189167Test extends TestCase {

    private Binding createTestBinding() {
        return new //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        TestBinding(//$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        "commandId", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        "schemeId", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        "contextId", //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        "locale", "platform", 0, //$NON-NLS-1$
        null);
    }

    public void testBindingsEqual() {
        Binding one = createTestBinding();
        Binding two = createTestBinding();
        assertEquals(one, two);
    }

    public void testHashCodeEquals() {
        Binding one = createTestBinding();
        Binding two = createTestBinding();
        Binding b3 = new TestBinding("commandID", "schemeID", "contextID", "locale", "platform", 1, null);
        assertEquals(one, two);
        assertEquals(one.hashCode(), two.hashCode());
        assertFalse(one.equals(b3));
    }
}
