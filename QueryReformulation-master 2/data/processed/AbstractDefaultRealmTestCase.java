/***/
package org.eclipse.jface.tests.databinding;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.jface.databinding.conformance.util.RealmTester;
import org.eclipse.jface.databinding.swt.DisplayRealm;
import org.eclipse.swt.widgets.Display;
import junit.framework.TestCase;

/**
* Base class that sets the default realm to be the SWT realm.
*
* @since 3.3
*/
public class AbstractDefaultRealmTestCase extends TestCase {

    private Realm previousRealm;

    /**
* Sets the default realm to be the realm for the default display.
*
* @see junit.framework.TestCase#setUp()
*/
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        previousRealm = Realm.getDefault();
        Display display = Display.getCurrent() != null && !Display.getCurrent().isDisposed() ? Display.getCurrent() : Display.getDefault();
        RealmTester.setDefault(DisplayRealm.getRealm(display));
    }

    /**
* Runs all currently-enqueued asynchronous events
*/
    protected void runAsync() {
        Display display = Display.getCurrent();
        while (display.readAndDispatch()) {
        }
    }

    /**
* Removes the default realm.
*/
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        RealmTester.setDefault(previousRealm);
    }
}
