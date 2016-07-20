/***/
package org.eclipse.jface.tests.viewers;

import org.eclipse.core.runtime.ISafeRunnable;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.util.ILogger;
import org.eclipse.jface.util.ISafeRunnableRunner;
import org.eclipse.jface.util.Policy;
import org.eclipse.jface.util.SafeRunnable;
import org.eclipse.jface.util.Util;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import junit.framework.TestCase;

public abstract class ViewerTestCase extends TestCase {

    Display fDisplay;

    protected Shell fShell;

    protected StructuredViewer fViewer;

    protected TestElement fRootElement;

    public TestModel fModel;

    protected boolean disableTestsBug347491 = false;

    protected boolean disableTestsBug493357 = false;

    private ILogger oldLogger;

    private ISafeRunnableRunner oldRunner;

    public  ViewerTestCase(String name) {
        super(name);
        disableTestsBug347491 = Util.isCocoa();
    }

    protected void assertSelectionEquals(String message, TestElement expected) {
        ISelection selection = fViewer.getSelection();
        assertTrue(selection instanceof IStructuredSelection);
        IStructuredSelection expectedSelection = new StructuredSelection(expected);
        assertEquals("selectionEquals - " + message, selection, expectedSelection);
    }

    protected abstract StructuredViewer createViewer(Composite parent);

    public void interact() {
        Shell shell = fShell;
        if (shell != null && !shell.isDisposed()) {
            Display display = shell.getDisplay();
            if (display != null) {
                while (shell.isVisible()) {
                    display.readAndDispatch();
                }
            }
        }
    }

    protected void openBrowser() {
        fDisplay = Display.getCurrent();
        if (fDisplay == null) {
            fDisplay = new Display();
        }
        fShell = new Shell(fDisplay, getShellStyle());
        fShell.setSize(500, 500);
        fShell.setLayout(new FillLayout());
        fViewer = createViewer(fShell);
        fViewer.setUseHashlookup(true);
        setInput();
        fShell.open();
    //processEvents();
    }

    /**
* @return
*/
    protected int getShellStyle() {
        return SWT.SHELL_TRIM;
    }

    protected void setInput() {
        fViewer.setInput(fRootElement);
    }

    public void processEvents() {
        Shell shell = fShell;
        if (shell != null && !shell.isDisposed()) {
            Display display = shell.getDisplay();
            if (display != null) {
                while (display.readAndDispatch()) {
                // loop until there are no more events to dispatch
                }
            }
        }
    }

    @Override
    public void setUp() {
        // $NON-NLS-1//$NON-NLS-2//$NON-NLS-3
        disableTestsBug493357 = System.getProperty("org.eclipse.swt.internal.gtk.version", "").startsWith("3.");
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
        setUpModel();
        openBrowser();
    }

    protected void setUpModel() {
        fRootElement = TestElement.createModel(3, 10);
        fModel = fRootElement.getModel();
    }

    void sleep(int d) {
        processEvents();
        try {
            Thread.sleep(d * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void tearDown() {
        Policy.setLog(oldLogger);
        SafeRunnable.setRunner(oldRunner);
        processEvents();
        fViewer = null;
        if (fShell != null) {
            fShell.dispose();
            fShell = null;
        }
        // leave the display
        fRootElement = null;
        fModel = null;
    }
}
