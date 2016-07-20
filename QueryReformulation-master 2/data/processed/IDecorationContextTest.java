/***/
package org.eclipse.jface.tests.labelProviders;

import junit.framework.TestCase;
import org.eclipse.core.runtime.AssertionFailedException;
import org.eclipse.jface.viewers.DecoratingStyledCellLabelProvider;
import org.eclipse.jface.viewers.DecorationContext;
import org.eclipse.jface.viewers.IDecorationContext;
import org.eclipse.jface.viewers.ILabelDecorator;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.swt.graphics.Image;

/**
* Most of the setup has been taken from
* org.eclipse.jface.snippets.viewers.Snippet010OwnerDraw.java
*
* @since 3.4
*
*/
public class IDecorationContextTest extends TestCase {

    private IDecorationContext getDecorationContext() {
        return new IDecorationContext() {

            @Override
            public String[] getProperties() {
                return null;
            }

            @Override
            public Object getProperty(String property) {
                return null;
            }
        };
    }

    private IStyledLabelProvider getStyledLabelProvider() {
        return new IStyledLabelProvider() {

            @Override
            public Image getImage(Object element) {
                return null;
            }

            @Override
            public StyledString getStyledText(Object element) {
                return null;
            }

            @Override
            public void addListener(ILabelProviderListener listener) {
            }

            @Override
            public void dispose() {
            }

            @Override
            public boolean isLabelProperty(Object element, String property) {
                return false;
            }

            @Override
            public void removeListener(ILabelProviderListener listener) {
            }
        };
    }

    private ILabelDecorator getLabelDecorator() {
        return new ILabelDecorator() {

            @Override
            public Image decorateImage(Image image, Object element) {
                return null;
            }

            @Override
            public String decorateText(String text, Object element) {
                return null;
            }

            @Override
            public void addListener(ILabelProviderListener listener) {
            }

            @Override
            public void dispose() {
            }

            @Override
            public boolean isLabelProperty(Object element, String property) {
                return false;
            }

            @Override
            public void removeListener(ILabelProviderListener listener) {
            }
        };
    }

    private DecoratingStyledCellLabelProvider getDecoratingStyledCellLabelProvider(boolean nullDecorationContext) {
        return nullDecorationContext ? new DecoratingStyledCellLabelProvider(getStyledLabelProvider(), getLabelDecorator(), null) : new DecoratingStyledCellLabelProvider(getStyledLabelProvider(), getLabelDecorator(), getDecorationContext());
    }

    public  IDecorationContextTest(String name) {
        super(name);
    }

    public void testDefaultContextIsUsed() {
        // Create a DecoratingStyledCellLabelProvider with a null
        // decorationContext
        assertEquals(getDecoratingStyledCellLabelProvider(true).getDecorationContext(), DecorationContext.DEFAULT_CONTEXT);
    }

    public void testSetDecorationContextNull() {
        DecoratingStyledCellLabelProvider label = getDecoratingStyledCellLabelProvider(false);
        try {
            label.setDecorationContext(null);
            fail("DecoratingStyledCellLabelProvider.setDecorationContext did not throw an exception when passed null");
        } catch (AssertionFailedException e) {
        }
    }
}
