/***/
package org.eclipse.ui.tests.propertysheet;

import junit.framework.TestCase;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.ui.views.properties.ComboBoxLabelProvider;
import org.eclipse.ui.views.properties.ComboBoxPropertyDescriptor;

/**
* Test for new functionality pertaining to Bug 21013.
*
* @since 3.0
*/
public class ComboBoxPropertyDescriptorTest extends TestCase {

    //$NON-NLS-1$
    private String ID = "ID";

    //$NON-NLS-1$
    private String NAME = "NAME";

    //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    private String[] values = { "One", "Two", "Three" };

    private ComboBoxPropertyDescriptor descriptor;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        descriptor = new ComboBoxPropertyDescriptor(ID, NAME, values);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
* Tests the case where the user does not set an ILabelProvider.
*/
    public void testGetDefaultLabelProvider() {
        ILabelProvider provider = descriptor.getLabelProvider();
        assertEquals(//$NON-NLS-1$
        "Default label provider is of the wrong type", ComboBoxLabelProvider.class, provider.getClass());
        for (int i = 0; i < values.length; i++) {
            String expected = values[i];
            assertEquals(//$NON-NLS-1$
            "Wrong label provided", expected, provider.getText(Integer.valueOf(i)));
        }
        testWrongLabel(provider, new Object());
        testWrongLabel(provider, null);
        testWrongLabel(provider, Integer.valueOf(-1));
        testWrongLabel(provider, Integer.valueOf(values.length));
    }

    /**
* Tests that a bad element object (an Integer outside the accepted range,
* null or an other Object) returns the empty String.
* @param provider the provider to test against.
* @param element the element to test.
*/
    public void testWrongLabel(ILabelProvider provider, Object element) {
        assertEquals(//$NON-NLS-1$
        "Wrong label provided in bad case", //$NON-NLS-1$
        "", provider.getText(element));
    }

    /**
* Tests the case where the user sets their own ILabelProvider.
*/
    public void testSetGetLabelProvider() {
        ILabelProvider provider = new LabelProvider();
        descriptor.setLabelProvider(provider);
        ILabelProvider descProvider = descriptor.getLabelProvider();
        assertSame(//$NON-NLS-1$
        "Wrong label provider", provider, descProvider);
    }
}
