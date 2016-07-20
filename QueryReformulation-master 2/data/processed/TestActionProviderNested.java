/***/
package org.eclipse.ui.tests.navigator.extension;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.navigator.CommonActionProvider;
import org.eclipse.ui.navigator.ICommonActionConstants;
import org.eclipse.ui.navigator.ICommonActionExtensionSite;
import org.eclipse.ui.navigator.ICommonMenuConstants;
import org.eclipse.ui.navigator.ICommonViewerWorkbenchSite;

public class TestActionProviderNested extends CommonActionProvider {

    public static final String GROUP_TEST_MENU = "group.testMenu";

    public static final String GROUP_TEST_DEPENDENCY = "group.testDependency";

    private IAction action = null;

    private IAction openAction;

    private ICommonActionExtensionSite site;

    @Override
    public void init(ICommonActionExtensionSite aSite) {
        super.init(aSite);
        site = aSite;
        action = new TestAction(aSite.getViewSite().getShell(), "Nested action (only visible if test ext active)");
        action.setId("org.eclipse.ui.tests.navigator.NestedAction");
        openAction = new Action() {

            @Override
            public void run() {
                IStructuredSelection selection = (IStructuredSelection) getContext().getSelection();
                if (selection.size() == 1) {
                    TestExtensionTreeData data = (TestExtensionTreeData) selection.getFirstElement();
                    ((TreeViewer) site.getStructuredViewer()).setExpandedState(data, true);
                    try {
                        IDE.openEditor(((ICommonViewerWorkbenchSite) site.getViewSite()).getPage(), data.getFile(), true);
                    } catch (PartInitException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
    }

    @Override
    public void fillContextMenu(IMenuManager menu) {
        menu.insertAfter(ICommonMenuConstants.GROUP_ADDITIONS, action);
    }

    @Override
    public void fillActionBars(IActionBars actionBars) {
        super.fillActionBars(actionBars);
        actionBars.setGlobalActionHandler(ICommonActionConstants.OPEN, openAction);
    }
}
