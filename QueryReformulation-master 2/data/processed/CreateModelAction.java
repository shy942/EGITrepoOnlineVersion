/***/
package org.eclipse.jface.tests.viewers.interactive;

import org.eclipse.jface.tests.viewers.TestElement;

public class CreateModelAction extends TestBrowserAction {

    int fLevel;

    int fChildCount;

    public  CreateModelAction(String label, TestBrowser browser, int level, int childCount) {
        super(label, browser);
        fLevel = level;
        fChildCount = childCount;
    }

    @Override
    public void run() {
        // Clear input since TestElement.equals does only
        // check the id, not the size of the TestElement.
        getBrowser().setInput(null);
        getBrowser().setInput(TestElement.createModel(fLevel, fChildCount));
    }
}
