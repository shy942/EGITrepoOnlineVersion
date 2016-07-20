/***/
package org.eclipse.core.tests.internal.databinding.observable;

import junit.framework.Test;
import junit.framework.TestSuite;
import org.eclipse.core.databinding.observable.IObservable;
import org.eclipse.core.databinding.observable.Observables;
import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.core.databinding.observable.map.IObservableMap;
import org.eclipse.core.databinding.observable.map.WritableMap;
import org.eclipse.core.databinding.observable.value.IObservableValue;
import org.eclipse.core.databinding.observable.value.IValueChangeListener;
import org.eclipse.core.databinding.observable.value.ValueChangeEvent;
import org.eclipse.core.databinding.observable.value.ValueDiff;
import org.eclipse.core.internal.databinding.observable.MapEntryObservableValue;
import org.eclipse.jface.databinding.conformance.MutableObservableValueContractTest;
import org.eclipse.jface.databinding.conformance.ObservableStaleContractTest;
import org.eclipse.jface.databinding.conformance.delegate.AbstractObservableValueContractDelegate;
import org.eclipse.jface.tests.databinding.AbstractDefaultRealmTestCase;

/**
* @since 1.2
*/
public class MapEntryObservableValueTest extends AbstractDefaultRealmTestCase implements IValueChangeListener {

    private static final String VALUE1 = "Value1";

    private static final String VALUE2 = "Value2";

    private final Object key = "mapKey";

    private IObservableMap map;

    private ValueDiff diff;

    private MapEntryObservableValue observedValue;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.map = new WritableMap();
        this.observedValue = (MapEntryObservableValue) Observables.observeMapEntry(this.map, this.key, String.class);
        observedValue.addValueChangeListener(this);
    }

    @Override
    public void handleValueChange(ValueChangeEvent p_event) {
        this.diff = p_event.diff;
    }

    public void testNullValue() {
        // test entry added with value null
        this.map.put(this.key, null);
        assertNull(this.diff);
        assertNull(this.observedValue.getValue());
        // test value changed from null to null
        this.map.put(this.key, null);
        assertNull(this.diff);
        assertNull(this.observedValue.getValue());
        // test null-valued entry removed
        this.map.remove(this.key);
        assertNull(this.diff);
        assertNull(this.observedValue.getValue());
    }

    public void testNonNullValue() {
        // test add non-null value
        this.map.put(this.key, VALUE1);
        assertNotNull(this.diff);
        assertNull(this.diff.getOldValue());
        assertSame(VALUE1, this.diff.getNewValue());
        assertSame(VALUE1, this.observedValue.getValue());
        // test change to another non-null value
        this.diff = null;
        this.map.put(this.key, VALUE2);
        assertNotNull(this.diff);
        assertSame(VALUE1, this.diff.getOldValue());
        assertSame(VALUE2, this.diff.getNewValue());
        assertSame(VALUE2, this.observedValue.getValue());
    }

    public void testTransitionBetweenNullAndNonNull() {
        this.map.put(this.key, null);
        // test transition to non-null
        this.diff = null;
        this.map.put(this.key, VALUE1);
        assertNotNull(this.diff);
        assertNull(this.diff.getOldValue());
        assertSame(VALUE1, this.diff.getNewValue());
        // test transition to null
        this.diff = null;
        this.map.put(this.key, null);
        assertNotNull(this.diff);
        assertSame(VALUE1, this.diff.getOldValue());
        assertNull(this.diff.getNewValue());
    }

    public void testRemoveKey() {
        this.map.put(this.key, VALUE1);
        this.diff = null;
        this.map.remove(this.key);
        assertNotNull(this.diff);
        assertSame(VALUE1, this.diff.getOldValue());
        assertNull(this.diff.getNewValue());
    }

    public void testGetAndSetValue() {
        // test set null value
        this.observedValue.setValue(null);
        assertNull(this.observedValue.getValue());
        assertNull(this.diff);
        // test set non-null value
        this.observedValue.setValue(VALUE1);
        assertSame(VALUE1, this.observedValue.getValue());
        assertNotNull(this.diff);
        assertNull(this.diff.getOldValue());
        assertSame(VALUE1, this.diff.getNewValue());
        // test set another non-null value
        this.diff = null;
        this.observedValue.setValue(VALUE2);
        assertSame(VALUE2, this.observedValue.getValue());
        assertNotNull(this.diff);
        assertSame(VALUE1, this.diff.getOldValue());
        assertSame(VALUE2, this.diff.getNewValue());
    }

    public static Test suite() {
        TestSuite suite = new TestSuite(MapEntryObservableValueTest.class.getName());
        suite.addTestSuite(MapEntryObservableValueTest.class);
        suite.addTest(MutableObservableValueContractTest.suite(new Delegate()));
        suite.addTest(ObservableStaleContractTest.suite(new Delegate()));
        return suite;
    }

    private static class Delegate extends AbstractObservableValueContractDelegate {

        private Object valueType = new Object();

        @Override
        public IObservableValue createObservableValue(Realm realm) {
            WritableMap map = new WritableMap(realm);
            Object key = new Object();
            map.put(key, new Object());
            return new MapEntryObservableValueStub(map, key, valueType);
        }

        @Override
        public Object createValue(IObservableValue observable) {
            return new Object();
        }

        @Override
        public Object getValueType(IObservableValue observable) {
            return valueType;
        }

        @Override
        public void change(IObservable observable) {
            MapEntryObservableValueStub mapEntryValue = (MapEntryObservableValueStub) observable;
            mapEntryValue.map.put(mapEntryValue.key, createValue(mapEntryValue));
        }

        @Override
        public void setStale(IObservable observable, boolean stale) {
            MapEntryObservableValueStub mapEntryValue = (MapEntryObservableValueStub) observable;
            mapEntryValue.map.setStale(stale);
        }
    }

    private static class MapEntryObservableValueStub extends MapEntryObservableValue {

        WritableMap map;

        Object key;

         MapEntryObservableValueStub(WritableMap map, Object key, Object valueType) {
            super(map, key, valueType);
            this.map = map;
            this.key = key;
        }
    }
}
