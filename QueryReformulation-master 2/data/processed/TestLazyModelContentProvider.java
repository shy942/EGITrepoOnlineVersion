/***/
package org.eclipse.jface.tests.viewers;

import junit.framework.AssertionFailedError;
import org.eclipse.core.runtime.Assert;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILazyContentProvider;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.Viewer;

/**
* The TestLazyModelContentProvider is the lazy version
* of the model content provider.
*/
public class TestLazyModelContentProvider extends TestModelContentProvider implements ILazyContentProvider, IContentProvider {

    TableViewerTest test;

    TestElement input;

     TestLazyModelContentProvider(TableViewerTest testObject) {
        test = testObject;
        if (!(testObject instanceof VirtualLazyTableViewerTest)) {
            throw new AssertionFailedError("TestLazyModelContentProvider only works with VirtualLazyTableViewerTest");
        }
    }

    @Override
    public void updateElement(int index) {
        ((VirtualLazyTableViewerTest) test).updateElementCalled(index);
        if (input == null) {
            //Nothing to update yet
            return;
        }
        ((TableViewer) test.fViewer).replace(input.getChildAt(index), index);
    }

    @Override
    public void dispose() {
        super.dispose();
    }

    @Override
    public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        input = (TestElement) newInput;
        ((TableViewer) viewer).setItemCount(input == null ? 0 : input.getChildCount());
        super.inputChanged(viewer, oldInput, newInput);
    }

    @Override
    public Object[] getElements(Object element) {
        Assert.isTrue(false, "Should not ever call getElements if lazy");
        return super.getElements(element);
    }
}
