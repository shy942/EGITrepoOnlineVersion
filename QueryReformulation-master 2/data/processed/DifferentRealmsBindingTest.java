/***/
package org.eclipse.core.tests.internal.databinding;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.core.databinding.DataBindingContext;
import org.eclipse.core.databinding.observable.list.ObservableList;
import org.eclipse.core.databinding.observable.list.WritableList;
import org.eclipse.core.databinding.observable.set.ObservableSet;
import org.eclipse.core.databinding.observable.set.WritableSet;
import org.eclipse.core.databinding.util.ILogger;
import org.eclipse.core.databinding.util.Policy;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.tests.databinding.observable.ThreadRealm;
import junit.framework.TestCase;

public class DifferentRealmsBindingTest extends TestCase {

    ThreadRealm targetAndModelRealm = new ThreadRealm();

    ThreadRealm validationRealm = new ThreadRealm();

    List<IStatus> errorStatusses = new ArrayList();

    DataBindingContext dbc;

    ILogger logger = new ILogger() {

        @Override
        public void log(IStatus status) {
            if (!status.isOK()) {
                errorStatusses.add(status);
            }
        }
    };

    @Override
    protected void setUp() throws Exception {
        errorStatusses.clear();
        new Thread() {

            @Override
            public void run() {
                targetAndModelRealm.init(Thread.currentThread());
                targetAndModelRealm.block();
            }
        }.start();
        validationRealm.init(Thread.currentThread());
        dbc = new DataBindingContext(validationRealm);
        Policy.setLog(logger);
    }

    @Override
    protected void tearDown() throws Exception {
        dbc.dispose();
    }

    public void testListBindingValidationRealm() throws Throwable {
        final ObservableList model = new WritableList(targetAndModelRealm);
        final ObservableList target = new WritableList(targetAndModelRealm);
        dbc.bindList(target, model);
        targetAndModelRealm.waitUntilBlocking();
        targetAndModelRealm.processQueue();
        targetAndModelRealm.unblock();
        assertTrue(errorStatusses.toString(), errorStatusses.isEmpty());
    }

    public void testSetBindingValidationRealm() throws Throwable {
        final ObservableSet model = new WritableSet(targetAndModelRealm);
        final ObservableSet target = new WritableSet(targetAndModelRealm);
        dbc.bindSet(target, model);
        targetAndModelRealm.waitUntilBlocking();
        targetAndModelRealm.processQueue();
        targetAndModelRealm.unblock();
        assertTrue(errorStatusses.toString(), errorStatusses.isEmpty());
    }
}
