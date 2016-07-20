/***/
package org.eclipse.core.tests.databinding.observable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import org.eclipse.core.databinding.observable.AbstractObservable;
import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.ObservableTracker;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.util.ILogger;
import org.eclipse.core.databinding.util.Policy;
import org.eclipse.core.internal.databinding.IdentitySet;
import org.eclipse.core.runtime.AssertionFailedException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.databinding.conformance.util.CurrentRealm;
import org.eclipse.jface.tests.databinding.AbstractDefaultRealmTestCase;

public class ObservableTrackerTest extends AbstractDefaultRealmTestCase {

    public void testRunAndMonitor_GetterCalled() throws Exception {
        final IObservable observable = new ObservableStub();
        IObservable[] result = ObservableTracker.runAndMonitor(new Runnable() {

            @Override
            public void run() {
                ObservableTracker.getterCalled(observable);
            }
        }, null, null);
        assertEquals(1, result.length);
        assertSame(observable, result[0]);
    }

    public void testGetterCalled_ObservableDisposed() throws Exception {
        try {
            IObservable observable = new ObservableStub();
            observable.dispose();
            ObservableTracker.getterCalled(observable);
            fail("expected AssertionFailedException");
        } catch (AssertionFailedException expected) {
        }
    }

    public void testGetterCalled_ObservableRealmNotCurrent() throws Exception {
        try {
            IObservable observable = new ObservableStub(new CurrentRealm(false));
            ObservableTracker.getterCalled(observable);
            fail("expected AssertionFailedException");
        } catch (AssertionFailedException expected) {
        }
    }

    public void testRunAndCollect() throws Exception {
        final IObservable[] created = new IObservable[1];
        IObservable[] collected = ObservableTracker.runAndCollect(new Runnable() {

            @Override
            public void run() {
                created[0] = new ObservableStub();
            }
        });
        assertEquals(1, collected.length);
        assertSame(created[0], collected[0]);
    }

    public void testRunAndIgnore_RunAndMonitor() throws Exception {
        final IObservable observable = new ObservableStub();
        IObservable[] result = ObservableTracker.runAndMonitor(new Runnable() {

            @Override
            public void run() {
                ObservableTracker.runAndIgnore(new Runnable() {

                    @Override
                    public void run() {
                        ObservableTracker.getterCalled(observable);
                    }
                });
            }
        }, null, null);
        assertEquals(0, result.length);
    }

    public void testRunAndIgnore_RunAndCollect() throws Exception {
        IObservable[] result = ObservableTracker.runAndCollect(new Runnable() {

            @Override
            public void run() {
                ObservableTracker.runAndIgnore(new Runnable() {

                    @Override
                    public void run() {
                        new ObservableStub();
                    }
                });
            }
        });
        assertEquals(0, result.length);
    }

    public void testSetIgnore_RunAndMonitor() throws Exception {
        final IObservable observable = new ObservableStub();
        IObservable[] result = ObservableTracker.runAndMonitor(new Runnable() {

            @Override
            public void run() {
                ObservableTracker.setIgnore(true);
                ObservableTracker.getterCalled(observable);
                ObservableTracker.setIgnore(false);
            }
        }, null, null);
        assertEquals(0, result.length);
    }

    public void testSetIgnore_RunAndCollect() throws Exception {
        IObservable[] result = ObservableTracker.runAndCollect(new Runnable() {

            @Override
            public void run() {
                ObservableTracker.setIgnore(true);
                new ObservableStub();
                ObservableTracker.setIgnore(false);
            }
        });
        assertEquals(0, result.length);
    }

    public void testSetIgnore_Nested_RunAndCollect() throws Exception {
        final List<ObservableStub> list = new ArrayList<ObservableStub>();
        Set collected = new IdentitySet(Arrays.asList(ObservableTracker.runAndCollect(new Runnable() {

            @Override
            public void run() {
                // list[0] collected
                list.add(new ObservableStub());
                ObservableTracker.setIgnore(true);
                // list[1] ignored
                list.add(new ObservableStub());
                ObservableTracker.setIgnore(true);
                // list[2] ignored
                list.add(new ObservableStub());
                ObservableTracker.setIgnore(false);
                // list[3] ignored
                list.add(new ObservableStub());
                ObservableTracker.setIgnore(false);
                // list[4] collected
                list.add(new ObservableStub());
            }
        })));
        // Have to compare result in identity set because ObservableTracker may
        // not return them in the same order they were collected
        Set<ObservableStub> expected = new IdentitySet();
        expected.add(list.get(0));
        expected.add(list.get(4));
        assertEquals(expected, collected);
    }

    public void testSetIgnore_Nested_RunAndMonitor() throws Exception {
        final IObservable[] observables = { new ObservableStub(), new ObservableStub(), new ObservableStub(), new ObservableStub(), new ObservableStub() };
        Set result = new IdentitySet(Arrays.asList(ObservableTracker.runAndMonitor(new Runnable() {

            @Override
            public void run() {
                // monitored
                ObservableTracker.getterCalled(observables[0]);
                ObservableTracker.setIgnore(true);
                // ignored
                ObservableTracker.getterCalled(observables[1]);
                ObservableTracker.setIgnore(true);
                // ignored
                ObservableTracker.getterCalled(observables[2]);
                ObservableTracker.setIgnore(false);
                // ignored
                ObservableTracker.getterCalled(observables[3]);
                ObservableTracker.setIgnore(false);
                // monitored
                ObservableTracker.getterCalled(observables[4]);
            }
        }, null, null)));
        // Have to compare result in identity set because ObservableTracker may
        // not return them in the same order they were monitored
        Set<IObservable> expected = new IdentitySet();
        expected.add(observables[0]);
        expected.add(observables[4]);
        assertEquals(expected, result);
    }

    public void testSetIgnore_RunAndMonitor_UnmatchedIgnore_LogsError() {
        final List<IStatus> log = new ArrayList<IStatus>();
        Policy.setLog(new ILogger() {

            @Override
            public void log(IStatus status) {
                log.add(status);
            }
        });
        ObservableTracker.runAndMonitor(new Runnable() {

            @Override
            public void run() {
                ObservableTracker.setIgnore(true);
            // do not call call setIgnore(false)
            }
        }, null, null);
        assertEquals(1, log.size());
        IStatus status = log.get(0);
        assertEquals(IStatus.ERROR, status.getSeverity());
        assertTrue(status.getMessage().indexOf("setIgnore") != -1);
    }

    public void testSetIgnore_RunAndCollect_UnmatchedIgnore_LogsError() {
        final List<IStatus> log = new ArrayList<IStatus>();
        Policy.setLog(new ILogger() {

            @Override
            public void log(IStatus status) {
                log.add(status);
            }
        });
        ObservableTracker.runAndCollect(new Runnable() {

            @Override
            public void run() {
                ObservableTracker.setIgnore(true);
            // do not call call setIgnore(false)
            }
        });
        assertEquals(1, log.size());
        IStatus status = log.get(0);
        assertEquals(IStatus.ERROR, status.getSeverity());
        assertTrue(status.getMessage().indexOf("setIgnore") != -1);
    }

    public void testSetIgnore_UnmatchedUnignore() {
        try {
            ObservableTracker.setIgnore(false);
            fail("Expected IllegalStateException");
        } catch (IllegalStateException expected) {
        }
    }

    public static class ObservableStub extends AbstractObservable {

        public  ObservableStub() {
            this(Realm.getDefault());
        }

        public  ObservableStub(Realm realm) {
            super(realm);
        }

        @Override
        public boolean isStale() {
            return false;
        }
    }
}
