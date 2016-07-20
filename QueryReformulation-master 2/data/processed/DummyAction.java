/***/
package org.eclipse.jface.tests.action;

import org.eclipse.jface.action.Action;

/**
* A dummy action, used just for testing.
*/
class DummyAction extends Action {

    static int Count = 0;

    public  DummyAction() {
        super("DummyAction " + ++Count);
    }
}
