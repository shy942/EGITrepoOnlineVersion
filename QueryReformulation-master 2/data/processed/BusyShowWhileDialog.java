/***/
package org.eclipse.ui.examples.jobs;

import java.lang.reflect.InvocationTargetException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.IconAndMessageDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.progress.ProgressManager;

/**
*BusyShowWhileDialog is a test of busyShowWhile in a modal dialog.
*/
public class BusyShowWhileDialog extends IconAndMessageDialog {

    /**
* @param parentShell
* @todo Generated comment
*/
    public  BusyShowWhileDialog(Shell parentShell) {
        super(parentShell);
        //$NON-NLS-1$
        message = "Busy While Test";
    }

    @Override
    protected Image getImage() {
        return null;
    }

    @Override
    protected void createButtonsForButtonBar(Composite parent) {
        super.createButtonsForButtonBar(parent);
        //$NON-NLS-1$
        Button detailsButton = createButton(parent, 4, "Start busy show while", false);
        detailsButton.addSelectionListener(new SelectionListener() {

            @Override
            public void widgetSelected(SelectionEvent e) {
                try {
                    ProgressManager.getInstance().busyCursorWhile(new IRunnableWithProgress() {

                        @Override
                        public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
                            long time = System.currentTimeMillis();
                            long delay = PlatformUI.getWorkbench().getProgressService().getLongOperationTime();
                            long end = time + delay + delay;
                            while (end > System.currentTimeMillis()) {
                                final Shell myShell = BusyShowWhileDialog.this.getShell();
                                myShell.getDisplay().asyncExec(new Runnable() {

                                    @Override
                                    public void run() {
                                        if (myShell.isDisposed())
                                            return;
                                        myShell.getDisplay().sleep();
                                        myShell.setText(String.valueOf(System.currentTimeMillis()));
                                    }
                                });
                            }
                        }
                    });
                } catch (InvocationTargetException error) {
                    error.printStackTrace();
                } catch (InterruptedException error) {
                }
            }

            @Override
            public void widgetDefaultSelected(SelectionEvent e) {
            //do nothing
            }
        });
    }
}
