/***/
package org.eclipse.ui.tests.keys;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.tests.harness.util.UITestCase;

/**
* Tests Bug 43597
*
* @since 3.0
*/
public class Bug43597Test extends UITestCase {

    private Font textFont;

    /**
* Constructor for Bug43597Test.
*
* @param name
*            The name of the test
*/
    public  Bug43597Test(String name) {
        super(name);
    }

    /**
* Tests that setting the text on a text widget to an empty string does not
* reset the font. This was a problem only on carbon.
*/
    public void testFontReset() {
        //$NON-NLS-1$
        String metaCharacter = "?X";
        // Set up a working environment.
        Display display = Display.getCurrent();
        Shell shell = new Shell(display);
        GridLayout gridLayout = new GridLayout();
        shell.setLayout(gridLayout);
        Text text = new Text(shell, SWT.LEFT);
        textFont = new Font(text.getDisplay(), "Lucida Grande", 13, SWT.NORMAL);
        //$NON-NLS-1$
        text.setFont(textFont);
        text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        shell.pack();
        shell.open();
        // Set the text once, and get the font.
        //$NON-NLS-1$
        text.setText(metaCharacter);
        Font fontBefore = text.getFont();
        // Set the font again, and get the font afterward.
        //$NON-NLS-1$
        text.setText("");
        text.setText(metaCharacter);
        Font fontAfter = text.getFont();
        // Test.
        //$NON-NLS-1$
        assertEquals("Clearing text resets font.", fontBefore, fontAfter);
        // Clean up after myself.
        shell.close();
        shell.dispose();
    }

    @Override
    protected void doTearDown() throws Exception {
        if (textFont != null) {
            textFont.dispose();
        }
        super.doTearDown();
    }
}
