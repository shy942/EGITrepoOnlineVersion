/***/
package org.eclipse.jface.tests.viewers.interactive;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.tests.viewers.TestElement;
import org.eclipse.jface.tests.viewers.TestModelContentProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.widgets.Composite;

public class TestTree extends TestBrowser {

    protected TreeViewer fViewer2;

    private Action fExpandAllAction;

    public  TestTree() {
        super();
        fExpandAllAction = new ExpandAllAction("Expand All", this);
    }

    @Override
    public Viewer createViewer(Composite parent) {
        TreeViewer viewer = new TreeViewer(parent);
        viewer.setContentProvider(new TestModelContentProvider());
        viewer.setUseHashlookup(true);
        if (fViewer2 == null) {
            fViewer2 = viewer;
        }
        return viewer;
    }

    public static void main(String[] args) {
        TestBrowser browser = new TestTree();
        if (args.length > 0 && args[0].equals("-twopanes")) {
            browser.show2Panes();
        }
        browser.setBlockOnOpen(true);
        browser.open(TestElement.createModel(3, 10));
    }

    /**
* Adds the expand all action to the tests menu.
*/
    @Override
    protected void viewerFillMenuBar(MenuManager mgr) {
        MenuManager testMenu = (MenuManager) (mgr.findMenuUsingPath("tests"));
        testMenu.add(new Separator());
        testMenu.add(fExpandAllAction);
    }
}
