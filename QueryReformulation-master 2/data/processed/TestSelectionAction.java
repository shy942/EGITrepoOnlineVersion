/***/
package org.eclipse.jface.tests.viewers.interactive;

import org.eclipse.jface.tests.viewers.TestElement;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;

public abstract class TestSelectionAction extends TestBrowserAction implements ISelectionChangedListener {

    public  TestSelectionAction(String label, TestBrowser browser) {
        super(label, browser);
        browser.getViewer().addSelectionChangedListener(this);
        setEnabled(false);
    }

    public TestElement getTestElement(ISelection selection) {
        if (selection instanceof IStructuredSelection) {
            return (TestElement) ((IStructuredSelection) selection).getFirstElement();
        }
        return null;
    }

    @Override
    public final void run() {
        TestElement testElement = getTestElement(getBrowser().getViewer().getSelection());
        if (testElement != null) {
            run(testElement);
        }
    }

    /**
* The default implementation calls run(TestElement) on every element
* contained in the vector.
*/
    protected abstract void run(TestElement o);

    @Override
    public void selectionChanged(SelectionChangedEvent event) {
        setEnabled(!event.getSelection().isEmpty());
    }
}
