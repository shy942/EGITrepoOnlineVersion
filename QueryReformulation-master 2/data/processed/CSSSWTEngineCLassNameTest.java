/***/
package org.akrogen.tkui.css.swt.engine.className;

import java.io.StringReader;
import org.akrogen.tkui.css.core.engine.CSSEngine;
import org.akrogen.tkui.css.swt.engine.CSSSWTEngineImpl;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class CSSSWTEngineCLassNameTest {

    public static void main(String[] args) {
        try {
            Display display = new Display();
            // Instanciate SWT CSS Engine
            CSSEngine engine = new CSSSWTEngineImpl(display);
            // Parse style class name.
            engine.parseStyleSheet(new StringReader(".blueClass {color:blue;} .greenClass {color:green;} .redClass {color:red;} .yellowClass {color:yellow;}"));
            /*---   UI SWT ---*/
            Shell shell = new Shell(display, SWT.SHELL_TRIM);
            FillLayout layout = new FillLayout();
            shell.setLayout(layout);
            Composite panel1 = new Composite(shell, SWT.NONE);
            panel1.setLayout(new FillLayout());
            // Label
            Label label1 = new Label(panel1, SWT.NONE);
            label1.setText("Label 0 [class=redClass]");
            label1.setData("class", "redClass");
            // Text
            Text text1 = new Text(panel1, SWT.NONE);
            text1.setText("bla bla bla...");
            Label label2 = new Label(panel1, SWT.NONE);
            label2.setText("Label 2 [class=blueClass]");
            label2.setData("class", "blueClass");
            Label label3 = new Label(panel1, SWT.NONE);
            label3.setText("Label 3 [class=yellowClass]");
            label3.setData("class", "yellowClass");
            // Composite
            Composite panel2 = new Composite(panel1, SWT.NONE);
            panel2.setLayout(new FillLayout());
            // Label
            Label label4 = new Label(panel2, SWT.NONE);
            label4.setText("Label 4 [class=greenClass]");
            label4.setData("class", "greenClass");
            /*---   End UI SWT  ---*/
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
