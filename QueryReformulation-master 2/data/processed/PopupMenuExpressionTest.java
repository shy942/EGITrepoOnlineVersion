/***/
package org.eclipse.ui.tests.internal;

import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.tests.api.ListView;
import org.eclipse.ui.tests.harness.util.ActionUtil;

/**
* This class contains tests for popup menu visibility
*/
public class PopupMenuExpressionTest extends ActionExpressionTest {

    public  PopupMenuExpressionTest(String testName) {
        super(testName);
    }

    /**
* Returns the menu manager containing the actions.
*/
    @Override
    protected MenuManager getActionMenuManager(ListView view) throws Throwable {
        return view.getMenuManager();
    }

    /**
* Tests the visibility of an action.
*/
    @Override
    protected void testAction(MenuManager mgr, String action, boolean expected) throws Throwable {
        if (expected) {
            assertNotNull(action, ActionUtil.getActionWithLabel(mgr, action));
        } else {
            assertNull(action, ActionUtil.getActionWithLabel(mgr, action));
        }
    }

    public void testExpressionEnabledAction() throws Throwable {
        // Setup.
        ListView view = showListView();
        MenuManager mgr = getActionMenuManager(view);
        // Test null selection.
        selectAndUpdateMenu(view, null, mgr);
        testAction(mgr, "expressionEnablementAction_v2", false);
        // Test red selection.
        selectAndUpdateMenu(view, red, mgr);
        testAction(mgr, "expressionEnablementAction_v2", true);
        // Test blue selection.
        selectAndUpdateMenu(view, blue, mgr);
        testAction(mgr, "expressionEnablementAction_v2", false);
    }
}
