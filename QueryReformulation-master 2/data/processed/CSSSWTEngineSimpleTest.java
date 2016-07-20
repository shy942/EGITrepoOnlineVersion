/***/
package org.akrogen.tkui.css.swt.engine;

import java.io.StringReader;
import org.akrogen.tkui.css.core.engine.CSSEngine;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class CSSSWTEngineSimpleTest {

    public static void main(String[] args) {
        try {
            Display display = new Display();
            // Create SWT CSS Engine
            CSSEngine engine = new CSSSWTEngineImpl(display);
            // Parse style sheet
            engine.parseStyleSheet(new StringReader("Label {color:red;} Text {background-color:green;}"));
            /*--- Start  UI SWT ---*/
            Shell shell = new Shell(display, SWT.SHELL_TRIM);
            FillLayout layout = new FillLayout();
            shell.setLayout(layout);
            Composite panel1 = new Composite(shell, SWT.NONE);
            panel1.setLayout(new FillLayout());
            // Label
            Label label1 = new Label(panel1, SWT.NONE);
            label1.setText("Label 0");
            // Text
            Text text1 = new Text(panel1, SWT.NONE);
            text1.setText("bla bla bla...");
            /*---  End UI SWT ---*/
            // Apply Styles
            engine.applyStyles(shell, true);
            shell.pack();
            shell.open();
            while (!shell.isDisposed()) {
                if (!display.readAndDispatch())
                    display.sleep();
            }
            display.dispose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
