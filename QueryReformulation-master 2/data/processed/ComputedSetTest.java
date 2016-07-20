/***/
package org.eclipse.core.tests.databinding.observable.set;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import junit.framework.Test;
import junit.framework.TestSuite;
import org.eclipse.core.databinding.observable.AbstractObservable;
import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.IObservableCollection;
import org.eclipse.core.databinding.observable.IStaleListener;
import org.eclipse.core.databinding.observable.ObservableTracker;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.StaleEvent;
import org.eclipse.core.databinding.observable.set.ComputedSet;
import org.eclipse.jface.databinding.conformance.ObservableCollectionContractTest;
import org.eclipse.jface.databinding.conformance.delegate.AbstractObservableCollectionContractDelegate;
import org.eclipse.jface.databinding.conformance.util.SetChangeEventTracker;
import org.eclipse.jface.tests.databinding.AbstractDefaultRealmTestCase;

public class ComputedSetTest extends AbstractDefaultRealmTestCase {

    ComputedSetStub set;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        set = new ComputedSetStub();
        // Force set to compute
        set.size();
    }

    public void testDependency_Staleness() {
        assertFalse(set.isStale());
        set.dependency.fireStale();
        assertTrue(set.isStale());
    }

    public void testDependency_FiresSetChange() {
        assertEquals(set.nextComputation, set);
        Object element = new Object();
        set.nextComputation.add(element);
        set.dependency.fireChange();
        assertEquals(Collections.singleton(element), set);
    }

    public void testDependency_NoStaleEventIfAlreadyDirty() {
        set.dependency.fireChange();
        set.addStaleListener(new IStaleListener() {

            @Override
            public void handleStale(StaleEvent staleEvent) {
                fail("Should not fire stale when set is already dirty");
            }
        });
        set.dependency.fireStale();
    }

    public void testDependency_SetChangeEventFiresOnlyWhenNotDirty() {
        SetChangeEventTracker tracker = SetChangeEventTracker.observe(set);
        set.dependency.fireChange();
        assertEquals("ComputedSet should fire set change event when its dependency changes", 1, tracker.count);
        set.dependency.fireChange();
        assertEquals("ComputedSet should not fire set change events when dirty", 1, tracker.count);
        // Force set to recompute.
        set.size();
        set.dependency.fireChange();
        assertEquals("ComputedSet should fire set change event when its dependency changes", 2, tracker.count);
    }

    static class ComputedSetStub extends ComputedSet {

        Set nextComputation = new HashSet();

        ObservableStub dependency;

         ComputedSetStub() {
            this(Realm.getDefault());
        }

         ComputedSetStub(Realm realm) {
            super(realm);
            dependency = new ObservableStub(realm);
        }

        @Override
        protected Set calculate() {
            ObservableTracker.getterCalled(dependency);
            return new HashSet(nextComputation);
        }
    }

    static class ObservableStub extends AbstractObservable {

        public  ObservableStub(Realm realm) {
            super(realm);
        }

        boolean stale;

        @Override
        public boolean isStale() {
            return stale;
        }

        @Override
        protected void fireStale() {
            stale = true;
            super.fireStale();
        }

        @Override
        protected void fireChange() {
            super.fireChange();
        }
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(ComputedSetTest.class.getName());
        suite.addTestSuite(ComputedSetTest.class);
        suite.addTest(ObservableCollectionContractTest.suite(new Delegate()));
        return suite;
    }

    static class Delegate extends AbstractObservableCollectionContractDelegate {

        @Override
        public IObservableCollection createObservableCollection(Realm realm, int elementCount) {
            final ComputedSetStub set = new ComputedSetStub(realm);
            for (int i = 0; i < elementCount; i++) set.nextComputation.add(createElement(set));
            // force set to compute
            set.size();
            return set;
        }

        @Override
        public void change(IObservable observable) {
            ComputedSetStub set = (ComputedSetStub) observable;
            set.nextComputation.add(new Object());
            set.dependency.fireChange();
        }

        @Override
        public Object createElement(IObservableCollection collection) {
            return new Object();
        }

        @Override
        public void setStale(IObservable observable, boolean stale) {
            if (stale)
                ((ComputedSetStub) observable).dependency.fireStale();
            else {
                ComputedSetStub computedSet = (ComputedSetStub) observable;
                computedSet.dependency.stale = false;
                computedSet.dependency.fireChange();
            }
        }
    }
}
