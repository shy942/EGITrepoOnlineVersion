/***/
package org.eclipse.ui.tests.statushandlers;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.internal.WorkbenchMessages;
import org.eclipse.ui.tests.harness.util.UITestCase;

/**
* This class parses the structure of the Shell and finds necessary widgets.
*
* @since 3.4
*
*/
public class StatusDialogUtil {

    public static Label getTitleImageLabel() {
        Composite c = getTitleAreaComposite();
        if (c != null & !c.isDisposed()) {
            return (Label) c.getChildren()[0];
        }
        return null;
    }

    public static Label getTitleLabel() {
        Composite c = getTitleAreaComposite();
        if (c == null || c.isDisposed()) {
            return null;
        }
        return (Label) c.getChildren()[1];
    }

    public static Label getSingleStatusLabel() {
        Composite c = getTitleAreaComposite();
        if (c == null || c.isDisposed() || c.getChildren().length < 3) {
            return null;
        }
        Composite d = (Composite) c.getChildren()[2];
        return (Label) d.getChildren()[0];
    }

    public static Shell getStatusShell() {
        UITestCase.waitForJobs(100, 1000);
        Shell[] shells = Display.getDefault().getShells();
        for (int i = 0; i < shells.length; i++) {
            if (shells[i].getText().equals("Problem Occurred") || shells[i].getText().equals("Multiple problems have occurred")) {
                if (!shells[i].isDisposed()) {
                    return shells[i];
                }
            }
        }
        return null;
    }

    private static Composite getTitleAreaComposite() {
        Shell shell = getStatusShell();
        if (shell == null || shell.isDisposed()) {
            return null;
        }
        Control controls[] = shell.getChildren();
        return (Composite) ((Composite) controls[0]).getChildren()[0];
    }

    private static Composite getListAreaComposite() {
        Shell shell = getStatusShell();
        if (shell == null || shell.isDisposed()) {
            return null;
        }
        Control controls[] = shell.getChildren();
        return (Composite) ((Composite) controls[0]).getChildren()[1];
    }

    private static Composite getButtonBar() {
        Shell shell = getStatusShell();
        if (shell == null || shell.isDisposed()) {
            return null;
        }
        Control controls[] = shell.getChildren();
        return (Composite) ((Composite) controls[0]).getChildren()[2];
    }

    public static Link getSupportLink() {
        Composite c = getButtonBar();
        if (c == null || c.isDisposed()) {
            return null;
        }
        Composite linkArea = (Composite) c.getChildren()[0];
        for (int i = 0; i < linkArea.getChildren().length; i++) {
            Widget w = linkArea.getChildren()[i];
            if (w instanceof Link) {
                if (((Link) w).getText().equals(WorkbenchMessages.WorkbenchStatusDialog_SupportHyperlink)) {
                    return (Link) w;
                }
            }
        }
        return null;
    }

    public static Link getErrorLogLink() {
        Composite c = getButtonBar();
        if (c == null || c.isDisposed()) {
            return null;
        }
        Composite linkArea = (Composite) c.getChildren()[0];
        for (int i = 0; i < linkArea.getChildren().length; i++) {
            Widget w = linkArea.getChildren()[i];
            if (w instanceof Link) {
                if (((Link) w).getText().equals(WorkbenchMessages.ErrorLogUtil_ShowErrorLogHyperlink)) {
                    return (Link) w;
                }
            }
        }
        return null;
    }

    public static Button getActionButton() {
        Composite c = getButtonBar();
        if (c == null || c.isDisposed()) {
            return null;
        }
        return (Button) c.getChildren()[1];
    }

    public static Button getOkButton() {
        Composite c = getButtonBar();
        if (c == null || c.isDisposed()) {
            return null;
        }
        // retrieve buttons based on dismissal alignment
        if (c.getDisplay().getDismissalAlignment() == SWT.RIGHT) {
            return (Button) c.getChildren()[3];
        }
        return (Button) c.getChildren()[2];
    }

    public static Button getDetailsButton() {
        Composite c = getButtonBar();
        if (c == null || c.isDisposed()) {
            return null;
        }
        // retrieve buttons based on dismissal alignment
        if (c.getDisplay().getDismissalAlignment() == SWT.RIGHT) {
            return (Button) c.getChildren()[2];
        }
        return (Button) c.getChildren()[3];
    }

    public static Table getTable() {
        Composite c = getListAreaComposite();
        if (c == null || c.getChildren().length == 0) {
            return null;
        }
        return (Table) c.getChildren()[0];
    }
}
