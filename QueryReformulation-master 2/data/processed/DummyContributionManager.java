/***/
package org.eclipse.jface.tests.action;

import org.eclipse.jface.action.ContributionManager;

/**
* A dummy contribution manager, used just for testing.
* Does not populate any widgets.
*/
class DummyContributionManager extends ContributionManager {

    @Override
    public void update(boolean force) {
    // ignore
    }
}
