/***/
package org.eclipse.core.tests.internal.databinding.observable;

import junit.framework.Test;
import junit.framework.TestSuite;
import org.eclipse.core.databinding.observable.Diffs;
import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.ObservableTracker;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.WritableValue;
import org.eclipse.core.internal.databinding.observable.UnmodifiableObservableValue;
import org.eclipse.jface.databinding.conformance.ObservableValueContractTest;
import org.eclipse.jface.databinding.conformance.delegate.AbstractObservableValueContractDelegate;
import org.eclipse.jface.databinding.conformance.util.StaleEventTracker;
import org.eclipse.jface.tests.databinding.AbstractDefaultRealmTestCase;

/**
* @since 3.2
*
*/
public class UnmodifiableObservableValueTest extends AbstractDefaultRealmTestCase {

    private UnmodifiableObservableValueStub unmodifiable;

    public static Test suite() {
        TestSuite suite = new TestSuite(UnmodifiableObservableValueTest.class.getName());
        suite.addTestSuite(UnmodifiableObservableValueTest.class);
        suite.addTest(ObservableValueContractTest.suite(new Delegate()));
        return suite;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        WrappedObservableValue wrapped = new WrappedObservableValue(Realm.getDefault(), null, String.class);
        unmodifiable = new UnmodifiableObservableValueStub(wrapped);
    }

    public void testFiresStaleEvents() {
        StaleEventTracker wrappedListener = new StaleEventTracker();
        StaleEventTracker unmodifiableListener = new StaleEventTracker();
        unmodifiable.wrappedValue.addStaleListener(wrappedListener);
        unmodifiable.addStaleListener(unmodifiableListener);
        assertEquals(0, wrappedListener.count);
        assertEquals(0, unmodifiableListener.count);
        unmodifiable.wrappedValue.setStale(true);
        assertEquals(1, wrappedListener.count);
        assertEquals(unmodifiable.wrappedValue, wrappedListener.event.getObservable());
        assertTrue(unmodifiable.wrappedValue.isStale());
        assertEquals(1, unmodifiableListener.count);
        assertEquals(unmodifiable, unmodifiableListener.event.getObservable());
        assertTrue(unmodifiable.isStale());
    }

    public void testIsStale() {
        assertFalse(unmodifiable.wrappedValue.isStale());
        assertFalse(unmodifiable.isStale());
        unmodifiable.wrappedValue.setStale(true);
        assertTrue(unmodifiable.wrappedValue.isStale());
        assertTrue(unmodifiable.isStale());
    }

    private static class Delegate extends AbstractObservableValueContractDelegate {

        private Object valueType = new Object();

        @Override
        public IObservableValue createObservableValue(Realm realm) {
            return new UnmodifiableObservableValueStub(new WrappedObservableValue(realm, null, valueType));
        }

        @Override
        public Object getValueType(IObservableValue observable) {
            return valueType;
        }

        @Override
        public Object createValue(IObservableValue observable) {
            return new Object();
        }

        @Override
        public void change(IObservable observable) {
            UnmodifiableObservableValueStub wrapper = (UnmodifiableObservableValueStub) observable;
            wrapper.wrappedValue.setValue(createValue(wrapper));
        }

        @Override
        public void setStale(IObservable observable, boolean stale) {
            UnmodifiableObservableValueStub wrapper = (UnmodifiableObservableValueStub) observable;
            wrapper.wrappedValue.setStale(stale);
        }
    }

    private static class UnmodifiableObservableValueStub extends UnmodifiableObservableValue {

        WrappedObservableValue wrappedValue;

         UnmodifiableObservableValueStub(WrappedObservableValue wrappedValue) {
            super(wrappedValue);
            this.wrappedValue = wrappedValue;
        }
    }

    private static class WrappedObservableValue extends WritableValue {

        private boolean stale = false;

        public  WrappedObservableValue(Realm realm, Object initialValue, Object valueType) {
            super(realm, initialValue, valueType);
        }

        @Override
        public boolean isStale() {
            ObservableTracker.getterCalled(this);
            return stale;
        }

        public void setStale(boolean stale) {
            if (this.stale != stale) {
                this.stale = stale;
                if (stale) {
                    fireStale();
                } else {
                    fireValueChange(Diffs.createValueDiff(getValue(), getValue()));
                }
            }
        }
    }
}
