/***/
package org.eclipse.jface.tests.viewers;

import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.util.ILogger;
import org.eclipse.jface.util.ISafeRunnableRunner;
import org.eclipse.jface.util.Policy;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.IElementComparer;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import junit.framework.TestCase;

public class ComboViewerComparerTest extends TestCase {

    private Shell shell;

    private StructuredViewer viewer;

    private ILogger oldLogger;

    private ISafeRunnableRunner oldRunner;

    private static final class TestElement {

        private final String name;

        public  TestElement(final String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public void testSetSelection() {
        viewer.setContentProvider(new ArrayContentProvider());
        viewer.setComparer(new IElementComparer() {

            @Override
            public boolean equals(final Object element1, final Object element2) {
                TestElement testElement1 = (TestElement) element1;
                TestElement testElement2 = (TestElement) element2;
                return testElement1.getName().equals(testElement2.getName());
            }

            @Override
            public int hashCode(final Object element) {
                TestElement testElement = (TestElement) element;
                return testElement.getName().hashCode();
            }
        });
        viewer.setInput(new TestElement[] { new TestElement("a"), new TestElement("b") });
        // Select equal element with different identity
        TestElement aElement = new TestElement("a");
        viewer.setSelection(new StructuredSelection(aElement));
        StructuredSelection sel = ((StructuredSelection) viewer.getSelection());
        assertEquals(false, sel.isEmpty());
        TestElement selectedElement = (TestElement) sel.getFirstElement();
        assertEquals(aElement.getName(), selectedElement.getName());
    }

    @Override
    protected void setUp() {
        oldLogger = Policy.getLog();
        oldRunner = SafeRunnable.getRunner();
        Policy.setLog(new ILogger() {

            @Override
            public void log(IStatus status) {
                fail(status.getMessage());
            }
        });
        SafeRunnable.setRunner(new ISafeRunnableRunner() {

            @Override
            public void run(ISafeRunnable code) {
                try {
                    code.run();
                } catch (Throwable th) {
                    throw new RuntimeException(th);
                }
            }
        });
        Display display = Display.getCurrent();
        if (display == null) {
            display = new Display();
        }
        shell = new Shell(display);
        shell.setSize(500, 500);
        shell.setLayout(new FillLayout());
        viewer = createViewer(shell);
        shell.open();
    }

    @Override
    protected void tearDown() {
        Policy.setLog(oldLogger);
        SafeRunnable.setRunner(oldRunner);
        processEvents();
        viewer = null;
        if (shell != null) {
            shell.dispose();
            shell = null;
        }
    }

    protected StructuredViewer createViewer(final Shell parent) {
        return new ComboViewer(parent, SWT.NONE);
    }

    private void processEvents() {
        if (shell != null && !shell.isDisposed()) {
            Display display = shell.getDisplay();
            if (display != null) {
                while (display.readAndDispatch()) {
                // loop until there are no more events to dispatch
                }
            }
        }
    }
}
