/***/
package org.eclipse.ui.tests.keys;

import org.eclipse.jface.bindings.keys.KeySequence;
import org.eclipse.jface.bindings.keys.KeySequenceText;
import org.eclipse.jface.bindings.keys.ParseException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.tests.harness.util.UITestCase;

/**
* Tests Bug 43168
*
* @since 3.0
*/
public class Bug43168Test extends UITestCase {

    /**
* Constructor for Bug43168Test.
*
* @param name
*            The name of the test
*/
    public  Bug43168Test(String name) {
        super(name);
    }

    /**
* Tests that a <code>StackOverflowError</code> does not occur when
* trying to set the key sequence in a key sequence entry widget.
*
* @throws ParseException
*             If "CTRL+" is not recognized as a key sequence.
*/
    public void testStackOverflow() throws ParseException {
        Display display = Display.getCurrent();
        Shell shell = new Shell(display);
        shell.setLayout(new RowLayout());
        Text text = new Text(shell, SWT.BORDER);
        KeySequenceText keySequenceText = new KeySequenceText(text);
        shell.pack();
        shell.open();
        //$NON-NLS-1$
        keySequenceText.setKeySequence(KeySequence.getInstance("CTRL+"));
        shell.close();
    }
}
