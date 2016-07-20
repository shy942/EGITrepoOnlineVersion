/***/
package org.eclipse.ui.tests.forms.util;

import junit.framework.TestCase;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class FormToolkitTest extends TestCase {

    /*
* Verify that calling dispose twice does not cause an NPE
*/
    public void testDispose() {
        Display display = Display.getCurrent();
        FormToolkit toolkit = new FormToolkit(display);
        toolkit.dispose();
        toolkit.dispose();
    }
}
