/***/
package org.eclipse.ui.internal;

import org.eclipse.core.runtime.Assert;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IPersistableElement;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchPart2;

public class PartTester {

    private  PartTester() {
    }

    /**
* Sanity-check the public interface of the editor. This is called on every editor after it
* is fully initiallized, but before it is actually connected to the editor reference or the
* layout. Calls as much of the editor's public interface as possible to test for exceptions,
* and tests the return values for glaring faults. This does not need to be an exhaustive conformance
* test, as it is called every time an editor is opened and it needs to be efficient.
* The part should be unmodified when the method exits.
*
* @param part
*/
    public static void testEditor(IEditorPart part) throws Exception {
        testWorkbenchPart(part);
        Assert.isTrue(part.getEditorSite() == part.getSite(), //$NON-NLS-1$
        "The part's editor site must be the same as the part's site");
        IEditorInput input = part.getEditorInput();
        //$NON-NLS-1$
        Assert.isNotNull(input, "The editor input must be non-null");
        testEditorInput(input);
        part.isDirty();
        part.isSaveAsAllowed();
        part.isSaveOnCloseNeeded();
    }

    public static void testEditorInput(IEditorInput input) throws Exception {
        input.getAdapter(Object.class);
        // Don't test input.getImageDescriptor() -- the workbench never uses that
        // method and most editor inputs would fail the test. It should really be
        // deprecated.
        Assert.isNotNull(input.getName(), //$NON-NLS-1$
        "The editor input must have a non-null name");
        Assert.isNotNull(input.getToolTipText(), //$NON-NLS-1$
        "The editor input must have a non-null tool tip");
        // Persistable element may be null
        IPersistableElement persistableElement = input.getPersistable();
        if (persistableElement != null) {
            Assert.isNotNull(persistableElement.getFactoryId(), //$NON-NLS-1$
            "The persistable element for the editor input must have a non-null factory id");
        }
    }

    /**
* Sanity-checks a workbench part. Excercises the public interface and tests for any
* obviously bogus return values. The part should be unmodified when the method exits.
*
* @param part
* @throws Exception
*/
    private static void testWorkbenchPart(IWorkbenchPart part) throws Exception {
        IPropertyListener testListener = new IPropertyListener() {

            @Override
            public void propertyChanged(Object source, int propId) {
            }
        };
        // Test addPropertyListener
        part.addPropertyListener(testListener);
        // Test removePropertyListener
        part.removePropertyListener(testListener);
        // Test equals
        //$NON-NLS-1$
        Assert.isTrue(part.equals(part), "A part must be equal to itself");
        Assert.isTrue(!part.equals(Integer.valueOf(32)), //$NON-NLS-1$
        "A part must have a meaningful equals method");
        // Test getAdapter
        Object partAdapter = part.getAdapter(part.getClass());
        Assert.isTrue(partAdapter == null || partAdapter == part, //$NON-NLS-1$
        "A part must adapter to itself or return null");
        // Test getTitle
        //$NON-NLS-1$
        Assert.isNotNull(part.getTitle(), "A part's title must be non-null");
        // Test getTitleImage
        Assert.isNotNull(part.getTitleImage(), //$NON-NLS-1$
        "A part's title image must be non-null");
        // Test getTitleToolTip
        Assert.isNotNull(part.getTitleToolTip(), //$NON-NLS-1$
        "A part's title tool tip must be non-null");
        // Test toString
        Assert.isNotNull(part.toString(), //$NON-NLS-1$
        "A part's toString method must return a non-null value");
        // Compute hashCode
        part.hashCode();
        if (part instanceof IWorkbenchPart2) {
            testWorkbenchPart2((IWorkbenchPart2) part);
        }
    }

    private static void testWorkbenchPart2(IWorkbenchPart2 part) throws Exception {
        Assert.isNotNull(part.getContentDescription(), //$NON-NLS-1$
        "A part must return a non-null content description");
        Assert.isNotNull(part.getPartName(), //$NON-NLS-1$
        "A part must return a non-null part name");
    }

    /**
* Sanity-check the public interface of a view. This is called on every view after it
* is fully initiallized, but before it is actually connected to the part reference or the
* layout. Calls as much of the part's public interface as possible without modifying the part
* to test for exceptions and check the return values for glaring faults. This does not need
* to be an exhaustive conformance test, as it is called every time an editor is opened and
* it needs to be efficient.
*
* @param part
*/
    public static void testView(IViewPart part) throws Exception {
        Assert.isTrue(part.getSite() == part.getViewSite(), //$NON-NLS-1$
        "A part's site must be the same as a part's view site");
        testWorkbenchPart(part);
    }
}
