/***/
package org.eclipse.jface.tests.viewers.interactive;

import org.eclipse.jface.tests.viewers.TestLabelProvider;
import org.eclipse.jface.viewers.ContentViewer;

public class SetLabelProviderAction extends TestBrowserAction {

    public  SetLabelProviderAction(String label, TestBrowser browser) {
        super(label, browser);
    }

    @Override
    public void run() {
        ((ContentViewer) getBrowser().getViewer()).setLabelProvider(new TestLabelProvider());
    }
}
