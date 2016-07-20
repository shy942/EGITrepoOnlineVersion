/***/
package org.eclipse.core.tests.databinding.observable.list;

import java.util.ArrayList;
import java.util.List;
import junit.framework.Test;
import junit.framework.TestSuite;
import org.eclipse.core.databinding.observable.AbstractObservable;
import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.IObservableCollection;
import org.eclipse.core.databinding.observable.IStaleListener;
import org.eclipse.core.databinding.observable.ObservableTracker;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.StaleEvent;
import org.eclipse.core.databinding.observable.list.ComputedList;
import org.eclipse.jface.databinding.conformance.ObservableListContractTest;
import org.eclipse.jface.databinding.conformance.delegate.AbstractObservableCollectionContractDelegate;
import org.eclipse.jface.databinding.conformance.util.ListChangeEventTracker;
import org.eclipse.jface.tests.databinding.AbstractDefaultRealmTestCase;

public class ComputedListTest extends AbstractDefaultRealmTestCase {

    ComputedListStub list;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        list = new ComputedListStub();
        // Force list to compute
        list.size();
    }

    public void testDependency_Staleness() {
        assertFalse(list.isStale());
        list.dependency.fireStale();
        assertTrue(list.isStale());
    }

    public void testDependency_FiresListChange() {
        assertEquals(list.nextComputation, list);
        Object element = new Object();
        list.nextComputation.add(element);
        list.dependency.fireChange();
        List expectedList = new ArrayList();
        expectedList.add(element);
        assertEquals(expectedList, list);
    }

    public void testDependency_NoStaleEventIfAlreadyDirty() {
        list.dependency.fireChange();
        list.addStaleListener(new IStaleListener() {

            @Override
            public void handleStale(StaleEvent staleEvent) {
                fail("Should not fire stale when list is already dirty");
            }
        });
        list.dependency.fireStale();
    }

    public void testDependency_ListChangeEventFiresOnlyWhenNotDirty() {
        ListChangeEventTracker tracker = ListChangeEventTracker.observe(list);
        list.dependency.fireChange();
        assertEquals("ComputedList should fire list change event when its dependency changes", 1, tracker.count);
        list.dependency.fireChange();
        assertEquals("ComputedList should not fire list change events when dirty", 1, tracker.count);
        // Force list to recompute.
        list.size();
        list.dependency.fireChange();
        assertEquals("ComputedList should fire list change event when its dependency changes", 2, tracker.count);
    }

    static class ComputedListStub extends ComputedList {

        List nextComputation = new ArrayList();

        ObservableStub dependency;

         ComputedListStub() {
            this(Realm.getDefault());
        }

         ComputedListStub(Realm realm) {
            super(realm);
            dependency = new ObservableStub(realm);
        }

        @Override
        protected List calculate() {
            ObservableTracker.getterCalled(dependency);
            return new ArrayList(nextComputation);
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
        TestSuite suite = new TestSuite(ComputedListTest.class.getName());
        suite.addTestSuite(ComputedListTest.class);
        suite.addTest(ObservableListContractTest.suite(new Delegate()));
        return suite;
    }

    static class Delegate extends AbstractObservableCollectionContractDelegate {

        @Override
        public IObservableCollection createObservableCollection(Realm realm, int elementCount) {
            final ComputedListStub list = new ComputedListStub(realm);
            for (int i = 0; i < elementCount; i++) list.nextComputation.add(createElement(list));
            // force list to compute
            list.size();
            return list;
        }

        @Override
        public void change(IObservable observable) {
            ComputedListStub list = (ComputedListStub) observable;
            list.nextComputation.add(new Object());
            list.dependency.fireChange();
        }

        @Override
        public void setStale(IObservable observable, boolean stale) {
            if (stale)
                ((ComputedListStub) observable).dependency.fireStale();
            else {
                ComputedListStub computedList = (ComputedListStub) observable;
                computedList.dependency.stale = false;
                computedList.dependency.fireChange();
            }
        }
    }
}
